package net.clesperanto.imglib2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;
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
public class Converters {

	private static String FLOAT32 = "float";
	private static String INT32 = "int";
	private static String UINT32 = "uint";
	private static String INT16 = "short";
	private static String UINT16 = "ushort";
	private static String INT8 = "char";
	private static String UINT8 = "uchar";
	
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
		String dType = arrayj.getDataType();
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		if (flatDims > Integer.MAX_VALUE)
			throw new IllegalArgumentException("The ArrayJ provided is too big to be converted into an ImgLib2 ArrayImg.");
		
		if (dType.equals(FLOAT32)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * 4).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readFloatBuffer(arrayj, byteBuffer.asFloatBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new FloatType(), arrayj.getDimensions());
		} else if (dType.equals(INT32)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * 4).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readIntBuffer(arrayj, byteBuffer.asIntBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new IntType(), arrayj.getDimensions());
		} else if (dType.equals(UINT32)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * 4).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readUIntBuffer(arrayj, byteBuffer.asIntBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new UnsignedIntType(), arrayj.getDimensions());
		} else if (dType.equals(INT16)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * 2).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readShortBuffer(arrayj, byteBuffer.asShortBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new ShortType(), arrayj.getDimensions());
		} else if (dType.equals(UINT16)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * 2).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readUShortBuffer(arrayj, byteBuffer.asShortBuffer(), 0);
			return fromBuffer(byteBuffer, (T) new UnsignedShortType(), arrayj.getDimensions());
		} else if (dType.equals(INT8)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readByteBuffer(arrayj, byteBuffer, 0);
			return fromBuffer(byteBuffer, (T) new ByteType(), arrayj.getDimensions());
		} else if (dType.equals(UINT8)) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims).order(ByteOrder.LITTLE_ENDIAN);
			MemoryJ.readUByteBuffer(arrayj, byteBuffer, 0);
			return fromBuffer(byteBuffer, (T) new UnsignedByteType(), arrayj.getDimensions());
		} else {
			throw new IllegalArgumentException();
		}
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
	 * 	the device into which the rai is going to be copied
	 * @param memoryType
	 * 	the type of memory array that we are working with. The options are image or buffer. For image use the
	 * 	String "image", for buffer use "buffer"
	 * @return an {@link ArrayJ} copied from the {@link RandomAccessibleInterval} of the CPU
	 */
	public static < T extends NativeType< T > > 
		ArrayJ copyImgLib2ToArrayJ(RandomAccessibleInterval<T> rai, DeviceJ device, String memoryType) {
		checkSize(rai);
		PrimitiveBlocks< T > blocks = PrimitiveBlocks.of( rai );
		long totalSize = Arrays.stream(rai.dimensionsAsLongArray()).reduce(1L, (a, b) -> a * b);
		if (totalSize > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		int[] integerDims = Arrays.stream(rai.dimensionsAsLongArray()).mapToInt(x -> (int) x).toArray();
		T type = Util.getTypeFromInterval(rai);
		
		if (type instanceof FloatType) {
			float[] flatArr = new float[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeFloatBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeFloatBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof IntType) {
			int[] flatArr = new int[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeIntBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeIntBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof UnsignedIntType) {
			int[] flatArr = new int[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeUIntBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeUIntBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof ShortType) {
			short[] flatArr = new short[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeShortBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeShortBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof UnsignedShortType) {
			short[] flatArr = new short[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeUShortBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeUShortBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof ByteType) {
			byte[] flatArr = new byte[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeByteBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeByteBuffer(arrayJ, flatArr, 0);
			return arrayJ;
		} else if (type instanceof UnsignedByteType) {
			byte[] flatArr = new byte[(int) totalSize];
			blocks.copy( new int[rai.dimensionsAsLongArray().length], flatArr, integerDims );
			ArrayJ arrayJ = MemoryJ.makeUByteBuffer(device, rai.dimensionsAsLongArray(), memoryType);
			MemoryJ.writeUByteBuffer(arrayJ, flatArr, 0);
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
