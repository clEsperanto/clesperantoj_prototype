import org.junit.jupiter.api.Test;

import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
import net.clesperanto.icy.IcyConverters;
import net.clesperanto.kernels.Tier1;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

public class TestAbsoluteIcySequence {

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
		in = null;
		out = null;
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
		in = null;
		out = null;
    }

    private static Sequence createSequence(long[] dims, int[] data)
    {
    	IcyConverters.initIcyPreferences();
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
