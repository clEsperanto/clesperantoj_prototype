import org.junit.jupiter.api.Test;

import net.imglib2.Cursor;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.type.numeric.integer.ByteType;
import net.imglib2.type.numeric.integer.IntType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.integer.UnsignedIntType;
import net.imglib2.type.numeric.integer.UnsignedShortType;
import net.imglib2.type.numeric.real.FloatType;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.clesperanto.imglib2.ImgLib2Converters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;


public class TestPullImagePlus {

	public final static long MAX_UINT32 = (long) Math.pow(2, 32);

	public final static int MAX_INT32 = Integer.MAX_VALUE;

	public final static int MAX_UINT16 = 65536;

	public final static int MAX_INT16 = 65536 /  2 - 1;

	public final static int MAX_UINT8 = 256;

	public final static int MAX_INT8 = 256 / 2 - 1;

    @Test
    public void testPullFloat() {

    	float[] flatVals = new float[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = ThreadLocalRandom.current().nextFloat();

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeFloatBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeFloatBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<FloatType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<FloatType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		assertEquals(flatVals[c ++], outCursor.get().get());
    	}
    }

    @Test
    public void testPullInt() {

    	int[] flatVals = new int[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = ThreadLocalRandom.current().nextInt();

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeIntBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeIntBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<IntType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<IntType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		assertEquals(flatVals[c ++], outCursor.get().get());
    	}
    }

    @Test
    public void testPullUint() {

    	int[] flatVals = new int[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] =  Integer.MAX_VALUE + ThreadLocalRandom.current().nextInt(0, 100);

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeUIntBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeUIntBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<UnsignedIntType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<UnsignedIntType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		long val = flatVals[c ++];
    		assertEquals(val < 0 ? MAX_UINT32 + val : val, outCursor.get().get());
    	}
    }

    @Test
    public void testPullShort() {

    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeShortBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeShortBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<ShortType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<ShortType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		short val = flatVals[c ++];
    		assertEquals(val, outCursor.get().get());
    	}
    }

    @Test
    public void testPullUshort() {

    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(0, MAX_UINT16);

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeUShortBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeUShortBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<UnsignedShortType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<UnsignedShortType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		short val = flatVals[c ++];
    		assertEquals(val < 0 ? MAX_UINT16 + val : val, outCursor.get().get());
    	}
    }

    @Test
    public void testPullByte() {

    	byte[] flatVals = new byte[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE + 1);

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeByteBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeByteBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<ByteType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<ByteType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		short val = flatVals[c ++];
    		assertEquals(val, outCursor.get().get());
    	}
    }

    @Test
    public void testPullUbyte() {

    	byte[] flatVals = new byte[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (byte) ThreadLocalRandom.current().nextInt(0, MAX_UINT8);

    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = MemoryJ.makeUByteBuffer(device, new long[] {3, 3, 2}, "buffer");
    	MemoryJ.writeUByteBuffer(in, flatVals, 18);
    	RandomAccessibleInterval<UnsignedByteType> outputImg = ImgLib2Converters.copyArrayJToImgLib2(in);
    	Cursor<UnsignedByteType> outCursor = outputImg.cursor();

    	int c = 0;
    	while (outCursor.hasNext()) {
    		outCursor.next();
    		short val = flatVals[c ++];
    		assertEquals(val < 0 ? MAX_UINT8 + val : val, outCursor.get().get());
    	}
    }
}
