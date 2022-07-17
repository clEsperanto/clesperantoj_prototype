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

        ImagePlus imp = IJ.openImage("./imgs/blobs.tif");
        IJ.run(imp, "32-bit", "");
        imp.show();

        ClesperantoJ cle = pyclesperanto.cle;
        clesperantojWrapper.ObjectJ blurred = cle.gaussian_blur(imp, null, 3, 3,0);
        cle.imshow(blurred);

        clesperantojWrapper.ObjectJ binary = cle.thresold_otsu(blurred, null);
        cle.imshow(binary);

        clesperantojWrapper.ObjectJ output = cle.connected_component_labeling_box(binary, null);
        cle.imshow(output);

        IJ.run("3-3-2 RGB");
    }
}
