import org.junit.jupiter.api.Test;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.clesperanto.imagej.ImageJConverters;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestImagePlusPushAndPull {
	
	public final static int MAX_UINT16 = 65536;
	
	public final static int MAX_UINT8 = 256;

    @Test
    public void testImagePlusPushAndPullFloat() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 32);

    	int[] flatVals = new int[18];
        Random random = new Random();
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = random.nextInt(100);

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

    @Test
    public void testImagePlusPushAndPullInt() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 32);
    	
    	int[] flatVals = new int[18];
        Random random = new Random();
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = random.nextInt(100);
        
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

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
                	assertEquals(flatVals[c ++], outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }

    @Test
    public void testImagePlusPushAndPullUint() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 32);

    	long[] flatVals = new long[18];
        Random random = new Random();
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (long) Integer.MAX_VALUE + (long) random.nextInt(100);
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

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
                	assertEquals(flatVals[c ++], outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }

    @Test
    public void testImagePlusPushAndPullShort() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 16);

    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);;
    	int c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
    				inpIp.putPixelValue(x, y, flatVals[c] < 0 ? MAX_UINT16 + flatVals[c]: flatVals[c]);
    				c ++;
            	}
        	}
    	}


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImageJConverters.copyImagePlus2ToArrayJ(inputImp, device, "buffer");
    	ImagePlus outputImp = ImageJConverters.copyArrayJToImagePlus(in);

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
    				float outVal = outIp.getPixelValue(x, y);
                	assertEquals(inpIp.getPixelValue(x, y), outVal);
                	assertEquals(flatVals[c ++], (short) outVal);
            	}
        	}
    	}
    }

    @Test
    public void testImagePlusPushAndPullUshort() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 16);

    	int[] flatVals = new int[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = ThreadLocalRandom.current().nextInt(0, MAX_UINT16);
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

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
                	assertEquals(flatVals[c ++], outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }

    @Test
    public void testImagePlusPushAndPullByte() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 8);

    	byte[] flatVals = new byte[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE + 1);
    	int c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
    				inpIp.putPixelValue(x, y, flatVals[c] < 0 ? MAX_UINT8 + flatVals[c] : flatVals[c]);
    				c ++;
            	}
        	}
    	}


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImageJConverters.copyImagePlus2ToArrayJ(inputImp, device, "buffer");
    	ImagePlus outputImp = ImageJConverters.copyArrayJToImagePlus(in);

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
                	assertEquals(flatVals[c ++], (byte) outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }

    @Test
    public void testImagePlusPushAndPullUbyte() {
    	ImagePlus inputImp = IJ.createImage("input", 3, 3, 2, 8);

    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(0, MAX_UINT8);
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

    	c = 0;
    	for (int z = 0; z < 2; z ++) {
    		inputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor inpIp = inputImp.getProcessor();
        	outputImp.setPositionWithoutUpdate(1, 1 + z, 1);
        	ImageProcessor outIp = outputImp.getProcessor();
    		for (int y = 0; y < 3; y ++) {
    			for (int x = 0; x < 3; x ++) {
                	assertEquals(inpIp.getPixelValue(x, y), outIp.getPixelValue(x, y));
                	assertEquals(flatVals[c ++], outIp.getPixelValue(x, y));
            	}
        	}
    	}
    }
}
