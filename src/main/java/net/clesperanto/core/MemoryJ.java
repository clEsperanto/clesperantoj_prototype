package net.clesperanto.core;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


/**
 * Class that provides the utility methods to read and write arrays to the wanted device (GPU)
 */
/**
 * TODO other possible methods:
 * - access a certain position of the array
 * - do we check that the dimensions of the float buffer or float[] match the ones of the ArrayJ  
 * 	when doing read or write here? Do we check them on the C side= dp we let them fail?
 * - method to return a reference to the byte buffer of the array in the GPU
 */
public class MemoryJ {
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an float array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeFloatBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeFloatBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an float array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeFloatBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeFloatBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU FloatBuffer into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link FloatBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the float buffer
	 */
	public static void writeFloatBuffer(ArrayJ array, FloatBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeFloatBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU float[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU float[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the float[]
	 */
	public static void writeFloatBuffer(ArrayJ array, float[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeFloatBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link FloatBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link FloatBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link FloatBuffer}
	 */
	public static void readFloatBuffer(ArrayJ array, FloatBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readFloatBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the float[]
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the float[] where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the float[]
	 */
	public static void readFloatBuffer(ArrayJ array, float[] data, long size) {
		net.clesperanto.jclic.MemoryJ.readFloatBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create a byte array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeByteBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeByteBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create a byte array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeByteBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeByteBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU ByteBuffer into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link ByteBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the ByteBuffer
	 */
	public static void writeByteBuffer(ArrayJ array, ByteBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU byte[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU byte[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the byte[]
	 */
	public static void writeByteBuffer(ArrayJ array, byte[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link ByteBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link ByteBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link ByteBuffer}
	 */
	public static void readByteBuffer(ArrayJ array, ByteBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the byte[]
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the byte[] where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the byte[]
	 */
	public static void readByteBuffer(ArrayJ array, byte[] data, long size) {
		net.clesperanto.jclic.MemoryJ.readByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create a byte array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeUByteBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUByteBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create a byte array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeUByteBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUByteBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU ByteBuffer into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link ByteBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the ByteBuffer
	 */
	public static void writeUByteBuffer(ArrayJ array, ByteBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU byte[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU byte[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the byte[]
	 */
	public static void writeUByteBuffer(ArrayJ array, byte[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link ByteBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link ByteBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link ByteBuffer}
	 */
	public static void readUByteBuffer(ArrayJ array, ByteBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readUByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the byte[]
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the byte[] where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the byte[]
	 */
	public static void readUByteBuffer(ArrayJ array, byte[] data, long size) {
		net.clesperanto.jclic.MemoryJ.readUByteBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an short array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the short array on the device
	 */
	public static ArrayJ makeShortBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeShortBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an short array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the short array on the device
	 */
	public static ArrayJ makeShortBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeShortBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU {@link ShortBuffer} into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link ShortBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the {@link ShortBuffer}
	 */
	public static void writeShortBuffer(ArrayJ array, ShortBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU short[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU short[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the short[]
	 */
	public static void writeShortBuffer(ArrayJ array, short[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link ShortBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link ShortBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link ShortBuffer}
	 */
	public static void readShortBuffer(ArrayJ array, ShortBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an unsigned short array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeUShortBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUShortBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an unsigned short array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the unsigned short array on the device
	 */
	public static ArrayJ makeUShortBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUShortBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU {@link ShortBuffer} into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link ShortBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the {@link ShortBuffer}
	 */
	public static void writeUShortBuffer(ArrayJ array, ShortBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU short[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU short[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the short[]
	 */
	public static void writeUShortBuffer(ArrayJ array, short[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link ShortBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link ShortBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link ShortBuffer}
	 */
	public static void readUShortBuffer(ArrayJ array, ShortBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readUShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the short[]
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the int[] where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the short[]
	 */
	public static void readUShortBuffer(ArrayJ array, short[] data, long size) {
		net.clesperanto.jclic.MemoryJ.readUShortBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an int array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeIntBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeIntBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an int array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the int array on the device
	 */
	public static ArrayJ makeIntBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeIntBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU {@link IntBuffer} into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link IntBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the {@link IntBuffer}
	 */
	public static void writeIntBuffer(ArrayJ array, IntBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU int[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU int[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the int[]
	 */
	public static void writeIntBuffer(ArrayJ array, int[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link IntBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link IntBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link IntBuffer}
	 */
	public static void readIntBuffer(ArrayJ array, IntBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an int array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not)
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param width
	 * 	the width of the nd-array created
	 * @param height
	 * 	the height of the nd-array created
	 * @param depth
	 * 	the depth of the nd-array created
	 * @param dimension
	 * 	the number of dimensions of the nd-array created, should be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeUIntBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUIntBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO what is the memory type and what are the possibilities
	 * 
	 * Create an int array in the wanted device with the wanted dimensions and the 
	 * memory type (whether it is an image or not).
	 * The dimensions array should be at least of length 1 and at max of length 3.
	 * The order of the dimensions should be: [width, height, depth]
	 * 
	 * 
	 * @param device
	 * 	the device where the data is going to be created, represented by {@link DeviceJ}
	 * @param dims
	 * 	the dimensions of the nd-array created
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the int array on the device
	 */
	public static ArrayJ makeUIntBuffer(DeviceJ device, long[] dims, String memoryType ) {
		dims = transformDims(dims);
		if (memoryType == null) memoryType = "";
		net.clesperanto.jclic.ArrayJ arrayJ = 
				net.clesperanto.jclic.MemoryJ.makeUIntBuffer(device.jcppDeviceJ, dims[0], dims[1], dims[2], 
						3, memoryType);
		return new ArrayJ(arrayJ);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU {@link IntBuffer} into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU {@link IntBuffer} into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the {@link IntBuffer}
	 */
	public static void writeUIntBuffer(ArrayJ array, IntBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * TODO size parameter might not be used
	 * Write the data from the CPU int[] into the existing {@link ArrayJ} on the device (GPU).
	 * 
	 * @param array
	 * 	the {@link ArrayJ} that is going to be overwritten
	 * @param data
	 * 	the data that is going to be copied from the CPU int[] into the device {@link ArrayJ}
	 * @param size 
	 * 	the total size in bytes of the int[]
	 */
	public static void writeUIntBuffer(ArrayJ array, int[] data, long size) {
		net.clesperanto.jclic.MemoryJ.writeUIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the {@link IntBuffer}
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the {@link IntBuffer} where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the {@link IntBuffer}
	 */
	public static void readUIntBuffer(ArrayJ array, IntBuffer data, long size) {
		net.clesperanto.jclic.MemoryJ.readUIntBuffer(array.arrayj, data, size);
	}
	
	/**
	 * Read the array located in the GPU defined by the {@link ArrayJ} 'array' into the int[]
	 * 'data' that is on the CPU.
	 * This method copies the data from the device into the CPU.
	 * 
	 * @param array
	 * 	the {@link ArrayJ} on the device
	 * @param data
	 * 	the int[] where the data from the device is going to be copied
	 * @param size
	 * 	the total size in bytes of the int[]
	 */
	public static void readUIntBuffer(ArrayJ array, int[] data, long size) {
		net.clesperanto.jclic.MemoryJ.readUIntBuffer(array.arrayj, data, size);
	}
	
	private static long[] transformDims(long[] dims) {
		if (dims.length > 3)
			throw new IllegalArgumentException();
		else if (dims.length == 0)
			throw new IllegalArgumentException();
		else if (dims.length == 1)
			return new long[] {dims[0], 1, 1};
		else 
			return new long[] {dims[0], dims[1], 1};
	}
}
