
package net.clesperanto.kernels;

import java.util.Objects;
import java.util.ArrayList;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.Utils;

/**
 * Class containing all functions of tier 5 category
 */
public class Tier5 {

	/**
	 * Compares if all pixels of two images are identical.
	 * If shape of the images or any pixel are different, returns False.
	 * True otherwise This function is supposed to work similarly like its counterpart in numpy [1].
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - 
	 * @param input1 ({@link ArrayJ}) - 
	 * @return boolean
	 * @see https://numpy.org/doc/stable/reference/generated/numpy.array_equal.html
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static boolean arrayEqual(DeviceJ device, ArrayJ input0, ArrayJ input1) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return net.clesperanto._internals.kernelj.Tier5.array_equal(device.getRaw(), input0.getRaw(), input1.getRaw());
    }
    
	/**
	 * Combines two label images by adding labels of a given label image to another.
	 * Labels in the second image overwrite labels in the first passed image.
	 * Afterwards, labels are relabeled sequentially.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - label image to add labels to.
	 * @param input1 ({@link ArrayJ}) - label image to add labels from.
	 * @param output ({@link ArrayJ}) - Output label image. (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ combineLabels(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier5.combine_labels(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Performs connected components analysis inspecting the box neighborhood of every pixel to a binary image and generates a label map.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Binary image to label.
	 * @param output ({@link ArrayJ}) - Output label image. (default: None)
	 * @param connectivity (String) - Defines pixel neighborhood relationship, "box" or "sphere". (default: 'box')
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_connectedComponentsLabelingBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ connectedComponentsLabeling(DeviceJ device, ArrayJ input, ArrayJ output, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier5.connected_components_labeling(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), connectivity), device);
    }
    
}
