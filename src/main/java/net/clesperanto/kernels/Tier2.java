
package net.clesperanto.kernels;

import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.Utils;

/**
 * Class containing all functions of tier 2 category
 */
public class Tier2 {

	/**
	 * Determines the absolute difference pixel by pixel between two images.
	 * <pre>f(x, y) = |x y| </pre>.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - The input image to be subtracted from.
	 * @param input1 ({@link ArrayJ}) - The input image which is subtracted.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_absoluteDifference
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ absoluteDifference(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.absolute_difference(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Calculates the sum of pairs of pixels x and y of two images X and Y.
	 * <pre>f(x, y) = x + y</pre>.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - The first input image to added.
	 * @param input1 ({@link ArrayJ}) - The second image to be added.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_addImages
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ addImages(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.add_images(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Apply a bottomhat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (int) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (int) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (int) - Radius of the background determination region in Z. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_bottomHatBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ bottomHatBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.bottom_hat_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Applies a bottomhat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (float) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (float) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (float) - Radius of the background determination region in Z. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_bottomHatSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ bottomHatSphere(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.bottom_hat_sphere(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Applies a bottomhat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (float) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (float) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (float) - Radius of the background determination region in Z. (default: 1)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_bottomHatBox
	 * @see https://clij.github.io/clij2-docs/reference_bottomHatSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ bottomHat(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.bottom_hat(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Limits the range of values in an image.
	 * This function is supposed to work similarly as its counter part in numpy [1].
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param min_intensity (float) - new, lower limit of the intensity range (default: None)
	 * @param max_intensity (float) - new, upper limit of the intensity range (default: None)
	 * @return {@link ArrayJ}
	 * @see https://numpy.org/doc/stable/reference/generated/numpy.clip.html
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ clip(DeviceJ device, ArrayJ input, ArrayJ output, float min_intensity, float max_intensity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.clip(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), min_intensity, max_intensity), device);
    }
    
	/**
	 * Closing operator, boxshaped Applies morphological closing to intensity images using a boxshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ closingBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.closing_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Closing operator, sphereshaped Applies morphological closing to intensity images using a sphereshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 1)
	 * @param radius_y (int) - Radius along the y axis. (default: 1)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ closingSphere(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.closing_sphere(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Closing operator, sphereshaped Applies morphological closing to intensity images using a sphereshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 1)
	 * @param radius_y (int) - Radius along the y axis. (default: 1)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ closing(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.closing(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Concatenate two images or stacks along the X axis.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input image.
	 * @param input1 ({@link ArrayJ}) - Second input image.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_combineHorizontally
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ concatenateAlongX(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.concatenate_along_x(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Concatenate two images or stacks along the Y axis.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input image.
	 * @param input1 ({@link ArrayJ}) - Second input image.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_combineVertically
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ concatenateAlongY(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.concatenate_along_y(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Concatenate two images or stacks along the Z axis.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input image.
	 * @param input1 ({@link ArrayJ}) - Second input image.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_concatenateStacks
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ concatenateAlongZ(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.concatenate_along_z(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Takes a touch matrix as input and delivers a vector with number of touching neighbors per label as a vector.
	 * Note: Background is considered as something that can touch.
	 * To ignore touches with background, hand over a touch matrix where the first column (index = 0) has been set to 0.
	 * Use set_column for that.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param ignore_background (boolean) -  (default: True)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_countTouchingNeighbors
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ countTouchingNeighbors(DeviceJ device, ArrayJ input, ArrayJ output, boolean ignore_background) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.count_touching_neighbors(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), ignore_background), device);
    }
    
	/**
	 * Crops an image by removing the outer pixels, per default 1.
	 * Notes * To make sure the output image has the right size, provide destination_image=None.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param border_size (int) - Border size to crop. (default: 1)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ cropBorder(DeviceJ device, ArrayJ input, ArrayJ output, int border_size) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.crop_border(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), border_size), device);
    }
    
	/**
	 * Applies Gaussian blur to the input image and divides the original by the result.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param sigma_x (float) - Gaussian sigma value along x. (default: 2)
	 * @param sigma_y (float) - Gaussian sigma value along y. (default: 2)
	 * @param sigma_z (float) - Gaussian sigma value along z. (default: 2)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_divideByGaussianBackground
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ divideByGaussianBackground(DeviceJ device, ArrayJ input, ArrayJ output, float sigma_x, float sigma_y, float sigma_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.divide_by_gaussian_background(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), sigma_x, sigma_y, sigma_z), device);
    }
    
	/**
	 * Converts radians to degrees.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ degreesToRadians(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.degrees_to_radians(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Detects local maxima in a given square/cubic neighborhood.
	 * Pixels in the resulting image are set to 1 if there is no other pixel in a given radius which has a higher intensity, and to 0 otherwise.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_detectMaximaBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ detectMaximaBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.detect_maxima_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Detects local maxima in a given square/cubic neighborhood.
	 * Pixels in the resulting image are set to 1 if there is no other pixel in a given radius which has a higher intensity, and to 0 otherwise.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_detectMaximaBox
	 * @see https://clij.github.io/clij2-docs/reference_detectMaximaSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ detectMaxima(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.detect_maxima(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Detects local maxima in a given square/cubic neighborhood.
	 * Pixels in the resulting image are set to 1 if there is no other pixel in a given radius which has a lower intensity, and to 0 otherwise.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_detectMinimaBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ detectMinimaBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.detect_minima_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Detects local maxima in a given square/cubic neighborhood.
	 * Pixels in the resulting image are set to 1 if there is no other pixel in a given radius which has a lower intensity, and to 0 otherwise.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_detectMinimaBox
	 * @see https://clij.github.io/clij2-docs/reference_detectMinimaSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ detectMinima(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.detect_minima(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Applies Gaussian blur to the input image twice with different sigma values resulting in two images which are then subtracted from each other.
	 * It is recommended to apply this operation to images of type Float (32 bit) as results might be negative.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image to be processed.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param sigma1_x (float) - Sigma of the first Gaussian filter in x (default: 2)
	 * @param sigma1_y (float) - Sigma of the first Gaussian filter in y (default: 2)
	 * @param sigma1_z (float) - Sigma of the first Gaussian filter in z (default: 2)
	 * @param sigma2_x (float) - Sigma of the second Gaussian filter in x (default: 2)
	 * @param sigma2_y (float) - Sigma of the second Gaussian filter in y (default: 2)
	 * @param sigma2_z (float) - Sigma of the second Gaussian filter in z (default: 2)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_differenceOfGaussian3D
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ differenceOfGaussian(DeviceJ device, ArrayJ input, ArrayJ output, float sigma1_x, float sigma1_y, float sigma1_z, float sigma2_x, float sigma2_y, float sigma2_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.difference_of_gaussian(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), sigma1_x, sigma1_y, sigma1_z, sigma2_x, sigma2_y, sigma2_z), device);
    }
    
	/**
	 * Takes a label map image and dilates the regions using a octagon shape until they touch.
	 * The resulting label map is written to the output.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_extendLabelingViaVoronoi
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ extendLabelingViaVoronoi(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.extend_labeling_via_voronoi(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Computes the negative value of all pixels in a given image.
	 * It is recommended to convert images to 32bit float before applying this operation.
	 * <pre>f(x) = x</pre> For binary images, use binaryNot.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_invert
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ invert(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.invert(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Transforms a binary image with single pixles set to 1 to a labelled spots image.
	 * Transforms a spots image as resulting from maximum/minimum detection in an image of the same size where every spot has a number 1, 2,.
	 * n.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_labelSpots
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ labelSpots(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.label_spots(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Determines the Hessian eigenvalues and returns the large eigenvalue image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ largeHessianEigenvalue(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.large_hessian_eigenvalue(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Determines the maximum of all pixels in a given image.
	 * It will be stored in a new row of ImageJs Results table in the column 'Max'.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @return float
	 * @see https://clij.github.io/clij2-docs/reference_maximumOfAllPixels
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float maximumOfAllPixels(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return net.clesperanto._internals.kernelj.Tier2.maximum_of_all_pixels(device.getRaw(), input.getRaw());
    }
    
	/**
	 * Determines the minimum of all pixels in a given image.
	 * It will be stored in a new row of ImageJs Results table in the column 'Min'.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @return float
	 * @see https://clij.github.io/clij2-docs/reference_minimumOfAllPixels
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float minimumOfAllPixels(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return net.clesperanto._internals.kernelj.Tier2.minimum_of_all_pixels(device.getRaw(), input.getRaw());
    }
    
	/**
	 * Determines the minimum intensity in a masked image.
	 * But only in pixels which have nonzero values in another mask image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param mask ({@link ArrayJ}) - Input
	 * @return float
	 * @see https://clij.github.io/clij2-docs/reference_minimumOfMaskedPixels
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float minimumOfMaskedPixels(DeviceJ device, ArrayJ input, ArrayJ mask) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
		Objects.requireNonNull(mask, "mask cannot be null");
        return net.clesperanto._internals.kernelj.Tier2.minimum_of_masked_pixels(device.getRaw(), input.getRaw(), mask.getRaw());
    }
    
	/**
	 * Opening operator, boxshaped Applies morphological opening to intensity images using a boxshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 0)
	 * @param radius_y (int) - Radius along the y axis. (default: 0)
	 * @param radius_z (int) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ openingBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.opening_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Opening operator, sphereshaped Applies morphological opening to intensity images using a sphereshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (float) - Radius along the x axis. (default: 1)
	 * @param radius_y (float) - Radius along the y axis. (default: 1)
	 * @param radius_z (float) - Radius along the z axis. (default: 0)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ openingSphere(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.opening_sphere(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Opening operator, sphereshaped Applies morphological opening to intensity images using a sphereshaped footprint.
	 * This operator also works with binary images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (float) - Radius along the x axis. (default: 1)
	 * @param radius_y (float) - Radius along the y axis. (default: 1)
	 * @param radius_z (float) - Radius along the z axis. (default: 0)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ opening(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.opening(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Converts radians to degrees.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ radiansToDegrees(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.radians_to_degrees(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Takes a label map and reduces all labels to their edges.
	 * Label IDs stay and background will be zero.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_reduceLabelsToLabelEdges
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ reduceLabelsToLabelEdges(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.reduce_labels_to_label_edges(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Determines the Hessian eigenvalues and returns the small eigenvalue image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ smallHessianEigenvalue(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.small_hessian_eigenvalue(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Return the elementwise square of the input.
	 * This function is supposed to be similar to its counterpart in numpy [1].
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://numpy.org/doc/stable/reference/generated/numpy.square.html
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ square(DeviceJ device, ArrayJ input, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.square(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Determines the squared difference pixel by pixel between two images.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input image.
	 * @param input1 ({@link ArrayJ}) - Second input image.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_squaredDifference
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ squaredDifference(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.squared_difference(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Computes the local standard deviation of a pixels box neighborhood.
	 * The box size is specified by its halfwidth, halfheight and halfdepth (radius).
	 * If 2D images are given, radius_z will be ignored.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 1)
	 * @param radius_y (int) - Radius along the y axis. (default: 1)
	 * @param radius_z (int) - Radius along the z axis. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_standardDeviationBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ standardDeviationBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.standard_deviation_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Computes the local standard deviation of a pixels sphere neighborhood.
	 * The box size is specified by its halfwidth, halfheight and halfdepth (radius).
	 * If 2D images are given, radius_z will be ignored.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 1)
	 * @param radius_y (int) - Radius along the y axis. (default: 1)
	 * @param radius_z (int) - Radius along the z axis. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_standardDeviationSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ standardDeviationSphere(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.standard_deviation_sphere(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Computes the local standard deviation of a pixels sphere neighborhood.
	 * The box size is specified by its halfwidth, halfheight and halfdepth (radius).
	 * If 2D images are given, radius_z will be ignored.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param radius_x (int) - Radius along the x axis. (default: 1)
	 * @param radius_y (int) - Radius along the y axis. (default: 1)
	 * @param radius_z (int) - Radius along the z axis. (default: 1)
	 * @param connectivity (String) - Neigborhood shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_standardDeviationBox
	 * @see https://clij.github.io/clij2-docs/reference_standardDeviationSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ standardDeviation(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.standard_deviation(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
	/**
	 * Applies Gaussian blur to the input image and subtracts the result from the original.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @param sigma_x (float) - Radius along the x axis. (default: 2)
	 * @param sigma_y (float) - Radius along the y axis. (default: 2)
	 * @param sigma_z (float) - Radius along the z axis. (default: 2)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_subtractGaussianBackground
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ subtractGaussianBackground(DeviceJ device, ArrayJ input, ArrayJ output, float sigma_x, float sigma_y, float sigma_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.subtract_gaussian_background(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), sigma_x, sigma_y, sigma_z), device);
    }
    
	/**
	 * Subtracts one image X from another image Y pixel wise.
	 * <pre>f(x, y) = x y</pre>.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input0 ({@link ArrayJ}) - First input image.
	 * @param input1 ({@link ArrayJ}) - Second input image.
	 * @param output ({@link ArrayJ}) - Output result image. (default: None)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_subtractImages
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ subtractImages(DeviceJ device, ArrayJ input0, ArrayJ input1, ArrayJ output) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input0, "input0 cannot be null");
		Objects.requireNonNull(input1, "input1 cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.subtract_images(device.getRaw(), input0.getRaw(), input1.getRaw(), output == null ? null : output.getRaw()), device);
    }
    
	/**
	 * Determines the sum of all pixels in a given image.
	 * It will be stored in a new row of ImageJs Results table in the column 'Sum'.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - Input image to process. (default: None)
	 * @return float
	 * @see https://clij.github.io/clij2-docs/reference_sumOfAllPixels
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static float sumOfAllPixels(DeviceJ device, ArrayJ input) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return net.clesperanto._internals.kernelj.Tier2.sum_of_all_pixels(device.getRaw(), input == null ? null : input.getRaw());
    }
    
	/**
	 * Applies a tophat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (int) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (int) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (int) - Radius of the background determination region in Z. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_topHatBox
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ topHatBox(DeviceJ device, ArrayJ input, ArrayJ output, int radius_x, int radius_y, int radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.top_hat_box(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Applies a tophat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (float) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (float) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (float) - Radius of the background determination region in Z. (default: 1)
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_topHatSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
	@Deprecated
    public static ArrayJ topHatSphere(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.top_hat_sphere(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z), device);
    }
    
	/**
	 * Applies a tophat filter for background subtraction to the input image.
	 * @param device ({@link DeviceJ}) - Device to perform the operation on.
	 * @param input ({@link ArrayJ}) - The input image where the background is subtracted from.
	 * @param output ({@link ArrayJ}) - The output image where results are written into. (default: None)
	 * @param radius_x (float) - Radius of the background determination region in X. (default: 1)
	 * @param radius_y (float) - Radius of the background determination region in Y. (default: 1)
	 * @param radius_z (float) - Radius of the background determination region in Z. (default: 1)
	 * @param connectivity (String) - Element shape, "box" or "sphere" (default: "box")
	 * @return {@link ArrayJ}
	 * @see https://clij.github.io/clij2-docs/reference_topHatBox
	 * @see https://clij.github.io/clij2-docs/reference_topHatSphere
	 * @throws NullPointerException if any of the device or input parameters are null.
	 */
    public static ArrayJ topHat(DeviceJ device, ArrayJ input, ArrayJ output, float radius_x, float radius_y, float radius_z, String connectivity) {
        Objects.requireNonNull(device, "device cannot be null");
		Objects.requireNonNull(input, "input cannot be null");
        return new ArrayJ(net.clesperanto._internals.kernelj.Tier2.top_hat(device.getRaw(), input.getRaw(), output == null ? null : output.getRaw(), radius_x, radius_y, radius_z, connectivity), device);
    }
    
}
