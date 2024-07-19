package net.clesperanto.imagej;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

import ij.ImagePlus;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;
// TODO add all types ImagePlus.GRAY8, ImagePlus.GRAY16, ImagePlus.GRAY32, ImagePlus.COLOR_256 or ImagePlus.COLOR_RGB)
public enum ImageJDataType {
    FLOAT32(DataType.fromString("float"), ImagePlus.GRAY32),
    UINT16(DataType.fromString("ushort"), ImagePlus.GRAY16),
    UINT8(DataType.fromString("uchar"), ImagePlus.GRAY8);

    private final DataType dt;
    private final int imgDtype;

    ImageJDataType(DataType dt, int imgDtype) {
    	this.dt = dt;
        this.imgDtype = imgDtype;
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
            if (type.typeSupplier.get().getClass().isInstance(dType)) {
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

    public < T extends NativeType< T > > T createType() {
        return (T) typeSupplier.get();
    }
}
