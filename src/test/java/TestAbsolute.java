import org.junit.jupiter.api.Test;

import net.clesperanto.kernels.Tier1;
import net.clesperanto.core.MemoryJ;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.IntBuffer;
import java.util.Arrays;

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
		in = null;
		out = null;
    }
}
