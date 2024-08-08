import org.junit.jupiter.api.Test;

import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.integer.IntType;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.imglib2.ImgLib2Converters;

import static org.junit.jupiter.api.Assertions.*;


public class TestImgLibPushAndPull {

    @Test
    public void testImgLibPushAndPull() {
    	int[] flatVals = new int[18];
    	for (int i = 0; i < flatVals.length; i ++)
    		flatVals[i] = i;
    	RandomAccessibleInterval<IntType> inputImg = ArrayImgs.ints(flatVals, new long[] {3, 3, 2});
    	
    	
    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = ImgLib2Converters.copyImgLib2ToArrayJ(inputImg, device, "buffer");
    	RandomAccessibleInterval<IntType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	
    	Cursor<IntType> inCursor = inputImg.cursor();
    	Cursor<IntType> outCursor = outputImg.cursor();
    	
    	while (inCursor.hasNext()) {
    		inCursor.next();
    		outCursor.next();
    		assertEquals(inCursor.get().get(), outCursor.get().get());
    	}
    }
}