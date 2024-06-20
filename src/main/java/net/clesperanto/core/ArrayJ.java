package net.clesperanto.core;

public class ArrayJ {
	
	private net.clesperanto.wrapper.clesperantoj.ArrayJ arrayj;

    protected ArrayJ(net.clesperanto.wrapper.clesperantoj.ArrayJ arrayj) {
    	this.arrayj = arrayj;
    }
    
    public long getWidth() {
    	return arrayj.getWidth();
    }
    
    public long getHeight() {
    	return arrayj.getHeight();
    }
    
    public long getDepth() {
    	return arrayj.getDepth();
    }
    
    public int getNDimensions() {
    	return arrayj.getDimension();
    }
    
    public String getDataType() {
    	return arrayj.getDataType();
    }
    
    public String getMemoryType() {
    	return arrayj.getMemoryType();
    }
    
    public String getDevice() {
    	return arrayj.getDevice();
    }
    
    public void fillMemory(float value) {
    	this.arrayj.fillMemory(value);
    }
    
    public void fillMemory(ArrayJ dst) {
    	this.arrayj.copyDataTo(dst.arrayj);
    }
}
