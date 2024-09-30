package net.clesperanto.imagej;

import java.nio.ByteBuffer;

import ij.ImagePlus;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;

public enum ImageJDataType {
    FLOAT32(DataType.FLOAT32, ImagePlus.GRAY32, float[].class, 32),
    INT32(DataType.INT32, ImagePlus.GRAY32, int[].class, 32),
    UINT32(DataType.UINT32, ImagePlus.GRAY32, int[].class, 32),
    UINT16(DataType.UINT16, ImagePlus.GRAY16, short[].class, 16),
    INT16(DataType.INT16, ImagePlus.GRAY16, short[].class, 16),
    UINT8(DataType.UINT8, ImagePlus.GRAY8, byte[].class, 8),
    INT8(DataType.INT8, ImagePlus.GRAY8, byte[].class, 8);

	private final DataType dt;
    private final int imgDtype;
    private final int bitDepth;
    private final Class<?> arrayClass;

    ImageJDataType(DataType dt, int imgDtype, Class<?> arrayClass, int bitDepth) {
        this.dt = dt;
        this.imgDtype = imgDtype;
        this.arrayClass = arrayClass;
        this.bitDepth = bitDepth;
    }

    public static ImageJDataType fromImagePlus(ImagePlus imp) {
        return fromImgPlusDataType(imp.getType());
    }

    public static ImageJDataType fromString(String dType) {
        for (ImageJDataType type : values()) {
            if (type.dt.getName().equals(dType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported data type: " + dType);
    }

    public String getName() {
    	return dt.getName();
    }

    public static ImageJDataType fromImgPlusDataType(int dType) {
        for (ImageJDataType type : values()) {
            if (dType == type.imgDtype) {
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

    public int getBitDepth() {
    	return this.bitDepth;
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

    public int createType() {
        return this.getByteSize() * 8;
    }

    public void putValInArray(Object arr, int pos, Number value) {
    	if (!arrayClass.isInstance(arr))
            throw new IllegalArgumentException("The input argument 'arr' is not an instance of " + arrayClass.getSimpleName() + ".");

        switch (this) {
            case FLOAT32:
                ((float[]) arr)[pos] = value.floatValue();
                break;
            case UINT16:
                ((short[]) arr)[pos] = value.shortValue();
                break;
            case UINT8:
                ((byte[]) arr)[pos] = value.byteValue();
                break;
            default:
            	throw new IllegalArgumentException("Unsupported data type");
        }
    }
}
