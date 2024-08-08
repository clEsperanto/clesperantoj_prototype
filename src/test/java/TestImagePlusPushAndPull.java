import org.junit.jupiter.api.Test;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.clesperanto.imagej.ImageJConverters;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

public class TestImagePlusPushAndPull {

    @Test
    public void testImagePlusPushAndPull() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 32);

    	int[] flatVals = new int[18];
    	for (int i = 0; i < flatVals.length; i ++)
    		flatVals[i] = i;
    	int c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
    				inpIp.putPixelValue(x, y, flatVals[c ++]);
            	}
        	}
    	}

    	
    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImageJConverters.copyImagePlus2ToArrayJ(inputImp, device, "buffer");
    	ImagePlus outputImp = ImageJConverters.copyArrayJToImagePlus(in);
    	

    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }
}