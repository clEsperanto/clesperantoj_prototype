
package net.clesperanto.kernels;

import java.util.Objects;
import java.util.ArrayList;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.Utils;

/**
 * Class containing all functions of tier 6 category
 */
public class Tier6 {

	/**
	 * Dilates labels to a larger size.
	 * No label overwrites another label.
	 * Similar to the implementation in scikitimage [2] and MorpholibJ[3] Notes * This operation assumes input images are isotropic.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - label image to erode
	 * @param output ({@link ArrayJ}) - result (default: None)
	 * @param radius (int) -  (default: 2)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ dilateLabels(DeviceJ device, ArrayJ input, ArrayJ output, int radius) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier6.dilate_labels(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius), device);
    }

	/**
	 * Erodes labels to a smaller size.
	 * Note: Depending on the label image and the radius, labels may disappear and labels may split into multiple islands.
	 * Thus, overlapping labels of input and output may not have the same identifier.
	 * Notes * This operation assumes input images are isotropic.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - result
	 * @param output ({@link ArrayJ}) -  (default: None)
	 * @param radius (int) -  (default: 1)
	 * @param relabel (boolean) - and all label indices exist. (default: False)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ erodeLabels(DeviceJ device, ArrayJ input, ArrayJ output, int radius, boolean relabel) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier6.erode_labels(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius, relabel), device);
    }

	/**
	 * Labels objects directly from grey-value images.
	 * The outline_sigma parameter allows tuning the segmentation result.
	 * Under the hood,  this filter applies a Gaussian blur, Otsu-thresholding [1] and connected component labeling [2].
	 * The  thresholded binary image is flooded using the Voronoi tesselation approach starting from the found local maxima.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - intensity image to add labels
	 * @param output ({@link ArrayJ}) - Output label image. (default: None)
	 * @param outline_sigma (float) - Gaussian blur sigma along all axes (default: 0)
	 * @return {@link ArrayJ}
	 * @see https://ieeexplore.ieee.org/document/4310076
	 * @see https://en.wikipedia.org/wiki/Connected-component_labeling
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ gaussOtsuLabeling(DeviceJ device, ArrayJ input0, ArrayJ output, float outline_sigma) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier6.gauss_otsu_labeling(device.getRaw(), input0.getRaw(), output == null ? null : output.getRaw(), outline_sigma), device);
    }

	/**
	 * Takes a binary image, labels connected components and dilates the regions using a octagon shape until they touch.
	 * The region growing is limited to a masked area.
	 * The resulting label map is written to the output.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) -
	 * @param mask ({@link ArrayJ}) -
	 * @param output ({@link ArrayJ}) -  (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_maskedVoronoiLabeling
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ maskedVoronoiLabeling(DeviceJ device, ArrayJ input, ArrayJ mask, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(mask, "mask cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier6.masked_voronoi_labeling(device.getRaw(), input.getRaw(), mask.getRaw(), output == null ? null : output.getRaw()), device);
    }

	/**
	 * Takes a binary image, labels connected components and dilates the regions using a octagon shape until they touch.
	 * The resulting label map is written to the output.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) -
	 * @param output ({@link ArrayJ}) -  (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_voronoiLabeling
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ voronoiLabeling(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier6.voronoi_labeling(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }

}
