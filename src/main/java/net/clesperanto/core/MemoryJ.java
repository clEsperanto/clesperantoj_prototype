package net.clesperanto.core;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;


/**
 * Class that provides the utility methods to read and write arrays to the wanted device (GPU)
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
	 * 	the dimensions of the nd-array created, shuould be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeFloatBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		if (memoryType == null) memoryType = "";
		net.clesperanto.wrapper.clesperantoj.ArrayJ arrayJ = 
				net.clesperanto.wrapper.clesperantoj.MemoryJ.makeFloatBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.writeFloatBuffer(array.arrayj, data, size);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.writeFloatBuffer(array.arrayj, data, size);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.readFloatBuffer(array.arrayj, data, size);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.readFloatBuffer(array.arrayj, data, size);
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
	 * 	the dimensions of the nd-array created, shuould be between 1 and 3
	 * @param memoryType
	 * 	whether the array represents an image or not. For the memory type to be image, 
	 * 	the String should be "image" any other String will be understood as "buffer"
	 * @return an {@link ArrayJ} that references the float array on the device
	 */
	public static ArrayJ makeIntBuffer(DeviceJ device, long width, long height, long depth, 
			long dimension, String memoryType ) {
		net.clesperanto.wrapper.clesperantoj.ArrayJ arrayJ = 
				net.clesperanto.wrapper.clesperantoj.MemoryJ.makeIntBuffer(device.jcppDeviceJ, width, height, depth, 
						dimension, memoryType);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.writeIntBuffer(array.arrayj, data, size);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.writeIntBuffer(array.arrayj, data, size);
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
		net.clesperanto.wrapper.clesperantoj.MemoryJ.readIntBuffer(array.arrayj, data, size);
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
	public static void readIntBuffer(ArrayJ array, int[] data, long size) {
		net.clesperanto.wrapper.clesperantoj.MemoryJ.readIntBuffer(array.arrayj, data, size);
	}
}
