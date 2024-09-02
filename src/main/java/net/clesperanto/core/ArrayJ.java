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

	protected final net.clesperanto._internals.jclic.ArrayJ arrayj;
	protected final DeviceJ devicej;

    public ArrayJ(net.clesperanto._internals.jclic.ArrayJ arrayj, DeviceJ devicej) {
    	if (!arrayj.getDevice().equals(devicej.getName()))
    		throw new IllegalArgumentException("Device name does not coincide with ArrayJ associated name: "
    				+ devicej.getName() + " vs " + arrayj.getDevice());
    	this.arrayj = arrayj;
    	this.devicej = devicej;
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
     * @return the device object that represents where the arrayJ is loaded
     */
    public DeviceJ getDevice() {
    	return devicej;
    }

    /**
     *
     *
     * @return the name of the device where the array is allocated
     */
    public String getDeviceName() {
    	return arrayj.getDevice();
    }

    /**
     *
     * @return a string representation of the array information
     */
    public String toString() {
        return "cle::Array[(" + this.getWidth() + ","+ this.getHeight() + ","+ this.getDepth() + "), dtype=" + this.getDataType() + ", mtype=" + this.getMemoryType() + "]";
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
    public net.clesperanto._internals.jclic.ArrayJ getRaw() {
    	return this.arrayj;
    }
}
