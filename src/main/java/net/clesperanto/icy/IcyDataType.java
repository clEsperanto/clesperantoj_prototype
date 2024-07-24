package net.clesperanto.icy;

import java.nio.ByteBuffer;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;


public enum IcyDataType {
    FLOAT32(DataType.fromString("float"), icy.type.DataType.FLOAT),
    INT32(DataType.fromString("int"), icy.type.DataType.INT),
    UINT32(DataType.fromString("uint"), icy.type.DataType.UINT),
    INT16(DataType.fromString("short"), icy.type.DataType.SHORT),
    UINT16(DataType.fromString("ushort"), icy.type.DataType.USHORT),
    INT8(DataType.fromString("char"), icy.type.DataType.BYTE),
    UINT8(DataType.fromString("uchar"), icy.type.DataType.UBYTE);

    private final DataType dt;
    private final icy.type.DataType icyDT;

    IcyDataType(DataType dt, icy.type.DataType icyDT) {
    	this.dt = dt;
        this.icyDT = icyDT;
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
}
