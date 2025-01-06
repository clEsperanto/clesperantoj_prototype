/*-
 * #%L
 * Java wrapper for Clesperanto
 * %%
 * Copyright (C) 2022 - 2025 Robert Haase, MPI CBG and Stephane Rigaud, Institut Pasteur
 * %%
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the PoL, TU Dresden nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
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

package net.clesperanto.imglib2;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DataType;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryType;
import net.imglib2.Dimensions;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.blocks.PrimitiveBlocks;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImg;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.basictypeaccess.array.ArrayDataAccess;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.integer.*;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.util.Intervals;
import net.imglib2.util.Util;

import java.util.Arrays;

/**
 * TODO
 * - define whether the buffers follow big or little endian and whether the copies are c- or fortran order
 * - using array img and blocks copy limits to images whose array size is smaller than the integer max
 */
/**
 * Class to copy {@link RandomAccessibleInterval}s into {@link ArrayJ}s and vice-versa
 */
public class ImgLib2Converters {

	/** TODO extend to RandomAccessibleInterval
	 * Conert an {@link ArrayJ} into an ImgLib2 {@link ArrayImg} of the same dimensions and data type.
	 * Creates a copy of the ArrayJ in the GPU into an ArrayImg in the CPU
	 *
	 * @param <T>
	 * 	data type of the ImgLib2 ArrayImg
	 * @param arrayj
	 * 	array that is located in the GPU for clesperanto to do some operations
	 * @return and ImgLib2 {@link ArrayImg} on the CPU copied from the {@link ArrayJ} on the GPU
	 */
	public static < T extends NativeType< T > > Img< T > copyArrayJToImgLib2(ArrayJ arrayj )
	{
		final long[] dims = new long[arrayj.numDimensions()];
		dims[0] = arrayj.width();
		if (dims.length > 1) dims[1] = arrayj.height();
		if (dims.length > 2) dims[2] = arrayj.depth();

		final T type = nativeType(arrayj.dataType());
		final ArrayImg<T, ?> img = new ArrayImgFactory<>(type).create(dims);
		final Object data = ((ArrayDataAccess<?>) img.update(null)).getCurrentStorageArray();
		arrayj.readToArray(data);
		return img;
	}

	/**
	 * Copy a {@link RandomAccessibleInterval} on the CPU into an {@link ArrayJ} on the device (GPU) of interest.
	 * The {@link RandomAccessibleInterval} should have at most 3 dimensions, and the order of the dimensions
	 * should be [width, height, depth]
	 *
	 *
	 * @param <T>
	 * 	the ImgLib2 data type of the {@link RandomAccessibleInterval}
	 * @param rai
	 *  the {@link RandomAccessibleInterval} that is going to be copied into the GPU
	 * @param device
	 * 	the device into which the rai is going to be copied. If null, the default system device is used.
	 * @param memoryType
	 * 	the type of memory array that we are working with. The options are image or buffer. For image use the
	 * 	String "image", for buffer use "buffer"
	 * @return an {@link ArrayJ} copied from the {@link RandomAccessibleInterval} of the CPU
	 */
	public static <T extends NativeType<T>>
	ArrayJ copyImgLib2ToArrayJ(RandomAccessibleInterval<T> rai, DeviceJ device, MemoryType memoryType) {

		final int n = rai.numDimensions();
		final int[] size = checkSize(rai);
		final DataType dataType = dataType(rai.getType());

        final Object data = dataType.memory().createArray((int) Intervals.numElements(size));
		PrimitiveBlocks.of(rai).copy(new int[n], data, size);

		final ArrayJ arrayJ = device.createArray(dataType, memoryType, size);
		arrayJ.writeFromArray(data);
		return arrayJ;
	}

	private static int[] checkSize(Dimensions dimensions) {
		final int n = dimensions.numDimensions();
		if (n > 3)
			throw new IllegalArgumentException("No more than 3 dimensions supported.");
		if (Intervals.numElements(dimensions) > Integer.MAX_VALUE)
			throw new IllegalArgumentException("Too many elements.");
		final int[] dims = new int[n];
		Arrays.setAll(dims, d -> Util.safeInt(dimensions.dimension(d)));
		return dims;
	}

	static <T extends NativeType<T>> DataType dataType(final T type) {
		if (type instanceof FloatType)
			return DataType.FLOAT32;
		else if (type instanceof IntType)
			return DataType.INT32;
		else if (type instanceof UnsignedIntType)
			return DataType.UINT32;
		else if (type instanceof ShortType)
			return DataType.INT16;
		else if (type instanceof UnsignedShortType)
			return DataType.UINT16;
		else if (type instanceof ByteType)
			return DataType.INT8;
		else if (type instanceof UnsignedByteType)
			return DataType.UINT8;
		else
			throw new IllegalArgumentException("Type not supported: " + type.getClass().getName());
	}

	@SuppressWarnings("unchecked")
	static <T extends NativeType<T>> T nativeType(final DataType dataType) {

		switch (dataType) {
			case INT8:
				return (T) new ByteType();
			case UINT8:
				return (T) new UnsignedByteType();
			case INT16:
				return (T) new ShortType();
			case UINT16:
				return (T) new UnsignedShortType();
			case INT32:
				return (T) new IntType();
			case UINT32:
				return (T) new UnsignedIntType();
			case FLOAT32:
				return (T) new FloatType();
			default:
				throw new IllegalArgumentException();
		}
	}
}
