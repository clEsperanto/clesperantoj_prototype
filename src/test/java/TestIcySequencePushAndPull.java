import org.junit.jupiter.api.Test;

import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
import net.clesperanto.icy.IcyConverters;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

public class TestIcySequencePushAndPull {

    @Test
    public void testIcySequencePushAndPull() {
    	int[] flatVals = new int[18];
    	for (int i = 0; i < flatVals.length; i ++)
    		flatVals[i] = i;
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, flatVals);


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

        for (int y = 0; y < 3; y ++) {
            for (int x = 0; x < 3; x ++) {
                for (int z = 0; z < 2; z ++) {
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    private static Sequence createSequence(long[] dims, int[] data)
    {
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
        	for (int y = 0; x < dims[1]; y ++) {
            	cursor.set(x, y, 0, 0, 0, data[c ++]);
            }
        }
        cursor.commitChanges();
        return seq;
    }
}
