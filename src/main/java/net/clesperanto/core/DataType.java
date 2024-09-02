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

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.function.Function;

public enum DataType {
    FLOAT32("float", 4, float.class, ByteBuffer::asFloatBuffer,
            (array, buffer, offset) -> MemoryJ.readFloatBuffer(array, (FloatBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeFloatBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeFloatBuffer(array, (float[])buffer, offset)
            ),
    INT32("int", 4, int.class, ByteBuffer::asIntBuffer,
            (array, buffer, offset) -> MemoryJ.readIntBuffer(array, (IntBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeIntBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeIntBuffer(array, (int[])buffer, offset)
            ),
    UINT32("uint", 4, int.class, ByteBuffer::asIntBuffer,
            (array, buffer, offset) -> MemoryJ.readUIntBuffer(array, (IntBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUIntBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUIntBuffer(array, (int[])buffer, offset)
            ),
    INT16("short", 2, short.class, ByteBuffer::asShortBuffer,
            (array, buffer, offset) -> MemoryJ.readShortBuffer(array, (ShortBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeShortBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeShortBuffer(array, (short[])buffer, offset)
            ),
    UINT16("ushort", 2, short.class, ByteBuffer::asShortBuffer,
            (array, buffer, offset) -> MemoryJ.readUShortBuffer(array, (ShortBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUShortBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUShortBuffer(array, (short[])buffer, offset)
            ),
    INT8("char", 1, byte.class, b -> b,
            (array, buffer, offset) -> MemoryJ.readByteBuffer(array, (ByteBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeByteBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeByteBuffer(array, (byte[])buffer, offset)
            ),
    UINT8("uchar", 1, byte.class, b -> b,
            (array, buffer, offset) -> MemoryJ.readUByteBuffer(array, (ByteBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUByteBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUByteBuffer(array, (byte[])buffer, offset)
            );

    private final String name;
    private final int byteSize;
    private final Class<?> arrayClass;
    private final MemoryReadFunction readFunction;
    private final MemoryWriteFunction writeFunction;
    private final MemoryMakeFunction makeFunction;
    private final Function<ByteBuffer, Buffer> bufferConverter;

    DataType(String name, int byteSize, Class<?> arrayClass, Function<ByteBuffer, Buffer> bufferConverter,
    		 MemoryReadFunction readFunction, MemoryMakeFunction makeFunction, MemoryWriteFunction writeFunction) {
        this.name = name;
        this.byteSize = byteSize;
        this.arrayClass = arrayClass;
        this.readFunction = readFunction;
        this.writeFunction = writeFunction;
        this.makeFunction = makeFunction;
        this.bufferConverter = bufferConverter;
    }

    public static DataType fromString(String dType) {
        for (DataType type : values()) {
            if (type.name.equals(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
    }

    public String getName() {
    	return name;
    }

    public Object createArray(int size) {
        return java.lang.reflect.Array.newInstance(arrayClass, size);
    }

    public int getByteSize() {
    	return this.byteSize;
    }

    public void readToBuffer(ArrayJ arrayj, ByteBuffer buffer) {
    	this.readFunction.read(arrayj, bufferConverter.apply(buffer), 0);
    }

    public void writeToBuffer(ArrayJ arrayj, ByteBuffer buffer) {
    	this.writeFunction.write(arrayj, bufferConverter.apply(buffer), 0);
    }

    public ArrayJ makeEmptyArrayJ(DeviceJ device, long[] dims, String memoryType) {
    	 return this.makeFunction.make(device, dims, memoryType);
    }

    public ArrayJ makeAndWriteArrayJ(Object buffer, DeviceJ device, long[] dims, String memoryType) {
    	 ArrayJ arrayj = this.makeFunction.make(device, dims, memoryType);
    	 this.writeFunction.write(arrayj, buffer, 0);
    	 return arrayj;

    }

    @FunctionalInterface
    private interface MemoryReadFunction {
        void read(ArrayJ array, Buffer buffer, long offset);
    }

    @FunctionalInterface
    private interface MemoryWriteFunction {
        void write(ArrayJ array, Object buffer, long offset);
    }

    @FunctionalInterface
    private interface MemoryMakeFunction {
        ArrayJ make(DeviceJ device, long[] dims, String memoryType);
    }
}
