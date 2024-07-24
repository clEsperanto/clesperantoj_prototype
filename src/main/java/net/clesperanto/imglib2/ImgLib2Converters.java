package net.clesperanto.imglib2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.blocks.PrimitiveBlocks;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.basictypeaccess.nio.BufferAccess;
import net.imglib2.img.basictypeaccess.nio.BufferDataAccessFactory;
import net.imglib2.type.NativeType;
import net.imglib2.type.NativeTypeFactory;
import net.imglib2.util.Fraction;
import net.imglib2.util.Util;

/**
 * TODO
 * - define whether the buffers follow big or little endian and whether the copies are c- or fortran order
 * - using array img and blocks copy limits to images whose array size is smaller than the integer max
 */
/**
 * Class to copy {@link RandomAccessibleInteral}s into {@link ArrayJ}s and vice-versa
 */
public class ImgLib2Converters {

	/** TODO extend to RandomAccessibleInterval
	 * Conert an {@link ArrayJ} into an ImgLib2 {@link ArrayImg} of the same dimensions and data type.
	 * Creates a copy of the ArrayJ in the GPU into an ArrayImg in the CPU
	 *
	 * @param <T>
	 * 	data type of the ImgLib2 ArrayImg
	 * @param <A>
	 * 	ImgLib2 data type of the BufferAccess
	 * @param arrayj
	 * 	array that is located in the GPU for clesperanto to do some operations
	 * @return and ImgLib2 {@link ArrayImg} on the CPU copied from the {@link ArrayJ} on the GPU
	 */
	public static < T extends NativeType< T >, A extends BufferAccess< A > > ArrayImg< T, A > copyArrayJToImgLib2(
			ArrayJ arrayj )
	{
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		ImgLib2DataType dataType = ImgLib2DataType.fromString(arrayj.getDataType());
		if (flatDims * dataType.getByteSize() > Integer.MAX_VALUE)
			throw new IllegalArgumentException("The ArrayJ provided is too big to be converted into an ImgLib2 ArrayImg.");

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * dataType.getByteSize())
                .order(ByteOrder.LITTLE_ENDIAN);
		dataType.readToBuffer(arrayj, byteBuffer);

		T type = dataType.createType();

		return fromBuffer(byteBuffer, type, arrayj.getDimensions());
	}

	/**
	 * Copy a {@link RandomAccessibleInterval} on the CPU into an {@link ArrayJ} on the device (GPU) of interest.
	 * The {@link RandomAccessibleInterval} should have at most 3 dimensions, and the order of the dimensions
	 * should be [width, height, depth]
	 *
	 *
	 * @param <T>
	 * 	the ImgLib2 data type of the {@link RandomAccessibleInterval}
	 * @param rai
	 *  the {@link RandomAccessibleInterval} that is going to be copied into the GPU
	 * @param device
	 * 	the device into which the rai is going to be copied. If null, the default system device is used.
	 * @param memoryType
	 * 	the type of memory array that we are working with. The options are image or buffer. For image use the
	 * 	String "image", for buffer use "buffer"
	 * @return an {@link ArrayJ} copied from the {@link RandomAccessibleInterval} of the CPU
	 */
	public static < T extends NativeType< T > >
		ArrayJ copyImgLib2ToArrayJ(RandomAccessibleInterval<T> rai, DeviceJ device, String memoryType) {
		checkSize(rai);
		T type = Util.getTypeFromInterval(rai);
		ImgLib2DataType dataType = ImgLib2DataType.fromImgLib2DataType(type);
		PrimitiveBlocks< T > blocks = PrimitiveBlocks.of( rai );
		long totalSize = Arrays.stream(rai.dimensionsAsLongArray()).reduce(1L, (a, b) -> a * b);
		if (totalSize * dataType.getByteSize() > Integer.MAX_VALUE)
			throw new IllegalArgumentException();

		int[] integerDims = Arrays.stream(rai.dimensionsAsLongArray()).mapToInt(x -> (int) x).toArray();
	    Object flatArr = dataType.createArray((int) totalSize);
	    blocks.copy(new int[rai.dimensionsAsLongArray().length], flatArr, integerDims);

	    return dataType.makeAndWriteArrayJ(flatArr, device, rai.dimensionsAsLongArray(), memoryType);
	}

	private static < T extends NativeType< T >, A extends BufferAccess< A > > ArrayImg< T, A >
		fromBuffer(ByteBuffer byteBuffer, T type, long[] dimensions) {

		final Fraction entitiesPerPixel = type.getEntitiesPerPixel();
		@SuppressWarnings( { "unchecked", "rawtypes" } )
		final NativeTypeFactory< T, ? super A > typeFactory = ( NativeTypeFactory ) type.getNativeTypeFactory();
		final A access = BufferDataAccessFactory.get( typeFactory );
		final A data = access.newInstance( byteBuffer, true );
		final ArrayImg< T, A > img = new ArrayImg<>( data, dimensions, entitiesPerPixel );
		img.setLinkedType( typeFactory.createLinkedType( img ) );
		return img;
	}

	private static < T extends NativeType< T > > void checkSize(RandomAccessibleInterval<T> rai) {
		long[] dims = rai.dimensionsAsLongArray();
		for (long l : dims) {
			if (l > Integer.MAX_VALUE)
				throw new IllegalArgumentException();
		}
	}

}
