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

public class ImagePlusClesperantoJExample {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");
        System.out.println(System.getProperty("java.library.path"));

        DeviceJ currentDevice = DeviceJ.getDefaultDevice();

        // create a image with 3x3x2 pixels in imglib2
        float data[] = new float[10 * 10 * 1];
        float out[] = new float[10 * 10 * 1];
        Arrays.fill(data, -5);
        Arrays.fill(out, -1);

        // set middle pixel to 15
        data[25] = 15;

        ImagePlus input_imp = IJ.createImage("Test", 10, 10, 1, 32);
        input_imp.getProcessor().setPixels(data);

        new ImageJ();
        IJ.run(input_imp, "32-bit", "");
        input_imp.show();

        ArrayJ input = ImageJConverters.copyImgLib2ToArrayJ(input_imp, currentDevice, "buffer");
        // ArrayJ output = ImgLib2Converters.copyImgLib2ToArrayJ(output_img,
        // currentDevice, "buffer");

        // ArrayJ output = Tier1.absolute(currentDevice, input, null);

        ImagePlus output_imp = ImageJConverters.copyArrayJToImgLib2(input);
        output_imp.show();

        // print out each pixel value of the output image
        // float[] output_pixels = (float[]) output_imp.getProcessor().getPixels();
        // for (int i = 0; i < output_pixels.length; i++) {
        // System.out.println("out[" + i + "] = " + output_pixels[i]);
        // }

        // Float sum = Tier2.sumOfAllPixels(currentDevice, output);
        // System.out.println("Sum of all pixels: " + sum);
    }

}
