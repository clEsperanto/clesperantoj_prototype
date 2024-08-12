import org.junit.jupiter.api.Test;

import icy.image.IcyBufferedImage;
import icy.preferences.IcyPreferences;
import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
import net.clesperanto.icy.IcyConverters;
import net.clesperanto.kernels.Tier1;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

public class TestAbsolute {

    @Test
    public void testAbsolute() {
    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeIntBuffer(device, 2, 2, 0, 2, "buffer");
    	in.fillMemory(-1);
    	ArrayJ out = MemoryJ.makeIntBuffer(device, 2, 2, 0, 2, "buffer");
    	out.fillMemory(-1);
        Tier1.absolute(device, in, out);

        int[] result = new int[4];
        IntBuffer resultBuff = IntBuffer.wrap(result);
        MemoryJ.readIntBuffer(out, resultBuff, 4);

        assertEquals(1, Arrays.stream(result).min().getAsInt());
        assertEquals(1, Arrays.stream(result).max().getAsInt());
        assertEquals(1, Arrays.stream(result).average().getAsDouble());
    }

    @Test
    public void testAbsolute1() {
    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeIntBuffer(device, 2, 2, 0, 2, "buffer");
    	in.fillMemory(-1);
        ArrayJ out = Tier1.absolute(device, in, null);

        int[] result = new int[4];
        IntBuffer resultBuff = IntBuffer.wrap(result);
        MemoryJ.readIntBuffer(out, resultBuff, 4);

        for (int val : result)
        	assertEquals(1, val);
    }

    @Test
    public void testAbsoluteImgLib2() {
    	int[] flatVals = {1, 1, -1, -1};
    	RandomAccessibleInterval<IntType> inputImg = ArrayImgs.ints(flatVals, new long[] {2, 2});
    	RandomAccessibleInterval<IntType> outputImg = ArrayImgs.ints(flatVals, new long[] {2, 2});


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImgLib2Converters.copyImgLib2ToArrayJ(inputImg, device, "buffer");
    	ArrayJ out = ImgLib2Converters.copyImgLib2ToArrayJ(outputImg, device, "buffer");

    	Tier1.absolute(device, in, out);

    	outputImg = ImgLib2Converters.copyArrayJToImgLib2(out);


    	double min = outputImg.firstElement().getRealDouble();
		double max = min;
		double mean = 0;

		for (IntType px : outputImg) {
			double val = px.getRealDouble();
			mean += val / 4;
			min = Math.min(min,val);
			max = Math.max(max,val);
		}

        assertEquals(1, min);
        assertEquals(1, max);
        assertEquals(1, mean);
    }

    @Test
    public void testAbsolute1ImgLib2() {
    	int[] flatVals = {1, 1, -1, -1};
    	RandomAccessibleInterval<IntType> inputImg = ArrayImgs.ints(flatVals, new long[] {2, 2});


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImgLib2Converters.copyImgLib2ToArrayJ(inputImg, device, "buffer");

    	ArrayJ out = Tier1.absolute(device, in, null);

    	RandomAccessibleInterval<IntType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(out);


		for (IntType px : outputImg) {
			double val = px.getRealDouble();
        	assertEquals(1, val);
		}
    }

    @Test
    public void testAbsoluteImagePlus() {
    	ImagePlus inputImp = IJ.createImage("input", 2, 2, 1, 32);
    	ImagePlus outputImp = IJ.createImage("input", 2, 2, 1, 32);
    	ImageProcessor inpIp = inputImp.getProcessor();
    	ImageProcessor outIp = outputImp.getProcessor();

    	int[] vals = {-1, -1, 1, 1};
    	int c = 0;
    	for (int x = 0; x < 2; x ++) {
    		for (int y = 0; y < 2; y ++) {
    			inpIp.putPixelValue(x, y, vals[c]);
    			outIp.putPixelValue(x, y, vals[c ++]);
        	}
    	}


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImageJConverters.copyImagePlus2ToArrayJ(inputImp, device, "buffer");
    	ArrayJ out = ImageJConverters.copyImagePlus2ToArrayJ(outputImp, device, "buffer");

    	Tier1.absolute(device, in, out);

    	outputImp = ImageJConverters.copyArrayJToImagePlus(out);
    	outIp = outputImp.getProcessor();


    	double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double mean = 0;
    	for (int x = 0; x < 2; x ++) {
    		for (int y = 0; y < 2; y ++) {
    			double val = outIp.getPixelValue(x, y);
    			mean += val / 4;
    			min = Math.min(min,val);
    			max = Math.max(max,val);
        	}
    	}

        assertEquals(1, min);
        assertEquals(1, max);
        assertEquals(1, mean);
    }

