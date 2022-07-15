package net.clesperanto.converters.implementations;

import ij.ImagePlus;
import ij.gui.NewImage;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.converters.AbstractConverter;
import net.clesperanto.converters.ConverterPlugin;
import org.scijava.plugin.Plugin;


@Plugin(type = ConverterPlugin.class)
public class ObjectJToImagePlusConverter extends AbstractConverter<clesperantojWrapper.ObjectJ, ImagePlus> {
    @Override
    public ImagePlus convert(clesperantojWrapper.ObjectJ source) {
        int width = (int) source.getWidth();
        int height = (int) source.getHeight();
        int depth = (int) source.getDepth();

        int numberOfPixelsPerPlane = width * height;
        long numberOfPixels = (long)numberOfPixelsPerPlane * (long)depth;

        ImagePlus result = null;
        if (source.getDataType().getString().compareTo("uchar") == 0) {
            result = NewImage.createByteImage("slice", width, height, depth, NewImage.FILL_BLACK);
            byte[] array = new byte[(int) numberOfPixels];
            cle._native.CharPull(array, source);
            for (int z = 0; z < depth; z++) {
                result.setSlice(z + 1);
                byte[] sliceArray = (byte[]) result.getProcessor().getPixels();
                System.arraycopy(array, z * numberOfPixelsPerPlane, sliceArray, 0, sliceArray.length);
            }
        } else if (source.getDataType().getString().compareTo("ushort") == 0) {
            result = NewImage.createShortImage("slice", width, height, depth, NewImage.FILL_BLACK);

            short[] array = new short[(int) numberOfPixels];
            cle._native.ShortPull(array, source);
            for (int z = 0; z < depth; z++) {
                result.setSlice(z + 1);
                short[] sliceArray = (short[]) result.getProcessor().getPixels();
                System.arraycopy(array, z * numberOfPixelsPerPlane, sliceArray, 0, sliceArray.length);
            }
        } else if (source.getDataType().getString().compareTo("float") == 0) {
            result = NewImage.createFloatImage("slice", width, height, depth, NewImage.FILL_BLACK);

            float[] array = new float[(int) numberOfPixels];
            cle._native.FloatPull(array, source);
            for (int z = 0; z < depth; z++) {
                result.setSlice(z + 1);
                float[] sliceArray = (float[]) result.getProcessor().getPixels();
                System.arraycopy(array, z * numberOfPixelsPerPlane, sliceArray, 0, sliceArray.length);
            }
        } else {
            throw new RuntimeException("Type not supported:" + source.getDataType().getString());
        }
        result.resetDisplayRange();
        return result;
    }

    @Override
    public Class<clesperantojWrapper.ObjectJ> getSourceType() {
        return clesperantojWrapper.ObjectJ.class;
    }

    @Override
    public Class<ImagePlus> getTargetType() {
        return ImagePlus.class;
    }
}
