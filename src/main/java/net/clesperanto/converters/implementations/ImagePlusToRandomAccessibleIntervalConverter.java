package net.clesperanto.converters.implementations;

import ij.ImagePlus;
import net.clesperanto.converters.AbstractConverter;
import net.clesperanto.converters.ConverterPlugin;
import net.haesleinhuepf.clij.converters.AbstractCLIJConverter;
import net.haesleinhuepf.clij.converters.CLIJConverterPlugin;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.display.imagej.ImageJFunctions;
import org.scijava.plugin.Plugin;

@Plugin(type = ConverterPlugin.class)
public class ImagePlusToRandomAccessibleIntervalConverter extends AbstractConverter<ImagePlus, RandomAccessibleInterval> {

    @Override
    public RandomAccessibleInterval convert(ImagePlus source) {
        return ImageJFunctions.wrapReal(source);
    }

    @Override
    public Class<ImagePlus> getSourceType() {
        return ImagePlus.class;
    }

    @Override
    public Class<RandomAccessibleInterval> getTargetType() {
        return RandomAccessibleInterval.class;
    }
}
