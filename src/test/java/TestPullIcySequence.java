/*
 * Copyright 2024 Stéphane Rigaud, Robert Haase, Institut Pasteur Paris,
 * Max Planck Institute for Molecular Cell Biology and Genetics Dresden,
 * ScaDS.AI, Leipzig University
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

import org.junit.jupiter.api.Test;

import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.clesperanto.icy.IcyConverters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;


public class TestPullIcySequence {

	static {
    	IcyConverters.initIcyPreferences();
	}

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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		long val = flatVals[c ++];
            		assertEquals(val < 0 ? MAX_UINT32 + val : val, outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		long val = flatVals[c ++];
            		assertEquals(val, outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		long val = flatVals[c ++];
            		assertEquals(val < 0 ? MAX_UINT16 + val : val, outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		long val = flatVals[c ++];
            		assertEquals(val, outCursor.get(x, y, z, 0, 0));
                }
            }
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
    	Sequence outputImg = IcyConverters.copyArrayJToSequence(in);
    	SequenceCursor outCursor = new SequenceCursor(outputImg);

    	int c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
            		long val = flatVals[c ++];
            		assertEquals(val < 0 ? MAX_UINT8 + val : val, outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }
}
