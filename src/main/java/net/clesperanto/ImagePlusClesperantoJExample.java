package net.clesperanto;

import java.util.Arrays;

import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.clesperanto.core.BackendJ;
import net.clesperanto.core.ArrayJ;

import net.clesperanto.kernels.Tier1;
import net.clesperanto.imagej.ImageJConverters;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

public class ImagePlusClesperantoJExample {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");
        System.out.println(System.getProperty("java.library.path"));

        DeviceJ currentDevice = DeviceJ.getDefaultDevice();

        ImagePlus input_imp = IJ.createImage("Test", 10, 10, 2, 32);
        ImageProcessor ip = input_imp.getStack().getProcessor(1);
        ip.setf(0, 0, -15.0f);

        new ImageJ();
        IJ.run(input_imp, "32-bit", "");
        input_imp.show();

        ArrayJ input = ImageJConverters.copyImgLib2ToArrayJ(input_imp, currentDevice, "buffer");
        ArrayJ output = Tier1.absolute(currentDevice, input, null);
        ImagePlus output_imp = ImageJConverters.copyArrayJToImgLib2(output);

        output_imp.show();
    }

}
