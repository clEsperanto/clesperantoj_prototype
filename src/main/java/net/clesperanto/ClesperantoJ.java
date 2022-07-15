package net.clesperanto;

import ij.ImagePlus;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.converters.ConverterPlugin;
import net.clesperanto.converters.ConverterService;
import net.imglib2.RandomAccessibleInterval;
import org.scijava.Context;

public class ClesperantoJ {

    @Deprecated
    public ClesperantoJ() {

    }

    private static ClesperantoJ instance = null;
    public static ClesperantoJ getInstance() {
        if (instance == null) {
            instance = new ClesperantoJ();
        }
        return instance;
    }

    public clesperantojWrapper.ClesperantoJInternal _native = new clesperantojWrapper.ClesperantoJInternal();

    public void say_hello() {
        _native.sayHello();
    }

    public clesperantojWrapper.ObjectJ push(Object image) {
        return convert(image, clesperantojWrapper.ObjectJ.class);
    }

    public ImagePlus pull(Object image) {
        return convert(image, ImagePlus.class);
    }

    public RandomAccessibleInterval pullRAI(Object image) {
        return convert(image, RandomAccessibleInterval.class);
    }


    private ConverterService converterService = null;

    public <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        if (targetClass.isAssignableFrom(source.getClass())) {
            return (T) source;
        }
        synchronized (this) {
            //try {
                if (converterService == null) {
                    converterService = new Context(ConverterService.class).service(ConverterService.class);
                }
            //} catch (RuntimeException e) {
            //    converterService = FallBackCLIJConverterService.getInstance();
            //}
            converterService.setCLE(this);
            ConverterPlugin<S, T> converter = (ConverterPlugin<S, T>) converterService.getConverter(source.getClass(), targetClass);
            converter.setCLE(this);
            T result = converter.convert(source);

            return  result;
        }
    }

    public void imshow(Object gpu_image) {
        ImagePlus image = pull(gpu_image);
        image.show();
    }

    public clesperantojWrapper.ObjectJ create_like(clesperantojWrapper.ObjectJ source) {
        return _native.FloatCreate(source.getWidth(), source.getHeight(), source.getDepth());
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
        return _native.gaussian_blur(sourceJ, targetJ, sigma_x, sigma_y, sigma_z);
    }
}
