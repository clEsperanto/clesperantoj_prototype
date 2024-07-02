package net.clesperanto.core.kernels;

import java.util.Arrays;
import java.util.Objects;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;

/**
 * Class containing all functions of tier 1 category
 */
public class Tier1 {

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * @param input
	 * 	the input image to be processed.
	 * @return the output image. An {@link ArrayJ} object containing the resultant image after applying the absolute value
	 *         transformation. The output image will have the same dimensions and type as the input image.
	 * @throws NullPointerException if the input image is {@code null}.
	 */
	public static ArrayJ absolute(ArrayJ input) {
		Objects.requireNonNull(input, "Input cannot be null");
		ArrayJ out = MemoryJ.like(input);
		net.clesperanto._internals.kernelj.Tier1.absolute(input.getDevice().getRaw(), input.getRaw(), out.getRaw());
		return out;
	}

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * @param input
	 * 	the input image to be processed
	 * @param output
	 * 	the output image where results are written into. Cannot be null, the result is written into this object.
	 * @throws NullPointerException if either input or output is null.
	 * @throws IllegalArgumentException if input and output are not on the same device,
	 * 	use different backends, or have mismatched dimensions.
	 */
	public static void absoluteInPlace(ArrayJ input, ArrayJ output) {
		Objects.requireNonNull(input, "Input cannot be null");
		Objects.requireNonNull(output, "Output cannot be null, as the result is written into it.");
		if (input.getDevice().getName() != output.getDevice().getName())
			throw new IllegalArgumentException("The input and output should be on the same device. Input is on '"
					+ input.getDeviceName() + "' and output on'" + output.getDeviceName() + "'.");
		else if (input.getDevice().getBackend() != output.getDevice().getBackend())
			throw new IllegalArgumentException("The input and output must ise the same backend. Input uses '"
					+ input.getDevice().getBackend() + "' and output uses'" + output.getDevice().getBackend() + "'.");
		else if (input.getDepth() != output.getDepth() || input.getDepth() != output.getDepth()
					|| input.getDepth() != output.getDepth())
			throw new IllegalArgumentException("Dimensions of input and output ArrayJs must coincide. Input dimensions "
					+ "are " + Arrays.toString(input.getDimensions()) + " and output dimensions are "
					+ Arrays.toString(output.getDimensions()));

		net.clesperanto._internals.kernelj.Tier1.absolute(input.getDevice().getRaw(), input.getRaw(), output.getRaw());
	}

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * The function supports various input types including {@link ArrayJ}, ImageJ ImagePlus, ImagLib2 RandomAccessible,
	 * and Icy Sequence. Usage of ImageJ ImagePlus, ImagLib2 RandomAccessible, and Icy Sequence requires the
	 * corresponding dependencies to be included. {@link ArrayJ} does not require any additional dependencies.
	 *
	 * @param device
	 * 	Device to perform the operation on.
	 * @param input
	 * 	the input image to be processed. Can be any of the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *  ImagLib2 RandomAccessible, or Icy Sequence. For types other than {@link ArrayJ}, corresponding
	 *  dependencies must be included.
	 * @return the resulting object. It is returned in the same type as the object. If the input was an {@link ArrayJ},
	 * 	the output will be an {@link ArrayJ}, and so on.
	 * @throws NullPointerException if input is null.
	 * @throws IllegalArgumentException if the input is an unsupported Object. This can happen because the Object is
	 * 	not supported or because the dependencies needed for the object are missing
	 */
	public static Object absoluteReturnSameType(DeviceJ device, Object input) {
		Objects.requireNonNull(input, "Input object cannot be null");
		if (input instanceof ArrayJ) {
			return absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
            ArrayJ arrayj = (ArrayJ) ObjectTypeConverter.convertImgLib2ToType(input, device, ObjectType.ARRAYJ);
            arrayj = absolute(arrayj);
            return ObjectTypeConverter.convertArrayJToType(arrayj, ObjectType.IMGLIB2);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}

	}

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * The function supports various input types including {@link ArrayJ}, ImageJ ImagePlus, ImagLib2 RandomAccessible,
	 * and Icy Sequence. Usage of ImageJ ImagePlus, ImagLib2 RandomAccessible, and Icy Sequence requires the
	 * corresponding dependencies to be included. {@link ArrayJ} does not require any additional dependencies.
	 *
	 * This method always return an {@link ArrayJ} as the output. Useful if we want to do another operation with
	 * ClesperantoJ as the {@link ArrayJ} stays on the device (GPU) and does not need to be copied back and forth
	 * into and from the CPU.
	 *
	 * @param device
	 * 	Device to perform the operation on.
	 * @param input
	 * 	the input image to be processed. Can be any of the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *  ImagLib2 RandomAccessible, or Icy Sequence. For types other than {@link ArrayJ}, corresponding
	 *  dependencies must be included.
	 * @return an {@link ArrayJ} resulting of processing the input object
	 * @throws NullPointerException if input is null.
	 * @throws IllegalArgumentException if the input is an unsupported Object. This can happen because the Object is
	 * 	not supported or because the dependencies needed for the object are missing
	 */
	public static ArrayJ absoluteReturnArrayJ(DeviceJ device, Object input) {
		Objects.requireNonNull(input, "Input object cannot be null");
		if (input instanceof ArrayJ) {
			return absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
			ArrayJ arrayj = (ArrayJ) ObjectTypeConverter.convertImgLib2ToType(input, device, ObjectType.ARRAYJ);
			return absolute(arrayj);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * The function supports various input types including {@link ArrayJ}, ImageJ ImagePlus, ImagLib2 RandomAccessible,
	 * and Icy Sequence. Usage of ImageJ ImagePlus, ImagLib2 RandomAccessible, and Icy Sequence requires the
	 * corresponding dependencies to be included. {@link ArrayJ} does not require any additional dependencies.
	 *
	 * @param device
	 * 	Device to perform the operation on.
	 * @param input
	 * 	the input image to be processed. Can be any of the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *  ImagLib2 RandomAccessible, or Icy Sequence. For types other than {@link ArrayJ}, corresponding
	 *  dependencies must be included.
	 * @param objectType
	 * 	the {@link ObjectType} in which we want the result to be given. If {@code null}, it defaults to
	 * 	{@link ObjectType#ARRAYJ} producing an {@link ArrayJ}
	 * @return the result of the processing on the input. The object type depends on the one selected. Note that
	 * 	in order to produce something different to {@link ArrayJ} the required dependencies need to be present
	 * @throws NullPointerException if input is null.
	 * @throws IllegalArgumentException if the input is an unsupported Object. This can happen because the Object is
	 * 	not supported or because the dependencies needed for the object are missing
	 */
	public static Object absoluteReturnWanted(DeviceJ device, Object input, ObjectType objectType) {
		Objects.requireNonNull(input, "Input object cannot be null");
		if (objectType == null) objectType = ObjectType.ARRAYJ;
		ArrayJ outArrayJ;
		if (input instanceof ArrayJ) {
			outArrayJ = absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
			ArrayJ inputArrayJ = (ArrayJ) ObjectTypeConverter.convertImagePlus2ToType(input, device, ObjectType.ARRAYJ);
			outArrayJ =  absolute(inputArrayJ);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}

		return ObjectTypeConverter.convertArrayJToType(outArrayJ, objectType);
	}

	/**
	 * Computes the absolute value of every individual pixel x in a given image. <pre>f(x) = |x| </pre>
	 *
	 * The function supports various input and output types including {@link ArrayJ}, ImageJ ImagePlus, ImagLib2 RandomAccessible,
	 * and Icy Sequence. Usage of ImageJ ImagePlus, ImagLib2 RandomAccessible, and Icy Sequence requires the
	 * corresponding dependencies to be included. {@link ArrayJ} does not require any additional dependencies.
	 *
	 * The input and output objects can be of different types. The operation modifies the output object in-place,
	 * regardless of its type.
	 *
	 * @param device
	 * 	Device to perform the operation on.
	 * @param input
	 * 	the input image to be processed. Can be any of the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *  ImagLib2 RandomAccessible, or Icy Sequence. For types other than {@link ArrayJ}, corresponding
	 *  dependencies must be included.
	 * @param output
	 * 	the output image where results are written into. Can be any of the supported types: {@link ArrayJ}, ImageJ ImagePlus,
	 *  ImagLib2 RandomAccessible, or Icy Sequence. For types other than {@link ArrayJ}, corresponding
	 *  dependencies must be included.
	 * @throws NullPointerException if either input or output is null.
	 * @throws IllegalArgumentException if input and output are not on the same device,
	 * 	use different backends, or have mismatched dimensions.
	 */
	public static void absoluteInPlace(DeviceJ device, Object input, Object output) {
		Objects.requireNonNull(input, "Input cannot be null");
		Objects.requireNonNull(output, "Output cannot be null, as the result is written into it.");
		if (input instanceof ArrayJ && output instanceof ArrayJ) {
			absoluteInPlace((ArrayJ) input, (ArrayJ) output);
		} else if (DepsManager.IMGLIB2_AVAILABLE
				&& DepsManager.RAI_CLASS.isInstance(input) && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMGLIB2_AVAILABLE
				&& DepsManager.RAI_CLASS.isInstance(input) && output instanceof ArrayJ) {
			// TODO do something
		} else if (DepsManager.IMGLIB2_AVAILABLE
				 && input instanceof ArrayJ && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && output instanceof ArrayJ) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE
				&& input instanceof ArrayJ && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMGLIB2_AVAILABLE
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMGLIB2_AVAILABLE
				&& DepsManager.RAI_CLASS.isInstance(input) && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}

}
