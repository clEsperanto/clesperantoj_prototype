package net.clesperanto.imglib2;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.function.Function;
import java.util.function.Supplier;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;

public enum DataType {
    FLOAT32("float", 4, float.class, FloatType::new, ByteBuffer::asFloatBuffer,
            (array, buffer, offset) -> MemoryJ.readFloatBuffer(array, (FloatBuffer)buffer, offset), 
            (device, dims, memType) -> MemoryJ.makeFloatBuffer(device, dims, memType), 
            (array, buffer, offset) -> MemoryJ.writeFloatBuffer(array, (float[])buffer, offset) 
            ),
    INT32("int", 4, int.class, IntType::new, ByteBuffer::asIntBuffer,
            (array, buffer, offset) -> MemoryJ.readIntBuffer(array, (IntBuffer)buffer, offset), 
            (device, dims, memType) -> MemoryJ.makeIntBuffer(device, dims, memType), 
            (array, buffer, offset) -> MemoryJ.writeIntBuffer(array, (int[])buffer, offset)
            ),
    UINT32("uint", 4, int.class, UnsignedIntType::new, ByteBuffer::asIntBuffer,
            (array, buffer, offset) -> MemoryJ.readUIntBuffer(array, (IntBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUIntBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUIntBuffer(array, (int[])buffer, offset)
            ),
    INT16("short", 2, short.class, ShortType::new, ByteBuffer::asShortBuffer,
            (array, buffer, offset) -> MemoryJ.readShortBuffer(array, (ShortBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeShortBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeShortBuffer(array, (short[])buffer, offset)
            ),
    UINT16("ushort", 2, short.class, UnsignedShortType::new, ByteBuffer::asShortBuffer,
            (array, buffer, offset) -> MemoryJ.readUShortBuffer(array, (ShortBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUShortBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUShortBuffer(array, (short[])buffer, offset)
            ),
    INT8("char", 1, byte.class, ByteType::new, b -> b,
            (array, buffer, offset) -> MemoryJ.readByteBuffer(array, (ByteBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeByteBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeByteBuffer(array, (byte[])buffer, offset)
            ),
    UINT8("uchar", 1, byte.class, UnsignedByteType::new, b -> b,
            (array, buffer, offset) -> MemoryJ.readUByteBuffer(array, (ByteBuffer)buffer, offset),
            (device, dims, memType) -> MemoryJ.makeUByteBuffer(device, dims, memType),
            (array, buffer, offset) -> MemoryJ.writeUByteBuffer(array, (byte[])buffer, offset)
            );

    private final String name;
    private final int byteSize;
    private final Class<?> arrayClass;
    private final Supplier<NativeType<?>> typeSupplier;
    private final MemoryReadFunction readFunction;
    private final MemoryWriteFunction writeFunction;
    private final MemoryMakeFunction makeFunction;
    private final Function<ByteBuffer, Buffer> bufferConverter;

    DataType(String name, int byteSize, Class<?> arrayClass, Supplier<NativeType<?>> typeSupplier,  Function<ByteBuffer, Buffer> bufferConverter,
    		 MemoryReadFunction readFunction, MemoryMakeFunction makeFunction, MemoryWriteFunction writeFunction) {
        this.name = name;
        this.byteSize = byteSize;
        this.arrayClass = arrayClass;
        this.typeSupplier = typeSupplier;
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

    public static < T extends NativeType< T > > DataType fromImgLib2DataType(T dType) {
        for (DataType type : values()) {
            if (type.typeSupplier.get().getClass().isInstance(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
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

    public < T extends NativeType< T > > T createType() {
        return (T) typeSupplier.get();
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
