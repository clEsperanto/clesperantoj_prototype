/*
 * Copyright 2024 Stéphane Rigaud, Robert Haase, Institut Pasteur Paris,
 * Max Planck Institute for Molecular Cell Biology and Genetics Dresden,
 * ScaDS.AI, Leipzig University
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package net.clesperanto.icy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import icy.image.IcyBufferedImage;
import icy.preferences.ApplicationPreferences;
import icy.preferences.GeneralPreferences;
import icy.preferences.IcyPreferences;
import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
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
public class IcyConverters {

	// Icy requires that the preferences are initialized if they have not bee initialized before.
	// TODO check if htey have been initialized before
	static {
		initIcyPreferences();
	}

	/** TODO extend to RandomAccessibleInterval
	 * Conert an {@link ArrayJ} into an ImgLib2 {@link ArrayImg} of the same dimensions and data type.
	 * Creates a copy of the ArrayJ in the GPU into an ArrayImg in the CPU
	 *
	 * @param arrayj
	 * 	array that is located in the GPU for clesperanto to do some operations
	 * @return and ImgLib2 {@link ArrayImg} on the CPU copied from the {@link ArrayJ} on the GPU
	 */
	public static Sequence copyArrayJToSequence( ArrayJ arrayj )
	{
		long flatDims = arrayj.getHeight() * arrayj.getDepth() * arrayj.getWidth();
		IcyDataType dataType = IcyDataType.fromString(arrayj.getDataType());
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
	public static ArrayJ copySequenceToArrayJ(Sequence rai, DeviceJ device, String memoryType) {
		IcyDataType dataType = IcyDataType.fromIcyDataType(rai.getDataType_());
		Map<String, Integer> sizeMap = checkSize(rai, dataType.getByteSize());
		long totalSize = sizeMap.values().stream().reduce((int) 1L, (a, b) -> a * b);

		long[] dims = sizeMap.values().stream().mapToLong(i -> (long) i).toArray();
	    Object flatArr = dataType.createArray((int) totalSize);
        SequenceCursor cursor = new SequenceCursor(rai);
	    int sizeT = sizeMap.get("t") != null ? sizeMap.get("t"): 1;
	    int sizeZ = sizeMap.get("z") != null ? sizeMap.get("z"): 1;
	    int sizeC = sizeMap.get("c") != null ? sizeMap.get("c"): 1;
	    int sizeY = sizeMap.get("y") != null ? sizeMap.get("y"): 1;
	    int sizeX = sizeMap.get("x") != null ? sizeMap.get("x"): 1;
	    int pos = 0;
	    for (int t = 0; t <  sizeT; t ++) {
	    	for (int z = 0; z <  sizeZ; z ++) {
	    		for (int c = 0; c <  sizeC; c ++) {
	    			for (int y = 0; y <  sizeY; y ++) {
	    				for (int x = 0; x <  sizeX; x ++) {
	    					dataType.putValInArray(flatArr, pos ++,  cursor.get(x, y, z, t, c));
	    			    }
	    		    }
	    	    }
		    }
	    }

	    return dataType.makeAndWriteArrayJ(flatArr, device, dims, memoryType);
	}

	private static Sequence fromBuffer(ByteBuffer byteBuffer, IcyDataType type, long[] dimensions) {
	    Sequence im = createSequence(dimensions, type.createType());
	    switch (type) {
	        case FLOAT32:
	            FloatBuffer floatBuff = byteBuffer.asFloatBuffer();
	            fillImage(im, dimensions, floatBuff::get);
	            break;
	        case INT8:
	            fillImage(im, dimensions, byteBuffer::get);
	            break;
	        case UINT8:
	            fillImage(im, dimensions, byteBuffer::get);
	            break;
	        case UINT16:
	            ShortBuffer uShortBuff = byteBuffer.asShortBuffer();
	            fillImage(im, dimensions, uShortBuff::get);
	            break;
	        case INT16:
	            ShortBuffer shortBuff = byteBuffer.asShortBuffer();
	            fillImage(im, dimensions, shortBuff::get);
	            break;
	        case INT32:
	            IntBuffer intBuff = byteBuffer.asIntBuffer();
	            fillImage(im, dimensions, intBuff::get);
	            break;
	        case UINT32:
	            IntBuffer uIntBuff = byteBuffer.asIntBuffer();
	            fillImage(im, dimensions, uIntBuff::get);
	            break;
	        default:
	            throw new IllegalArgumentException("Data type not supported.");
	    }

	    return im;
	}

	private static void fillImage(Sequence im, long[] dimensions, Supplier<Number> getValue) {
        SequenceCursor cursor = new SequenceCursor(im);
	    for (int z = 0; z < dimensions[2]; z++) {
	        for (int y = 0; y < dimensions[1]; y++) {
	            for (int x = 0; x < dimensions[0]; x++) {
	            	cursor.set(x, y, z, 0, 0, getValue.get().doubleValue());
	            }
	        }
	    }
	    cursor.commitChanges();
	}

	private static Map<String, Integer> checkSize(Sequence imp, long byteSize) {
		Map<String, Integer> sizeMap = new LinkedHashMap<String, Integer>();
		sizeMap.put("x", imp.getWidth() == 0 ? 1 : imp.getWidth());
		sizeMap.put("y", imp.getHeight() == 0 ? 1 : imp.getHeight());
		sizeMap.put("z", imp.getSizeZ() == 0 ? 1 : imp.getSizeZ());
		for (Integer vv : sizeMap.values()) {
			byteSize *= (long) vv;
		}
		if (byteSize > Integer.MAX_VALUE)
			throw new IllegalArgumentException();
		return sizeMap;
	}

    /**
     * Create a sequence of the wanted size and data type
     * @param dims
     * 	wanted size of the sequence in the order [xyczt]
     * @param type
     * 	type of the sequence
     * @return empty Icy sequence of the wanted type and dimensions
     */
    public static Sequence createSequence(long[] dims, icy.type.DataType type)
    {
    	while (dims.length < 3) {
    		long[] newArray = new long[dims.length + 1];
    	    System.arraycopy(dims, 0, newArray, 0, dims.length);
    	    newArray[dims.length] = 1;
    	    dims = newArray;
    	}
        Sequence seq = new Sequence();
        int z;
        for (z = 0; z < dims[2]; z++)
        {
            seq.setImage(0, z, new IcyBufferedImage((int) dims[0], (int) dims[1], 1, type));
        }
        return seq;
    }

    /**
     * Initialize the Icy meta data that is required to use Icy Sequences.
     * Only initializes it if it has not been done before.
     */
    public static void initIcyPreferences() {
    	if (ApplicationPreferences.getPreferences() == null
    			|| GeneralPreferences.getPreferences() == null) {
            IcyPreferences.init();
    	}
    }

    public static void main(String[] args) {

    	createSequence(new long[] {5, 5, 5}, icy.type.DataType.FLOAT);
    }
}
