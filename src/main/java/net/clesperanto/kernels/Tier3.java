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
 * Class containing all functions of tier 3 category
 */
public class Tier3 {

	/**
	 * Determines the bounding box of all nonzero pixels in a binary image.
	 * The positions are returned in  an array of 6 values as follows: minX, minY, minZ, maxX, maxY, maxZ.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input binary image
	 * @return ArrayList&amp;lt;Float&amp;gt;
	 * @see <a href="https://clij.github.io/clij2-docs/reference_boundingBox">reference_boundingBox</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayList<Float> boundingBox(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return Utils.toArrayList(net.clesperanto._internals.kernelj.Tier3.bounding_box(device.getRaw(), input.getRaw()));
    }
    
	/**
	 * Determines the center of mass of an image or image stack.
	 * It writes the result in the results table in the columns MassX, MassY and MassZ.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image
	 * @return ArrayList&amp;lt;Float&amp;gt;
	 * @see <a href="https://clij.github.io/clij2-docs/reference_centerOfMass">reference_centerOfMass</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayList<Float> centerOfMass(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return Utils.toArrayList(net.clesperanto._internals.kernelj.Tier3.center_of_mass(device.getRaw(), input.getRaw()));
    }
    
	/**
	 * This operation removes labels from a labelmap and renumbers the remaining labels.
	 * Hand over a binary flag list vector starting with a flag for the background, continuing with label1, label2,.
	 * For example if you pass 0,1,0,0,1: Labels 1 and 4 will be removed (those with a 1 in the vector will be excluded).
	 * Labels 2 and 3 will be kept and renumbered to 1 and 2.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input label image
	 * @param list ({@link ArrayJ}) - Vector of 0 and 1 flagging labels to remove
	 * @param output ({@link ArrayJ}) - Output label image (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabels">reference_excludeLabels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ removeLabels(DeviceJ device, ArrayJ input, ArrayJ list, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(list, "list cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.remove_labels(device.getRaw(), input.getRaw(), list.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * This operation removes labels from a labelmap and renumbers the remaining labels.
	 * Hand over a binary flag list vector starting with a flag for the background, continuing with label1, label2,.
	 * For example if you pass 0,1,0,0,1: Labels 1 and 4 will be removed (those with a 1 in the vector will be excluded).
	 * Labels 2 and 3 will be kept and renumbered to 1 and 2.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input label image
	 * @param list ({@link ArrayJ}) - Vector of 0 and 1 flagging labels to remove
	 * @param output ({@link ArrayJ}) - Output label image (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabels">reference_excludeLabels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ excludeLabels(DeviceJ device, ArrayJ input, ArrayJ list, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(list, "list cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.exclude_labels(device.getRaw(), input.getRaw(), list.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Removes all labels from a label map which touch the edges of the image.
	 * Remaining label elements are renumbered afterwards.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on. (default: None)
	 * @param input ({@link ArrayJ}) - Input label image
	 * @param output ({@link ArrayJ}) - Output label image (default: None)
	 * @param exclude_x (boolean) - Exclude labels along min and max x (default: True)
	 * @param exclude_y (boolean) - Exclude labels along min and max y (default: True)
	 * @param exclude_z (boolean) - Exclude labels along min and max z (default: True)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsOnEdges">reference_excludeLabelsOnEdges</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ removeLabelsOnEdges(DeviceJ device, ArrayJ input, ArrayJ output, boolean exclude_x, boolean exclude_y, boolean exclude_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.remove_labels_on_edges(device == null ? null : device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), exclude_x, exclude_y, exclude_z));
    }
    
	/**
	 * Removes all labels from a label map which touch the edges of the image.
	 * Remaining label elements are renumbered afterwards.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on. (default: None)
	 * @param input ({@link ArrayJ}) - Input label image
	 * @param output ({@link ArrayJ}) - Output label image (default: None)
	 * @param exclude_x (boolean) - Exclude labels along min and max x (default: True)
	 * @param exclude_y (boolean) - Exclude labels along min and max y (default: True)
	 * @param exclude_z (boolean) - Exclude labels along min and max z (default: True)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_excludeLabelsOnEdges">reference_excludeLabelsOnEdges</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ excludeLabelsOnEdges(DeviceJ device, ArrayJ input, ArrayJ output, boolean exclude_x, boolean exclude_y, boolean exclude_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.exclude_labels_on_edges(device == null ? null : device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), exclude_x, exclude_y, exclude_z));
    }
    
	/**
	 * Given a label map this function will generate a binary vector where all pixels are set to 1 if label with given xcoordinate in the vector exists.
	 * For example a label image such as ``` 0 1 3 5 ``` will produce a flag_vector like this: ``` 1 1 0 1 0 1 ```.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - a label image
	 * @param output ({@link ArrayJ}) - binary vector, if given should have size 1*n with n = maximum label + 1 (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ flagExistingLabels(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.flag_existing_labels(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Applies a gamma correction to an image.
	 * Therefore, all pixels x of the Image X are normalized and the power to gamma g is computed, before normlization is reversed (^ is the power operator):f(x) = (x / max(X)) ^ gamma * max(X).
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image
	 * @param output ({@link ArrayJ}) - Output image (default: None)
	 * @param gamma (float) -  (default: 1)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_gammaCorrection">reference_gammaCorrection</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ gammaCorrection(DeviceJ device, ArrayJ input, ArrayJ output, float gamma) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.gamma_correction(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), gamma));
    }
    
	/**
	 * Takes two labelmaps with n and m labels and generates a (n+1)*(m+1) matrix where all pixels are set to 0 exept those where labels overlap between the label maps.
	 * For example, if labels 3 in labelmap1 and 4 in labelmap2 are touching then the pixel (3,4) in the matrix will be set to 1.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input label image
	 * @param input1 ({@link ArrayJ}) - Second input label image
	 * @param output ({@link ArrayJ}) - Output overlap matrix (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_generateBinaryOverlapMatrix">reference_generateBinaryOverlapMatrix</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ generateBinaryOverlapMatrix(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.generate_binary_overlap_matrix(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Takes a labelmap with n labels and generates a (n+1)*(n+1) matrix where all pixels are set to 0 exept those where labels are touching.
	 * Only half of the matrix is filled (with x &amp;lt; y).
	 * For example, if labels 3 and 4 are touching then the pixel (3,4) in the matrix will be set to 1.
	 * The touch matrix is a representation of a region adjacency graph.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input label image
	 * @param output ({@link ArrayJ}) - Output touch matrix (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_generateTouchMatrix">reference_generateTouchMatrix</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ generateTouchMatrix(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.generate_touch_matrix(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()));
    }
    
	/**
	 * Determines the histogram of a given image.
	 * The histogram image is of dimensions number_of_bins/1/1; a 3D image with height=1 and depth=1.
	 * Histogram bins contain the number of pixels with intensity in this corresponding bin.
	 * The histogram bins are uniformly distributed between given minimum and maximum grey value intensity.
	 * If the flag determine_min_max is set, minimum and maximum intensity will be determined.
	 * When calling this operation many times, it is recommended to determine minimum and maximum intensity once at the beginning and handing over these values.
	 * Author(s): Robert Haase adapted work from Aaftab Munshi, Benedict Gaster, Timothy Mattson, James Fung, Dan Ginsburg License: adapted code from https://github.
	 * com/bgaster/openclbooksamples/blob/master/src/Chapter_14/histogram/histogram_image.
	 * cl It was published unter BSD license according to https://code.
	 * google.
	 * com/archive/p/openclbooksamples/ Book: OpenCL(R) Programming Guide Authors: Aaftab Munshi, Benedict Gaster, Timothy Mattson, James Fung, Dan Ginsburg ISBN10: 0321749642 ISBN13: 9780321749642 Publisher: AddisonWesley Professional URLs: http://safari.
	 * informit.
	 * com/9780132488006/ http://www.
	 * openclprogrammingguide.
	 * com.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to derive histogram from
	 * @param output ({@link ArrayJ}) - Output histogram (default: None)
	 * @param num_bins (int) -  (default: 256)
	 * @param minimum_intensity (float) -  (default: None)
	 * @param maximum_intensity (float) -  (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_histogram">reference_histogram</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ histogram(DeviceJ device, ArrayJ input, ArrayJ output, int num_bins, float minimum_intensity, float maximum_intensity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.histogram(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), num_bins, minimum_intensity, maximum_intensity));
    }
    
	/**
	 * Determines the overlap of two binary images using the Jaccard index.
	 * A value of 0 suggests no overlap, 1 means perfect overlap.
	 * The resulting Jaccard index is saved to the results table in the 'Jaccard_Index' column.
	 * Note that the SorensenDice coefficient can be calculated from the Jaccard index j using this formula: &amp;lt;pre&amp;gt;s = f(j) = 2 j / (j + 1)&amp;lt;/pre&amp;gt;.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First binary image to compare
	 * @param input1 ({@link ArrayJ}) - Second binary image to compare
	 * @return float
	 * @see <a href="https://clij.github.io/clij2-docs/reference_jaccardIndex">reference_jaccardIndex</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float jaccardIndex(DeviceJ device, ArrayJ input0, ArrayJ input1) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return net.clesperanto._internals.kernelj.Tier3.jaccard_index(device.getRaw(), input0.getRaw(), input1.getRaw());
    }
    
	/**
	 * Generates a coordinate list of points in a labelled spot image.
	 * Transforms a labelmap of spots (single pixels with values 1, 2,.
	 * , n for n spots) as resulting from connected components analysis in an image where every column contains d pixels (with d = dimensionality of the original image) with the coordinates of the maxima/minima.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param label ({@link ArrayJ}) - Input
	 * @param pointlist ({@link ArrayJ}) - Output coordinate list (default: None)
	 * @return {@link ArrayJ}
	 * @see <a href="https://clij.github.io/clij2-docs/reference_labelledSpotsToPointList">reference_labelledSpotsToPointList</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ labelledSpotsToPointlist(DeviceJ device, ArrayJ label, ArrayJ pointlist) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(label, "label cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.labelled_spots_to_pointlist(device.getRaw(), label.getRaw(), pointlist == null ? null : pointlist.getRaw()));
    }
    
	/**
	 * Determines the position of the maximum of all pixels in a given image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The image of which the position of the maximum of all pixels will be determined.
	 * @return ArrayList&amp;lt;Float&amp;gt;
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayList<Float> maximumPosition(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return Utils.toArrayList(net.clesperanto._internals.kernelj.Tier3.maximum_position(device.getRaw(), input.getRaw()));
    }
    
	/**
	 * Determines the mean average of all pixels in a given image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The image of which the mean average of all pixels will be determined.
	 * @return float
	 * @see <a href="https://clij.github.io/clij2-docs/reference_meanOfAllPixels">reference_meanOfAllPixels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float meanOfAllPixels(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return net.clesperanto._internals.kernelj.Tier3.mean_of_all_pixels(device.getRaw(), input.getRaw());
    }
    
	/**
	 * Determines the position of the minimum of all pixels in a given image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The image of which the position of the minimum of all pixels will be determined.
	 * @return ArrayList&amp;lt;Float&amp;gt;
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayList<Float> minimumPosition(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return Utils.toArrayList(net.clesperanto._internals.kernelj.Tier3.minimum_position(device.getRaw(), input.getRaw()));
    }
    
	/**
	 * Compute an active contour model using the Chan-Vese morphological algorithm.
	 * The output image (dst) should also be initialisation of the contour.
	 * If not provided (nullptr), the function will use a checkboard pattern initialisation.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output contour, can also be use to provide initialisation. (default: None)
	 * @param num_iter (int) - Number of iterations. (default: 100)
	 * @param smoothing (int) - Number of (default: 1)
	 * @param lambda1 (float) - Lambda1. (default: 1)
	 * @param lambda2 (float) - Lambda2. (default: 1)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ morphologicalChanVese(DeviceJ device, ArrayJ input, ArrayJ output, int num_iter, int smoothing, float lambda1, float lambda2) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier3.morphological_chan_vese(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), num_iter, smoothing, lambda1, lambda2));
    }
    
	/**
	 * Compute the bounding box, area (in pixels/voxels), minimum intensity, maximum intensity, average intensity, standard deviation of the intensity, and some shape descriptors of labelled objects in a label image and its corresponding intensity image.
	 * The intensity image is equal to the label image if not provided.
	 * The label image is set to the entire image if not provided.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param intensity ({@link ArrayJ}) - Intensity image. (default: None)
	 * @param label ({@link ArrayJ}) - Label image to compute the statistics. (default: None)
	 * @return StatisticsMap
	 * @see <a href="https://clij.github.io/clij2-docs/reference_statisticsOfLabelledPixels">reference_statisticsOfLabelledPixels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static HashMap<String, ArrayList<Float>> statisticsOfLabelledPixels(DeviceJ device, ArrayJ intensity, ArrayJ label) {
        Objects.requireNonNull(device, "device cannot be null");
        return Utils.toHashMap(net.clesperanto._internals.kernelj.Tier3.statistics_of_labelled_pixels(device.getRaw(), intensity == null ? null : intensity.getRaw(), label == null ? null : label.getRaw()));
    }
    
	/**
	 * Compute, for the background and labels, the bounding box, area (in pixels/voxels), minimum intensity, maximum intensity, average intensity, standard deviation of the intensity, and some shape descriptors of labelled objects in a label image and its corresponding intensity image.
	 * The intensity image is equal to the label image if not provided.
	 * The label image is set to the entire image if not provided.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param intensity ({@link ArrayJ}) - Intensity image. (default: None)
	 * @param label ({@link ArrayJ}) - Label image to compute the statistics. (default: None)
	 * @return StatisticsMap
	 * @see <a href="https://clij.github.io/clij2-docs/reference_statisticsOfBackgroundAndLabelledPixels">reference_statisticsOfBackgroundAndLabelledPixels</a>
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static HashMap<String, ArrayList<Float>> statisticsOfBackgroundAndLabelledPixels(DeviceJ device, ArrayJ intensity, ArrayJ label) {
        Objects.requireNonNull(device, "device cannot be null");
        return Utils.toHashMap(net.clesperanto._internals.kernelj.Tier3.statistics_of_background_and_labelled_pixels(device.getRaw(), intensity == null ? null : intensity.getRaw(), label == null ? null : label.getRaw()));
    }
    
}
