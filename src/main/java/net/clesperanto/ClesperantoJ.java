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

    public void say_hello() {
        clesperantoJ.sayHello();
    }

    public clesperantojWrapper.ObjectJ push(Object image) {
        if (image == null) {
            return null;
        }
        if (image instanceof clesperantojWrapper.ObjectJ) {
            return (clesperantojWrapper.ObjectJ) image;
        }
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

    public ImagePlus pull_if_necessary(Object image) {
        if (image == null) {
            return null;
        }
        if (image instanceof ImagePlus) {
            return (ImagePlus) image;
        }
        if (image instanceof clesperantojWrapper.ObjectJ){
            return pull((clesperantojWrapper.ObjectJ) image);
        }
        throw new RuntimeException("Image type not supported: " + image.getClass().getName());
    }

    public void imshow(Object gpu_image) {
        ImagePlus image = pull_if_necessary(gpu_image);
        image.show();
    }

    public clesperantojWrapper.ObjectJ create_like(clesperantojWrapper.ObjectJ source) {
        return clesperantoJ.FloatCreate(source.getWidth(), source.getHeight(), source.getDepth());
    }

    private clesperantojWrapper.ObjectJ create_like_if_none(Object source, Object target) {
        clesperantojWrapper.ObjectJ sourceJ = push(source); // that might be not necessary and slow
        clesperantojWrapper.ObjectJ targetJ = push(target); // that might be not necessary and slow
        if (targetJ != null) {
            return targetJ;
        }
        return create_like(sourceJ);
    }

    public clesperantojWrapper.ObjectJ gaussian_blur(Object source, Object target, float sigma_x, float sigma_y, float sigma_z) {
        clesperantojWrapper.ObjectJ sourceJ = push(source);
        clesperantojWrapper.ObjectJ targetJ = create_like_if_none(sourceJ, target);
        return clesperantoJ.gaussian_blur(sourceJ, targetJ, sigma_x, sigma_y, sigma_z);
    }
}
