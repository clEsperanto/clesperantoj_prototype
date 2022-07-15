package net.clesperanto.converters.implementations;

import ij.ImagePlus;
import net.clesperanto.converters.AbstractConverter;
import net.clesperanto.converters.ConverterPlugin;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.display.imagej.ImageJFunctions;
import org.scijava.plugin.Plugin;

@Plugin(type = ConverterPlugin.class)
public class RandomAccessibleIntervalToImagePlusConverter extends AbstractConverter<RandomAccessibleInterval, ImagePlus> {

    @Override
    public ImagePlus convert(RandomAccessibleInterval source) {
        return ImageJFunctions.wrap(source, "" + source);
    }

    @Override
    public Class<RandomAccessibleInterval> getSourceType() {
        return RandomAccessibleInterval.class;
    }

    @Override
    public Class<ImagePlus> getTargetType() {
        return ImagePlus.class;
    }
}
