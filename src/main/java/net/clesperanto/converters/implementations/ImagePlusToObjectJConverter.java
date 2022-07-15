package net.clesperanto.converters.implementations;

import ij.ImagePlus;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.converters.AbstractConverter;
import net.clesperanto.converters.ConverterPlugin;
import org.scijava.plugin.Plugin;

@Plugin(type = ConverterPlugin.class)
public class ImagePlusToObjectJConverter extends AbstractConverter<ImagePlus, clesperantojWrapper.ObjectJ> {

    final int THIRD_DIMENSION_Z = 0;
    final int THIRD_DIMENSION_T = 1;
    final int THIRD_DIMENSION_C = 2;
    final int THIRD_DIMENSION_NONE = -1;

    private void setThirdDimension(ImagePlus imp, int thirdDimension, int value) {
        if (thirdDimension == THIRD_DIMENSION_Z) {
            imp.setZ(value);
        } else if (thirdDimension == THIRD_DIMENSION_C) {
            imp.setC(value);
        } else if (thirdDimension == THIRD_DIMENSION_T) {
            imp.setT(value);
        }
    }

    private int getThirdDimension(ImagePlus imp, int thirdDimension) {
        if (thirdDimension == THIRD_DIMENSION_Z) {
            return imp.getZ();
        } else if (thirdDimension == THIRD_DIMENSION_C) {
            return imp.getC();
        } else if (thirdDimension == THIRD_DIMENSION_T) {
            return imp.getT();
        }
        return imp.getZ();
    }

    @Override
    public clesperantojWrapper.ObjectJ convert(ImagePlus source) {
        long[] dimensions = null; // = new long[source.getNSlices() == 1?2:3];
        int thirdDimension;
        // check what's the third dimension
        if (source.getNSlices() > 1) {
            dimensions = new long[]{source.getWidth(), source.getHeight(), source.getNSlices()};
            thirdDimension = THIRD_DIMENSION_Z;
        } else if (source.getNChannels() > 1) {
            dimensions = new long[]{source.getWidth(), source.getHeight(), source.getNChannels()};
            thirdDimension = THIRD_DIMENSION_C;
        } else if (source.getNFrames() > 1) {
            dimensions = new long[]{source.getWidth(), source.getHeight(), source.getNFrames()};
            thirdDimension = THIRD_DIMENSION_T;
        } else {
            dimensions = new long[]{source.getWidth(), source.getHeight(), 1};
            thirdDimension = THIRD_DIMENSION_NONE;
        }

        int numberOfPixelsPerSlice = (int)(dimensions[0] * dimensions[1]);
        long numberOfPixels = numberOfPixelsPerSlice;
        if (thirdDimension != THIRD_DIMENSION_NONE) {
            numberOfPixels = numberOfPixels * dimensions[2];
        }

        int thirdDimensionBefore = getThirdDimension(source, thirdDimension);
        long max_z = dimensions[2];

        if (source.getBitDepth() == 8) {
            byte[] inputArray = new byte[(int) numberOfPixels];
            for (int z = 0; z < max_z; z++) {
                setThirdDimension(source, thirdDimension, z + 1);

                byte[] sourceArray = (byte[])(source.getProcessor().getPixels());
                System.arraycopy(sourceArray, 0, inputArray, z * numberOfPixelsPerSlice, sourceArray.length);
            }
            clesperantojWrapper.ObjectJ target = cle._native.CharPush(inputArray, (int)dimensions[0], (int)dimensions[1], (int)dimensions[2]);
            setThirdDimension(source, thirdDimension, thirdDimensionBefore);
            return target;

        } else if (source.getBitDepth() == 16) {
            short[] inputArray = new short[(int) numberOfPixels];
            for (int z = 0; z < max_z; z++) {
                setThirdDimension(source, thirdDimension, z + 1);

                short[] sourceArray = (short[])(source.getProcessor().getPixels());
                System.arraycopy(sourceArray, 0, inputArray, z * numberOfPixelsPerSlice, sourceArray.length);
            }
            clesperantojWrapper.ObjectJ target = cle._native.ShortPush(inputArray, (int)dimensions[0], (int)dimensions[1], (int)dimensions[2]);
            setThirdDimension(source, thirdDimension, thirdDimensionBefore);
            return target;
        } else if (source.getBitDepth() == 32) {
            float[] inputArray = new float[(int) numberOfPixels];
            for (int z = 0; z < max_z; z++) {
                setThirdDimension(source, thirdDimension, z + 1);

                float[] sourceArray = (float[])(source.getProcessor().getPixels());
                System.arraycopy(sourceArray, 0, inputArray, z * numberOfPixelsPerSlice, sourceArray.length);
            }
            clesperantojWrapper.ObjectJ target = cle._native.FloatPush(inputArray, (int)dimensions[0], (int)dimensions[1], (int)dimensions[2]);
            setThirdDimension(source, thirdDimension, thirdDimensionBefore);
            return target;
        } else {
            throw new RuntimeException("Type not supported: " + source.getBitDepth());
        }
    }

    @Override
    public Class<ImagePlus> getSourceType() {
        return ImagePlus.class;
    }

    @Override
    public Class<clesperantojWrapper.ObjectJ> getTargetType() {
        return clesperantojWrapper.ObjectJ.class;
    }
}
