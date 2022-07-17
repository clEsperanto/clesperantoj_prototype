package net.clesperanto.deprecated;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.ShortPointer;

import net.imglib2.Cursor;
import net.imglib2.Dimensions;
import net.imglib2.FinalDimensions;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgs;
import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.integer.ShortType;
import net.imglib2.type.numeric.real.FloatType;
import net.imglib2.view.Views;

public class ConvertersUtility {
	/**
	 * Converts from an II to a FloatPointer
	 * 
	 * @param ii IterableInterval
	 * @return FloatPointer containing the image data
	 */
	static public <T extends ComplexType<T>> FloatPointer ii2DToFloatPointer(
		final IterableInterval<T> ii)
	{
		final Cursor<T> c = ii.cursor();

		final int xd = (int) ii.dimension(0);

		final long[] pos = new long[2];

		final FloatPointer imgfp = new FloatPointer(ii.dimension(0) * ii.dimension(
			1));

		while (c.hasNext()) {
			c.fwd();
			c.localize(pos);

			final int index = (int) (pos[0] + pos[1] * xd);

			imgfp.put(index, c.get().getRealFloat());

		}

		return imgfp;

	}

	/**
	 * Converts from an II to a ShortPointer
	 * 
	 * @param ii IterableInterval
	 * @return ShortPointer containing the image data
	 */
	static public <T extends ComplexType<T>> ShortPointer ii2DToShortPointer(
		final IterableInterval<T> ii)
	{
		final Cursor<T> c = ii.cursor();

		final int xd = (int) ii.dimension(0);

		final long[] pos = new long[2];

		final ShortPointer imgfp = new ShortPointer(ii.dimension(0) * ii.dimension(
			1));

		while (c.hasNext()) {
			c.fwd();
			c.localize(pos);

			final int index = (int) (pos[0] + pos[1] * xd);

			imgfp.put(index, (short)c.get().getRealFloat());

		}

		return imgfp;

	}

	/**
	 * Converts from an II to a FloatPointer
	 * 
	 * @param ii IterableInterval
	 * @return FloatPointer containing the image data
	 */
	static public <T extends ComplexType<T>> FloatPointer ii3DToFloatPointer_(
		final IterableInterval<T> ii)
	{
		final Cursor<T> c = ii.cursor();

		final int xd = (int) ii.dimension(0);
		final int yd = (int) ii.dimension(1);

		final long[] pos = new long[3];

		final FloatPointer imgfp = new FloatPointer(ii.dimension(0) * ii.dimension(
			1) * ii.dimension(2));

		while (c.hasNext()) {
			c.fwd();
			c.localize(pos);

			final int index = (int) (pos[0] + pos[1] * xd + pos[2] * xd * yd);

			imgfp.put(index, c.get().getRealFloat());

		}

		return imgfp;

	}

	/**
	 * Converts from an II to a FloatPointer
	 * 
	 * @param ii IterableInterval
	 * @return FloatPointer containing the image data
	 */
	static public <T extends ComplexType<T>> FloatPointer ii3DToFloatPointer__(
		final IterableInterval<T> ii)
	{
		final Cursor<T> c = ii.cursor();

		float[] temp = new float[(int) (ii.dimension(0) * ii.dimension(1) * ii
			.dimension(2))];

		int index = 0;

		while (c.hasNext()) {
			c.fwd();
			temp[index++] = c.get().getRealFloat();

		}

		return new FloatPointer(temp);

	}

