package net.clesperanto.imglib2;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;

public enum ImgLib2DataType {
    FLOAT32(DataType.FLOAT32, FloatType::new),
    INT32(DataType.INT32, IntType::new),
    UINT32(DataType.UINT32, UnsignedIntType::new),
    INT16(DataType.INT16, ShortType::new),
    UINT16(DataType.UINT16, UnsignedShortType::new),
    INT8(DataType.INT8, ByteType::new),
    UINT8(DataType.UINT8, UnsignedByteType::new);

    private final DataType dt;
    private final Supplier<NativeType<?>> typeSupplier;
    private final NativeType<?> type;

    ImgLib2DataType(DataType dt, Supplier<NativeType<?>> typeSupplier) {
    	this.dt = dt;
        this.typeSupplier = typeSupplier;
        this.type = typeSupplier.get();
    }

    public static ImgLib2DataType fromString(String dType) {
        for (ImgLib2DataType type : values()) {
            if (type.dt.getName().equals(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
    }

    public String getName() {
    	return dt.getName();
    }

    public static < T extends NativeType< T > > ImgLib2DataType fromImgLib2DataType(T dType) {
        for (ImgLib2DataType type : values()) {
            if (type.type.getClass().isInstance(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
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

    public ArrayJ makeEmptyArrayJ(DeviceJ device, long[] dims, String memoryType) {
    	return dt.makeEmptyArrayJ(device, dims, memoryType);
    }

    public ArrayJ makeAndWriteArrayJ(Object buffer, DeviceJ device, long[] dims, String memoryType) {
    	return dt.makeAndWriteArrayJ(buffer, device, dims, memoryType);
    }

    public < T extends NativeType< T > > T createType() {
        return (T) typeSupplier.get();
    }
}
