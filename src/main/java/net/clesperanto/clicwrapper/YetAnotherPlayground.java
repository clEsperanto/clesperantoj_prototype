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

        ClesperantoJ cle = pyclesperanto.cle;

        imp.show();
        clesperantojWrapper.ObjectJ input = cle.push(imp);

        ImagePlus result = cle.pull(input);
        result.show();
    }
}
