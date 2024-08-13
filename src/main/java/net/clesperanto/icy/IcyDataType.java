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
            case INT32:
                ((int[]) arr)[pos] = value.intValue();
                break;
            case INT16:
            case UINT16:
                ((short[]) arr)[pos] = value.shortValue();
                break;
            case INT8:
            case UINT8:
                ((byte[]) arr)[pos] = value.byteValue();
                break;
            default:
            	throw new IllegalArgumentException("Unsupported data type");
        }
    }
}
