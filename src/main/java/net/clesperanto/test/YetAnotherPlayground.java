package net.clesperanto.test;

import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import net.clesperanto.ClesperantoJ;
import net.clesperanto.clicwrapper.clesperantojWrapper;
import net.clesperanto.pyclesperanto;

public class YetAnotherPlayground {
    public static void main(String[] args) {

        new ImageJ();

        ImagePlus imp = IJ.openImage("./imgs/boats.tif");
        IJ.run(imp, "32-bit", "");
        imp.show();

        ClesperantoJ cle = pyclesperanto.cle;
        clesperantojWrapper.ObjectJ output = cle.gaussian_blur(imp, null, 3, 3,0);
        cle.imshow(output);

    }
}
