package net.clesperanto.kernels;

import java.util.Objects;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

/**
 * Class containing all functions of tier 1 category
 */
public class Tier1 {

	/**
	 * Computes the absolute value of every individual pixel x in a given image.
	 *
	 * <pre>
	 * f(x) = |x|
	 * </pre>
	 *
	 * The function supports various input and output types including
	 * {@link ArrayJ}, ImageJ ImagePlus, ImagLib2 RandomAccessible,
	 * and Icy Sequence. Usage of ImageJ ImagePlus, ImagLib2 RandomAccessible, and
	 * Icy Sequence requires the
	 * corresponding dependencies to be included. {@link ArrayJ} does not require
	 * any additional dependencies.
	 *
	 * The input and output objects can be of different types. The operation
	 * modifies the output object in-place,
	 * regardless of its type.
	 *
	 * @param device
	 *               Device to perform the operation on.
	 * @param input
	 *               the input image to be processed. Can be any of the supported
	 *               types: {@link ArrayJ}, ImageJ ImagePlus,
	 *               ImagLib2 RandomAccessible, or Icy Sequence. For types other
	 *               than {@link ArrayJ}, corresponding
	 *               dependencies must be included.
	 * @param output
	 *               the output image where results are written into. Can be any of
	 *               the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *               ImagLib2 RandomAccessible, or Icy Sequence. For types other
	 *               than {@link ArrayJ}, corresponding
	 *               dependencies must be included.
	 * @throws NullPointerException     if either input or output is null.
	 * @throws IllegalArgumentException if input and output are not on the same
	 *                                  device,
	 *                                  use different backends, or have mismatched
	 *                                  dimensions.
	 */
	public static ArrayJ absolute(DeviceJ device, ArrayJ input, ArrayJ output) {
		Objects.requireNonNull(input, "Input cannot be null");

		return new ArrayJ(
				net.clesperanto._internals.kernelj.Tier1.absolute(input.getDevice().getRaw(),
						input.getRaw(),
						output == null ? null : output.getRaw()),
				device);
	}
}