    @Test
    public void testAbsolute1ImagePlus() {
    	ImagePlus inputImp = IJ.createImage("input", 2, 2, 1, 32);
    	ImageProcessor inpIp = inputImp.getProcessor();

    	int[] vals = {-1, -1, 1, 1};
    	int c = 0;
    	for (int x = 0; x < 2; x ++) {
    		for (int y = 0; y < 2; y ++) {
    			inpIp.putPixelValue(x, y, vals[c ++]);
        	}
    	}


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImageJConverters.copyImagePlus2ToArrayJ(inputImp, device, "buffer");

    	ArrayJ out = Tier1.absolute(device, in, null);

    	ImagePlus outputImp = ImageJConverters.copyArrayJToImagePlus(out);
    	ImageProcessor outIp = outputImp.getProcessor();

    	for (int x = 0; x < 2; x ++) {
    		for (int y = 0; y < 2; y ++) {
    			double val = outIp.getPixelValue(x, y);
            	assertEquals(1, val);
        	}
    	}
    }

    @Test
    public void testAbsoluteIcySequence() {
    	int[] data = new int[] {1, 1, -1, -1};
    	Sequence inputSeq = createSequence(new long[] {2, 2}, data);
    	Sequence outputSeq = createSequence(new long[] {2, 2}, data);


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");
    	ArrayJ out = IcyConverters.copySequenceToArrayJ(outputSeq, device, "buffer");

    	Tier1.absolute(device, in, out);

    	outputSeq = IcyConverters.copyArrayJToSequence(out);

    	double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double mean = 0;
		SequenceCursor cursor = new SequenceCursor(outputSeq);
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
            	double val = cursor.get(x, y, 0, 0, 0);
    			mean += val / 4;
    			min = Math.min(min,val);
    			max = Math.max(max,val);
            }
	    }

        assertEquals(1, min);
        assertEquals(1, max);
        assertEquals(1, mean);
    }

    @Test
    public void testAbsolute1IcySequence() {
    	int[] data = new int[] {1, 1, -1, -1};
    	Sequence inputSeq = createSequence(new long[] {2, 2}, data);


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	ArrayJ out = Tier1.absolute(device, in, null);

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(out);

		SequenceCursor cursor = new SequenceCursor(outputSeq);
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
            	double val = cursor.get(x, y, 0, 0, 0);
            	assertEquals(1, val);
            }
	    }
    }

    private static Sequence createSequence(long[] dims, int[] data)
    {
    	IcyPreferences.init();
    	while (dims.length < 3) {
    		long[] newArray = new long[dims.length + 1];
    	    System.arraycopy(dims, 0, newArray, 0, dims.length);
    	    newArray[dims.length] = 1;
    	    dims = newArray;
    	}
        Sequence seq = new Sequence();
        int z;
        for (z = 0; z < dims[2]; z++)
        {
            seq.setImage(0, z, new IcyBufferedImage((int) dims[0], (int) dims[1], 1, icy.type.DataType.INT));
        }

        SequenceCursor cursor = new SequenceCursor(seq);
        int c = 0;
        for (int x = 0; x < dims[0]; x ++) {
        	for (int y = 0; y < dims[1]; y ++) {
            	cursor.set(x, y, 0, 0, 0, data[c ++]);
            }
        }
        cursor.commitChanges();
        return seq;
    }
}
