package net.clesperanto;

import ij.ImagePlus;
import net.clesperanto.clicwrapper.ConvertersUtility;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import org.bytedeco.javacpp.FloatPointer;

public class ClesperantoJ {

    private clesperantojWrapper.ClesperantoJInternal clesperantoJ = new clesperantojWrapper.ClesperantoJInternal();

    public clesperantojWrapper.ObjectJ push(Object image) {
        if (image instanceof ImagePlus) {
            image = ImageJFunctions.convertFloat((ImagePlus)image);
        }
        if (image instanceof IterableInterval) {
            IterableInterval img = (IterableInterval) image;

            int nx = (int)img.dimension(0);
            int ny = (int)img.dimension(1);

            FloatPointer fp = ConvertersUtility.ii2DToFloatPointer(img);
            clesperantojWrapper.ObjectJ objIn = clesperantoJ.FloatPush(fp, nx, ny);

            return objIn;
        } else {
            System.out.println("Type not supported" + image.getClass().getName());
        }
        return null;
    }

    public ImagePlus pull(clesperantojWrapper.ObjectJ gpu_image) {
        int width = gpu_image.getWidth();
        int height = gpu_image.getHeight();
        int depth = gpu_image.getDepth();
        FloatPointer outFp = new FloatPointer(width * height * depth);

        clesperantoJ.FloatPull(outFp, gpu_image);
        Img outImFromObj = ConvertersUtility.floatPointerToImg(outFp, width, height, depth);
        ImagePlus result = ImageJFunctions.wrap(outImFromObj, "Output");
        result.resetDisplayRange();
        return result;
    }
}
