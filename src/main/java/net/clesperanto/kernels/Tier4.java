
/**
 * This file is autogenerated. Do not edit manually.
 */    
package net.clesperanto.kernels;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.Utils;

/**
 * Class containing all functions of tier 4 category
 */
public class Tier4 {

	/**
	 * Determines the bounding box of the specified label from a label image.
	 * The positions are returned in  an array of 6 values as follows: minX, minY, minZ, maxX, maxY, maxZ.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Label image
	 * @param label_id (int) - Identifier of label
	 * @return ArrayList&amp;lt;Float&amp;gt;
	 * @see <a href="https://clij.github.io/clij2-docs/reference_boundingBox">reference_boundingBox</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayList<Float> labelBoundingBox(DeviceJ device, ArrayJ input, int label_id) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return Utils.toArrayList(net.clesperanto._internals.kernelj.Tier4.label_bounding_box(device.getRaw(), input.getRaw(), label_id));
    }
    
	/**
	 * Determines the mean squared error (MSE) between two images.
	 * The MSE will be stored in a new row of ImageJs Results table in the column 'MSE'.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First image to compare
	 * @param input1 ({@link ArrayJ}) - Second image to compare
	 * @return float
	 * @see <a href="https://clij.github.io/clij2-docs/reference_meanSquaredError">reference_meanSquaredError</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float meanSquaredError(DeviceJ device, ArrayJ input0, ArrayJ input1) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return net.clesperanto._internals.kernelj.Tier4.mean_squared_error(device.getRaw(), input0.getRaw(), input1.getRaw());
    }
    
	/**
	 * Transforms a spots image as resulting from maximum/minimum detection in an image where every column contains d pixels (with d = dimensionality of the original image) with the coordinates of the maxima/minima.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input binary image of spots
	 * @param output ({@link ArrayJ}) - Output coordinate list of spots (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_spotsToPointList">reference_spotsToPointList</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ spotsToPointlist(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.spots_to_pointlist(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Analyses a label map and if there are gaps in the indexing (e.
	 * g.
	 * label 5 is not present) all subsequent labels will be relabelled.
	 * Thus, afterwards number of labels and maximum label index are equal.
	 * This operation is mostly performed on the CPU.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input label image.
	 * @param output ({@link ArrayJ}) - Output label image. (default: None)
	 * @param blocksize (int) - Renumbering is done in blocks for performance reasons. (default: 4096)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_closeIndexGapsInLabelMap">reference_closeIndexGapsInLabelMap</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ relabelSequential(DeviceJ device, ArrayJ input, ArrayJ output, int blocksize) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.relabel_sequential(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), blocksize));
    }
    
	/**
	 * Binarizes an image using Otsu's threshold method [3] implemented in scikit-image[2] using a histogram determined on the GPU to create binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to threshold.
	 * @param output ({@link ArrayJ}) - Output binary image. (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_thresholdOtsu">reference_thresholdOtsu</a>
	 * @see <a href="https://scikit-image.org/docs/dev/api/skimage.filters.html#skimage.filters.threshold_otsu">skimage.filters.html#skimage.filters.threshold_otsu</a>
	 * @see <a href="https://ieeexplore.ieee.org/document/4310076">4310076</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ thresholdOtsu(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.threshold_otsu(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Takes an image and a corresponding label map, determines the mean   intensity per label and replaces every label with the that number.
	 * This results in a parametric image expressing mean object intensity.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - intensity image
	 * @param labels ({@link ArrayJ}) - label image
	 * @param output ({@link ArrayJ}) - Parametric image computed (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_meanIntensityMap">reference_meanIntensityMap</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ meanIntensityMap(DeviceJ device, ArrayJ input, ArrayJ labels, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(labels, "labels cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.mean_intensity_map(device.getRaw(), input.getRaw(), labels.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Takes a label map, determines the number of pixels per label and replaces every label with the that number.
	 * This results in a parametric image expressing area or volume.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Label image to measure
	 * @param output ({@link ArrayJ}) - Parametric image computed (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_pixelCountMap">reference_pixelCountMap</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ pixelCountMap(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.pixel_count_map(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Takes a label map, determines the number of pixels per label and replaces every label with the that number.
	 * This results in a parametric image expressing area or volume.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Label image to measure
	 * @param output ({@link ArrayJ}) - Parametric image computed (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_pixelCountMap">reference_pixelCountMap</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ labelPixelCountMap(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.label_pixel_count_map(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Determines the centroids of all labels in a label image or image stack.
	 * It writes the resulting coordinates in point list image of dimensions n * d where n is the number of labels and d=3 the dimensionality (x,y,z) of the original image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param label_image ({@link ArrayJ}) - Label image where the centroids will be determined from.
	 * @param centroids_coordinates ({@link ArrayJ}) - Output list of coordinates where the centroids will be written to.
	 * @param include_background (boolean) - Determines if the background label should be included. (default: False)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_centroidsOfLabels">reference_centroidsOfLabels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ centroidsOfLabels(DeviceJ device, ArrayJ label_image, ArrayJ centroids_coordinates, boolean include_background) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(label_image, "label_image cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.centroids_of_labels(device.getRaw(), label_image.getRaw(), centroids_coordinates.getRaw(), include_background));
    }
    
	/**
	 * Remove labels with values outside a given value range based on a vector of values associated with the labels.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image where labels will be filtered.
	 * @param values ({@link ArrayJ}) - Vector of
	 * @param output ({@link ArrayJ}) - Output image where labels will be written to. (default: None)
	 * @param min_value (float) - Minimum value to keep. (default: 0)
	 * @param max_value (float) - Maximum value to keep. (default: 100)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsWithValuesOutOfRange">reference_excludeLabelsWithValuesOutOfRange</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ removeLabelsWithMapValuesOutOfRange(DeviceJ device, ArrayJ input, ArrayJ values, ArrayJ output, float min_value, float max_value) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(values, "values cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.remove_labels_with_map_values_out_of_range(device.getRaw(), input.getRaw(), values.getRaw(), output == null ? null : output.getRaw(), min_value, max_value));
    }
    
	/**
	 * Remove labels with values inside a given value range based on a vector of values associated with the labels.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image where labels will be filtered.
	 * @param values ({@link ArrayJ}) - Vector of
	 * @param output ({@link ArrayJ}) - Output image where labels will be written to. (default: None)
	 * @param min_value (float) - Minimum value to keep. (default: 0)
	 * @param max_value (float) - Maximum value to keep. (default: 100)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsWithValuesWithinRange">reference_excludeLabelsWithValuesWithinRange</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ removeLabelsWithMapValuesWithinRange(DeviceJ device, ArrayJ input, ArrayJ values, ArrayJ output, float min_value, float max_value) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(values, "values cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.remove_labels_with_map_values_within_range(device.getRaw(), input.getRaw(), values.getRaw(), output == null ? null : output.getRaw(), min_value, max_value));
    }
    
	/**
	 * Exclude labels with values outside a given value range based on a vector of values associated with the labels.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param values_map ({@link ArrayJ}) - Vector of values associated with the labels.
	 * @param label_map_input ({@link ArrayJ}) - Input image where labels will be filtered.
	 * @param output ({@link ArrayJ}) - Output image where labels will be written to. (default: None)
	 * @param minimum_value_range (float) - Minimum value to keep. (default: 0)
	 * @param maximum_value_range (float) - Maximum value to keep. (default: 100)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsWithValuesOutOfRange">reference_excludeLabelsWithValuesOutOfRange</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ excludeLabelsWithMapValuesOutOfRange(DeviceJ device, ArrayJ values_map, ArrayJ label_map_input, ArrayJ output, float minimum_value_range, float maximum_value_range) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(values_map, "values_map cannot be null");
		Objects.requireNonNull(label_map_input, "label_map_input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.exclude_labels_with_map_values_out_of_range(device.getRaw(), values_map.getRaw(), label_map_input.getRaw(), output == null ? null : output.getRaw(), minimum_value_range, maximum_value_range));
    }
    
	/**
	 * Exclude labels with values inside a given value range based on a vector of values associated with the labels.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param values_map ({@link ArrayJ}) - Vector of values associated with the labels.
	 * @param label_map_input ({@link ArrayJ}) - Input image where labels will be filtered.
	 * @param output ({@link ArrayJ}) - Output image where labels will be written to. (default: None)
	 * @param minimum_value_range (float) - Minimum value to keep. (default: 0)
	 * @param maximum_value_range (float) - Maximum value to keep. (default: 100)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsWithValuesWithinRange">reference_excludeLabelsWithValuesWithinRange</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ excludeLabelsWithMapValuesWithinRange(DeviceJ device, ArrayJ values_map, ArrayJ label_map_input, ArrayJ output, float minimum_value_range, float maximum_value_range) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(values_map, "values_map cannot be null");
		Objects.requireNonNull(label_map_input, "label_map_input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.exclude_labels_with_map_values_within_range(device.getRaw(), values_map.getRaw(), label_map_input.getRaw(), output == null ? null : output.getRaw(), minimum_value_range, maximum_value_range));
    }
    
	/**
	 * Determines the ratio of the extension for every label in a label map and returns it as a parametric map.
	 * The extension ration is defined as the maximum distance of any pixel in the label to the label's centroid divided by the average distance of all pixels in the label to the centroid.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input label image.
	 * @param output ({@link ArrayJ}) - Output parametric image. (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_extensionRatioMap">reference_extensionRatioMap</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ extensionRatioMap(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier4.extension_ratio_map(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
}
