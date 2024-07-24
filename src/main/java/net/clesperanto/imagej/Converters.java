package net.clesperanto.imagej;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
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
	public static ImagePlus copyArrayJToImgLib2( ArrayJ arrayj )
	{
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		ImageJDataType dataType = ImageJDataType.fromString(arrayj.getDataType());
		if (flatDims * dataType.getByteSize() > Integer.MAX_VALUE)
			throw new IllegalArgumentException("The ArrayJ provided is too big to be converted into an ImgLib2 ArrayImg.");

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) flatDims * dataType.getByteSize())
                .order(ByteOrder.LITTLE_ENDIAN);
		dataType.readToBuffer(arrayj, byteBuffer);

		return fromBuffer(byteBuffer, dataType, arrayj.getDimensions());
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
		Map<String, Integer> sizeMap = checkSize(rai);
		ImageJDataType dataType = ImageJDataType.fromImgPlusDataType(rai.getType());
		long totalSize = sizeMap.values().stream().reduce((int) 1L, (a, b) -> a * b);

		long[] dims = sizeMap.values().stream().mapToLong(i -> (long) i).toArray();
	    Object flatArr = dataType.createArray((int) totalSize);

	    int sizeT = sizeMap.get("t") != null ? sizeMap.get("t"): 1;
	    int sizeZ = sizeMap.get("z") != null ? sizeMap.get("z"): 1;
	    int sizeC = sizeMap.get("c") != null ? sizeMap.get("c"): 1;
	    int sizeY = sizeMap.get("y") != null ? sizeMap.get("y"): 1;
	    int sizeX = sizeMap.get("x") != null ? sizeMap.get("x"): 1;
	    int pos = 0;
	    for (int t = 0; t <  sizeT; t ++) {
	    	for (int z = 0; z <  sizeZ; z ++) {
	    		for (int c = 0; c <  sizeC; c ++) {
	    			rai.setPositionWithoutUpdate(c + 1, z + 1, t + 1);
	    			ImageProcessor ip = rai.getProcessor();
	    			for (int y = 0; y <  sizeY; y ++) {
	    				for (int x = 0; x <  sizeX; x ++) {
	    					dataType.putValInArray(flatArr, pos ++,  ip.getValue(x, y));
	    			    }
	    		    }
	    	    }
		    }
	    }

	    return dataType.makeAndWriteArrayJ(flatArr, device, dims, memoryType);
	}

	private static ImagePlus fromBuffer(ByteBuffer byteBuffer, ImageJDataType type, long[] dimensions) {
	    ImagePlus im = IJ.createImage("image", (int) dimensions[0], (int) dimensions[1], (int) dimensions[2], type.createType());
	    
	    switch (type) {
	        case FLOAT32:
	            FloatBuffer floatBuff = byteBuffer.asFloatBuffer();
	            fillImage(im, dimensions, floatBuff::get);
	            break;
	        case UINT8:
	            fillImage(im, dimensions, byteBuffer::get);
	            break;
	        case UINT16:
	            ShortBuffer shortBuff = byteBuffer.asShortBuffer();
	            fillImage(im, dimensions, shortBuff::get);
	            break;
	        default:
	            throw new IllegalArgumentException("Data type not supported.");
	    }
	    
	    return im;
	}

	private static void fillImage(ImagePlus im, long[] dimensions, Supplier<Number> getValue) {
	    for (int z = 0; z < dimensions[2]; z++) {
	        im.setPositionWithoutUpdate(1, 1 + z, 1);
	        ImageProcessor ip = im.getProcessor();
	        for (int y = 0; y < dimensions[1]; y++) {
	            for (int x = 0; x < dimensions[0]; x++) {
	                ip.putPixelValue(x, y, getValue.get().doubleValue());
	            }
	        }
	    }
	}

	private static Map<String, Integer> checkSize(ImagePlus imp) {
		Map<String, Integer> sizeMap = new LinkedHashMap<String, Integer>();
		sizeMap.put("x", imp.getWidth());
		sizeMap.put("y", imp.getHeight());
		sizeMap.put("c", imp.getNChannels());
		sizeMap.put("z", imp.getNSlices());
		sizeMap.put("t", imp.getNFrames());
		sizeMap = sizeMap.entrySet().stream()
				.filter(ee -> ee.getValue() == 1).collect(Collectors.toMap(ee -> ee.getKey(), ee -> ee.getValue()));
		while (sizeMap.entrySet().size() > 3) {
			if (sizeMap.get("t") != null)
				sizeMap.remove("t");
			else if (sizeMap.get("c") != null)
				sizeMap.remove("c");
			else if (sizeMap.get("z") != null)
				sizeMap.remove("z");
		}
		int tot = 1;
		for (Integer vv : sizeMap.values()) {
			tot *= vv;
		}
		if (tot > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		return sizeMap;
	}

}
