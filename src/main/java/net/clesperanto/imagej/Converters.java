package net.clesperanto.imagej;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import ij.ImagePlus;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

/**
 * TODO
 * - define whether the buffers follow big or little endian and whether the copies are c- or fortran order
 * - using array img and blocks copy limits to images whose array size is smaller than the integer max
 */
/**
 * Class to copy {@link RandomAccessibleInteral}s into {@link ArrayJ}s and vice-versa
 */
public class Converters {

	/** TODO extend to RandomAccessibleInterval
	 * Conert an {@link ArrayJ} into an ImgLib2 {@link ArrayImg} of the same dimensions and data type.
	 * Creates a copy of the ArrayJ in the GPU into an ArrayImg in the CPU
	 *
	 * @param arrayj
	 * 	array that is located in the GPU for clesperanto to do some operations
	 * @return and ImgLib2 {@link ArrayImg} on the CPU copied from the {@link ArrayJ} on the GPU
	 */
	public static ImagePlus copyArrayJToImgLib2(
			ArrayJ arrayj )
	{
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		ImageJDataType dataType = ImageJDataType.fromString(arrayj.getDataType());
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
	 * @param rai
	 *  the {@link RandomAccessibleInterval} that is going to be copied into the GPU
	 * @param device
	 * 	the device into which the rai is going to be copied. If null, the default system device is used.
	 * @param memoryType
	 * 	the type of memory array that we are working with. The options are image or buffer. For image use the
	 * 	String "image", for buffer use "buffer"
	 * @return an {@link ArrayJ} copied from the {@link RandomAccessibleInterval} of the CPU
	 */
	public static ArrayJ copyImgLib2ToArrayJ(ImagePlus rai, DeviceJ device, String memoryType) {
		checkSize(rai);
		int type = rai.getType();
		ImageJDataType dataType = ImageJDataType.fromImgPlusDataType(type);
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

	private static void checkSize(ImagePlus rai) {
		int[] dims = rai.getDimensions();
		for (long l : dims) {
			if (l > Integer.MAX_VALUE)
				throw new IllegalArgumentException();
		}
	}

}