	/**
	 * Converts from an II to a FloatPointer
	 * 
	 * @param rai RandomAccessibleInterval
	 * @return FloatPointer containing the image data
	 */
	static public <T extends ComplexType<T>> FloatPointer ii3DToFloatPointer(
		final RandomAccessibleInterval<T> rai)
	{

		final IterableInterval<T> ii = Views.iterable(rai);

		final Cursor<T> c = ii.cursor();

		long totalSize = ii.dimension(0) * ii.dimension(1) * ii.dimension(2);

		int chunkSize = (int) Math.min(Integer.MAX_VALUE - 128, totalSize);

		float[] temp = new float[chunkSize];

		FloatPointer imgfp = new FloatPointer(totalSize);

		int numElementsInChunk = 0;
		int chunkNum = 0;

		while (c.hasNext()) {

			c.fwd();
			temp[numElementsInChunk++] = c.get().getRealFloat();

			// if the chunk is full put it in the FloatPointer
			if (numElementsInChunk == chunkSize) {
				imgfp.put(temp, chunkNum * chunkSize, numElementsInChunk);
				numElementsInChunk = 0;
				chunkNum++;
			}
		}

		// if there is a partial chunk left put it in the FloatPointer
		if (numElementsInChunk > 0) {
			imgfp.put(temp, chunkNum * chunkSize, numElementsInChunk);
		}

		return imgfp;

	}

	/**
	 * Converts from a complex II to a FloatPointer
	 * 
	 * @param ii IterableInterval
	 * @return FloatPointer containing the image data
	 */
	static public <T extends ComplexType<T>> FloatPointer
		ii2DComplexToFloatPointer(final IterableInterval<T> ii)
	{
		final Cursor<T> c = ii.cursor();

		final int xd = (int) ii.dimension(0);
		final int yd = (int) ii.dimension(1);

		final FloatPointer imgfp = new FloatPointer((long) xd * yd * 2);
		final long[] pos = new long[2];

		while (c.hasNext()) {
			c.fwd();
			c.localize(pos);

			final int index = 2 * (int) (pos[0] + pos[1] * xd);

			imgfp.put(index, c.get().getRealFloat());
			imgfp.put(index + 1, c.get().getImaginaryFloat());

		}

		return imgfp;

	}

	/**
	 * Converts from a FloatPointer to an Img
	 */
	static public Img<FloatType> floatPointerToImg3D(final FloatPointer fp,
		final long[] d)
	{
		Dimensions finalDimensions=new FinalDimensions(d);
		
		return floatPointerToImg3D(fp, finalDimensions);
	}

	/**
	 * Converts from a FloatPointer to an Img
	 */
	static public Img<FloatType> floatPointerToImg3D(final FloatPointer fp,
		final Dimensions d)
	{

		final float[] temp = new float[(int) (d.dimension(0) * d.dimension(1) * d
			.dimension(2))];
		fp.get(temp);

		return ArrayImgs.floats(temp, d.dimension(0), d.dimension(1), d.dimension(2));
	}
	
	/**
	 * Converts from a FloatPointer to an Img
	 */
	static public Img<FloatType> floatPointerToImg2D(final FloatPointer fp,
		final Dimensions d)
	{

		final float[] temp = new float[(int) (d.dimension(0) * d.dimension(1))];
		fp.get(temp);

		return ArrayImgs.floats(temp, d.dimension(0), d.dimension(1));
	}

	static public Img<FloatType> floatPointerToImg(final FloatPointer fp, int width, int height, int depth)
	{

		final float[] temp = new float[(int) (width * height * depth)];
		fp.get(temp);

		return ArrayImgs.floats(temp, width, height, depth);
	}

	/**
	 * Converts from a FloatPointer to an Img
	 */
	static public Img<ShortType> shortPointerToImg2D(final ShortPointer fp,
		final Dimensions d)
	{

		final short[] temp = new short[(int) (d.dimension(0) * d.dimension(1))];
		fp.get(temp);

		return ArrayImgs.shorts(temp, d.dimension(0), d.dimension(1));
	}

	static public float[] shortIndexedToFloatContiguous(final short[][][] f) {
		int dx = f[0][0].length;
		int dy = f[0].length;
		int dz = f.length;

		float[] out = new float[dx * dy * dz];

		for (int z = 0; z < dz; z++) {
			for (int y = 0; y < dy; y++) {
				for (int x = 0; x < dx; x++) {
					out[z * dx * dy + y * dx + x] = f[x][y][z];
				}
			}
		}

		return out;

	}

}
