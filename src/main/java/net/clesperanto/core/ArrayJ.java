package net.clesperanto.core;

/**
 * Class to interact with the arrays allocated on the GPU by ClesperantoJ
 */
/**
 * TODO think about possible new methods
 * 
 * - method to modify the data inside the array from this class
 * - access a certain position of the array
 * - Do we check here if the size of the destination array is compatible for the method {@link #copyDataTo(ArrayJ)}
 * 
 */
public class ArrayJ {
	
	protected net.clesperanto.jclic.ArrayJ arrayj;

    protected ArrayJ(net.clesperanto.jclic.ArrayJ arrayj) {
    	this.arrayj = arrayj;
    }
    
    /**
     * 
     * @return the width of the array
     */
    public long getWidth() {
    	return arrayj.getWidth();
    }
    
    /**
     * 
     * @return the height of the array
     */
    public long getHeight() {
    	return arrayj.getHeight();
    }
    
    /**
     * 
     * @return the dimensions of the array. The order of the dimensions is [width, height, depth].
     */
    public long[] getDimensions() {
    	return new long[] {this.getWidth(), this.getHeight(), this.getDepth()};
    }
    
    /**
     * 
     * @return the depth of the array
     */
    public long getDepth() {
    	return arrayj.getDepth();
    }
    
    /**
     * 
     * @return the number of dimensions of the array
     */
    public int getNDimensions() {
    	return arrayj.getDimension();
    }
    
    /**
     * 
     * @return the data type of the array
     */
    public String getDataType() {
    	return arrayj.getDataType();
    }
    
    /**
     * 
     * @return the type of object that the array represents, either an 'image' or a 'buffer'
     */
    public String getMemoryType() {
    	return arrayj.getMemoryType();
    }
    
    /**
     * 
     * @return the name of the device where the array is allocated
     */
    public String getDevice() {
    	return arrayj.getDevice();
    }
    
    /**
     * Fill the array in the GPU with the wanted value.
     * All the positions in the array will be set to the same value
     * @param value
     * 	the value that every position in the array will adopt
     */
    public void fillMemory(float value) {
    	this.arrayj.fillMemory(value);
    }
    
    /*
     * Copy the data from the current array to the destination array given as a parameter
     * 
     * @param dst
     * 	the array into which the current array will be copied
     */
    public void copyDataTo(ArrayJ dst) {
    	this.arrayj.copyDataTo(dst.arrayj);
    }
    
    /**
     * 
     * @return the raw object that is going to be sent to the native Clesperanto library. Without Java wrappers
     */
    public net.clesperanto.jclic.ArrayJ getRaw() {
    	return this.arrayj;
    }
}
