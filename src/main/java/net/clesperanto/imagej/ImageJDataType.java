package net.clesperanto.imagej;

import java.nio.ByteBuffer;

import ij.ImagePlus;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;
// TODO add all types ImagePlus.GRAY8, ImagePlus.GRAY16, ImagePlus.GRAY32, ImagePlus.COLOR_256 or ImagePlus.COLOR_RGB)
public enum ImageJDataType {
    FLOAT32(DataType.fromString("float"), ImagePlus.GRAY32, float[].class),
    UINT16(DataType.fromString("ushort"), ImagePlus.GRAY16, short[].class),
    UINT8(DataType.fromString("uchar"), ImagePlus.GRAY8, byte[].class);

	private final DataType dt;
    private final int imgDtype;
    private final Class<?> arrayClass;

    ImageJDataType(DataType dt, int imgDtype, Class<?> arrayClass) {
        this.dt = dt;
        this.imgDtype = imgDtype;
        this.arrayClass = arrayClass;
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
        return this.imgDtype;
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
