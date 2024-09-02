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

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;


public enum IcyDataType {
    FLOAT32(DataType.fromString("float"), icy.type.DataType.FLOAT, float[].class),
    INT32(DataType.fromString("int"), icy.type.DataType.INT, int[].class),
    UINT32(DataType.fromString("uint"), icy.type.DataType.UINT, int[].class),
    INT16(DataType.fromString("short"), icy.type.DataType.SHORT, short[].class),
    UINT16(DataType.fromString("ushort"), icy.type.DataType.USHORT, short[].class),
    INT8(DataType.fromString("char"), icy.type.DataType.BYTE, byte[].class),
    UINT8(DataType.fromString("uchar"), icy.type.DataType.UBYTE, byte[].class);

    private final DataType dt;
    private final icy.type.DataType icyDT;
    private final Class<?> arrayClass;

	public final static long MAX_UINT32 = (long) Math.pow(2, 32);

	public final static int MAX_INT32 = Integer.MAX_VALUE;

	public final static int MAX_UINT16 = 65536;

	public final static int MAX_INT16 = 65536 /  2 - 1;

	public final static int MAX_UINT8 = 256;

	public final static int MAX_INT8 = 256 / 2 - 1;

    IcyDataType(DataType dt, icy.type.DataType icyDT, Class<?> arrayClass) {
    	this.dt = dt;
        this.icyDT = icyDT;
        this.arrayClass = arrayClass;
    }

    public static IcyDataType fromString(String dType) {
        for (IcyDataType type : values()) {
            if (type.dt.getName().equals(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
    }

    public String getName() {
    	return dt.getName();
    }

    public static IcyDataType fromIcyDataType(icy.type.DataType icyType) {
        for (IcyDataType type : values()) {
            if (type.createType() == icyType) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + icyType.toString());
    }

    public Object createArray(int size) {
        return dt.createArray(size);
    }

    public int getByteSize() {
    	return dt.getByteSize();
    }

    public void readToBuffer(ArrayJ arrayj, ByteBuffer buffer) {
    	this.dt.readToBuffer(arrayj, buffer);
    }

    public void writeToBuffer(ArrayJ arrayj, ByteBuffer buffer) {
    	dt.writeToBuffer(arrayj, buffer);
    }

    public ArrayJ makeEmptyArrayJ(DeviceJ device, long[] dims, String memoryType) {
    	return dt.makeEmptyArrayJ(device, dims, memoryType);
    }

    public ArrayJ makeAndWriteArrayJ(Object buffer, DeviceJ device, long[] dims, String memoryType) {
    	return dt.makeAndWriteArrayJ(buffer, device, dims, memoryType);
    }

    public icy.type.DataType createType() {
        return this.icyDT;
    }

    public void putValInArray(Object arr, int pos, Number value) {
    	if (!arrayClass.isInstance(arr))
            throw new IllegalArgumentException("The input argument 'arr' is not an instance of " + arrayClass.getSimpleName() + ".");

        switch (this) {
            case FLOAT32:
                ((float[]) arr)[pos] = value.floatValue();
                break;
            case UINT32:
                ((int[]) arr)[pos] = (int) (value.doubleValue() > Integer.MAX_VALUE ? value.doubleValue() - MAX_UINT32 : value.doubleValue());
                break;
            case INT32:
                ((int[]) arr)[pos] = value.intValue();
                break;
            case UINT16:
                ((short[]) arr)[pos] = (short) (value.doubleValue() > Short.MAX_VALUE ? value.doubleValue() - MAX_UINT16 : value.doubleValue());
                break;
            case INT16:
                ((short[]) arr)[pos] = value.shortValue();
                break;
            case UINT8:
                ((byte[]) arr)[pos] = (byte) (value.doubleValue() > Byte.MAX_VALUE ? value.doubleValue() - MAX_UINT8 : value.doubleValue());
                break;
            case INT8:
                ((byte[]) arr)[pos] = value.byteValue();
                break;
            default:
            	throw new IllegalArgumentException("Unsupported data type");
        }
    }
}
