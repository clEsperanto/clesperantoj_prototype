package net.clesperanto.imglib2;

import java.nio.ByteBuffer;
import java.util.Arrays;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.blocks.PrimitiveBlocks;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.basictypeaccess.nio.BufferAccess;
import net.imglib2.img.basictypeaccess.nio.BufferDataAccessFactory;
import net.imglib2.type.NativeType;
import net.imglib2.type.NativeTypeFactory;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Fraction;

/**
 * TODO 
 * - define whether the buffers follow big or little endian and whether the copies are c- or fortran order
 * - using array img and blocks copy limits to images whose array size is smaller than the integer max
 */
public class Converters {
	
	private static String FLOAT32 = "float32";
	
	public static < T extends NativeType< T >, A extends BufferAccess< A > > ArrayImg< T, A > arrayJToImgLib2(
			ArrayJ arrayj )
	{
		String dType = arrayj.getDataType();
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		if (flatDims > Integer.MAX_VALUE)
			throw new IllegalArgumentException("The ArrayJ provided is too big to be converted into an ImgLib2 ArrayImg.");
		
		if (dType.equals(FLOAT32)) {
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) flatDims * 4);
			MemoryJ.readFloatBuffer(arrayj, byteBuffer.asFloatBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new FloatType(), arrayj.getDimensions());
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static < T extends NativeType< T > > ArrayJ imgLib2ToArrayJ(RandomAccessibleInterval<T> rai, DeviceJ device, String memoryType) {
		checkSize(rai);
		PrimitiveBlocks< T > blocks = PrimitiveBlocks.of( rai );
		long totalSize = Arrays.stream(rai.dimensionsAsLongArray()).reduce(1L, (a, b) -> a * b);
		if (totalSize > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		int[] integerDims = Arrays.stream(rai.dimensionsAsLongArray()).mapToInt(x -> (int) x).toArray();
		
		if (rai.getType() instanceof FloatType) {
			float[] flatArr = new float[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeFloatBuffer(device, integerDims[0], integerDims[1], integerDims[2], totalSize, memoryType);
			MemoryJ.writeFloatBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else {
			throw new IllegalArgumentException();
		}
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
