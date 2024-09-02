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

import icy.image.IcyBufferedImage;
import icy.sequence.Sequence;
import icy.sequence.SequenceCursor;
import icy.type.DataType;
import net.clesperanto.icy.IcyConverters;
import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

public class TestPushAndPullIcySequence {

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
    public void testIcySequencePushAndPullFloat() {
    	float[] flatVals = new float[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = ThreadLocalRandom.current().nextFloat();
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.FLOAT);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullInt() {
    	int[] flatVals = new int[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = ThreadLocalRandom.current().nextInt();
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.INT);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullUint() {
    	int[] flatVals = new int[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] =  Integer.MAX_VALUE + ThreadLocalRandom.current().nextInt(0, 100);
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.UINT);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
	            	long val = flatVals[c ++];
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(val < 0 ? val + MAX_UINT32 : val, outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullShort() {
    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.SHORT);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullUshort() {
    	short[] flatVals = new short[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (short) ThreadLocalRandom.current().nextInt(0, MAX_UINT16);
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.USHORT);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
	            	int val = flatVals[c ++];
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(val < 0 ? val + MAX_UINT16 : val, outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullByte() {
    	byte[] flatVals = new byte[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE + 1);
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.BYTE);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(flatVals[c ++], outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    @Test
    public void testIcySequencePushAndPullUbyte() {
    	byte[] flatVals = new byte[18];
        for (int i = 0; i < flatVals.length; i++)
        	flatVals[i] = (byte) ThreadLocalRandom.current().nextInt(0, MAX_UINT8);
    	Sequence inputSeq = createSequence(new long[] {3, 3, 2}, DataType.UBYTE);

        SequenceCursor cursor = new SequenceCursor(inputSeq);
        int c = 0;
    	for (int z = 0; z < 2; z ++) {
        	for (int y = 0; y < 3; y ++) {
        		for (int x = 0; x < 3; x ++) {
        			cursor.set(x, y, z, 0, 0, flatVals[c ++]);
        		}
            }
        }
        cursor.commitChanges();


    	DeviceJ device = DeviceJ.getDefaultDevice();
    	ArrayJ in = IcyConverters.copySequenceToArrayJ(inputSeq, device, "buffer");

    	Sequence outputSeq = IcyConverters.copyArrayJToSequence(in);

    	SequenceCursor inCursor = new SequenceCursor(inputSeq);
    	SequenceCursor outCursor = new SequenceCursor(outputSeq);

    	c = 0;
        for (int z = 0; z < 2; z ++) {
	        for (int y = 0; y < 3; y ++) {
	            for (int x = 0; x < 3; x ++) {
	            	short val = flatVals[c ++];
                	assertEquals(inCursor.get(x, y, z, 0, 0), outCursor.get(x, y, z, 0, 0));
                	assertEquals(val < 0 ? val + MAX_UINT8 : val, outCursor.get(x, y, z, 0, 0));
                }
            }
	    }
    }

    private static Sequence createSequence(long[] dims, DataType dt)
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
            seq.setImage(0, z, new IcyBufferedImage((int) dims[0], (int) dims[1], 1, dt));
        }
        return seq;
    }
}
