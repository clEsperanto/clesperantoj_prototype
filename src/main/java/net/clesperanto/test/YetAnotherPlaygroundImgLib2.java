package net.clesperanto.test;

import ij.IJ;
import ij.ImagePlus;
import net.clesperanto.ClesperantoJ;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.pyclesperanto;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.real.FloatType;

public class YetAnotherPlaygroundImgLib2 {

    final static net.imagej.ImageJ ij = new ImageJ();

    public static void main(String[] args) throws Exception {
        ij.launch(args);

        Dataset dataset = (Dataset) ij.io().open("./imgs/boats.tif");

        Img<FloatType> img = ij.op().convert().float32((Img)dataset);
        ij.ui().show("input", img);

        ClesperantoJ cle = pyclesperanto.cle;
        clesperantojWrapper.ObjectJ output = cle.gaussian_blur(img, null, 3, 3,0);
        //cle.imshow(output);

        RandomAccessibleInterval result = cle.pullRAI(output);
        ij.ui().show("output", result);
    }
}
