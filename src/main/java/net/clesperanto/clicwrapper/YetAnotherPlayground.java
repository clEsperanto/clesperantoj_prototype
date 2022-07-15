package net.clesperanto.clicwrapper;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import net.clesperanto.ClesperantoJ;
import net.clesperanto.pyclesperanto;

public class YetAnotherPlayground {
    public static void main(String[] args) {

        //clesperantojWrapper.ClesperantoJInternal clesperantoJ = new clesperantojWrapper.ClesperantoJInternal();

        // sanity test
        //clesperantoJ.sayHello();

        new ImageJ();

        ImagePlus imp = IJ.openImage("./imgs/boats.tif");
        imp.show();

        ClesperantoJ cle = pyclesperanto.cle;
        clesperantojWrapper.ObjectJ output = cle.gaussian_blur(imp, null, 3, 3,0);
        cle.imshow(output);

        //ImagePlus result = cle.pull(output);
        //result.show();
    }
}