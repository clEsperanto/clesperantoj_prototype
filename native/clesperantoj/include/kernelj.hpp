
/*
 * This file is autogenerated. Do not edit manually.
 */    
#ifndef __INCLUDE_KERNEL_HPP
#define __INCLUDE_KERNEL_HPP

#include "clesperantoj.hpp"


class Tier1
{
public:
    static ArrayJ absolute(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ add_images_weighted(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst, float factor1, float factor2);
	static ArrayJ add_image_and_scalar(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ binary_and(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ binary_edge_detection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ binary_not(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ binary_or(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ binary_subtract(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ binary_xor(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ binary_supinf(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ binary_infsup(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ block_enumerate(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst, int blocksize);
	static ArrayJ convolve(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ copy(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ copy_slice(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int slice_index);
	static ArrayJ copy_horizontal_slice(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int slice_index);
	static ArrayJ copy_vertical_slice(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int slice_index);
	static ArrayJ crop(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int start_x, int start_y, int start_z, int width, int height, int depth);
	static ArrayJ cubic_root(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ detect_label_edges(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ dilation(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst);
	static ArrayJ dilate_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ dilate_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ binary_dilate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ divide_images(DeviceJ * device, ArrayJ * dividend, ArrayJ * divisor, ArrayJ * dst);
	static ArrayJ divide_scalar_by_image(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ equal(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ equal_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ erosion(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst);
	static ArrayJ erode_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ erode_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ binary_erode(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ exponential(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ flip(DeviceJ * device, ArrayJ * src, ArrayJ * dst, bool flip_x, bool flip_y, bool flip_z);
	static ArrayJ gaussian_blur(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma_x, float sigma_y, float sigma_z);
	static ArrayJ generate_distance_matrix(DeviceJ * device, ArrayJ * coordinate_list1, ArrayJ * coordinate_list2, ArrayJ * distance_matrix_destination);
	static ArrayJ gradient_x(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ gradient_y(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ gradient_z(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ greater(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ greater_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ greater_or_equal(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ greater_or_equal_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static std::vector<ArrayJ> hessian_eigenvalues(DeviceJ * device, ArrayJ * src, ArrayJ * small_eigenvalue, ArrayJ * middle_eigenvalue, ArrayJ * large_eigenvalue);
	static ArrayJ laplace_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ laplace_diamond(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ laplace(DeviceJ * device, ArrayJ * src, ArrayJ * dst, std::string connectivity);
	static ArrayJ local_cross_correlation(DeviceJ * device, ArrayJ * src, ArrayJ * kernel, ArrayJ * dst);
	static ArrayJ logarithm(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mask(DeviceJ * device, ArrayJ * src, ArrayJ * mask, ArrayJ * dst);
	static ArrayJ mask_label(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst, float label);
	static ArrayJ maximum_image_and_scalar(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ maximum_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ maximum_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ maximum_filter(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ grayscale_dilate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ maximum_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ maximum_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ maximum_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mean_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ mean_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ mean_filter(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ mean_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mean_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mean_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ median_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ median_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ median(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ minimum_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ minimum_filter(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ grayscale_erode(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ minimum_image_and_scalar(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ minimum_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ minimum_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ minimum_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ minimum_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mode_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ mode_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ mode(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ modulo_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ multiply_image_and_position(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int dimension);
	static ArrayJ multiply_image_and_scalar(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ multiply_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ nan_to_num(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float nan, float posinf, float neginf);
	static ArrayJ nonzero_maximum_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1);
	static ArrayJ nonzero_maximum_diamond(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1);
	static ArrayJ nonzero_maximum(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1, std::string connectivity);
	static ArrayJ nonzero_minimum_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1);
	static ArrayJ nonzero_minimum_diamond(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1);
	static ArrayJ nonzero_minimum(DeviceJ * device, ArrayJ * src, ArrayJ * dst0, ArrayJ * dst1, std::string connectivity);
	static ArrayJ not_equal(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ not_equal_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ paste(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int destination_x, int destination_y, int destination_z);
	static ArrayJ onlyzero_overwrite_maximum_box(DeviceJ * device, ArrayJ * src, ArrayJ * flag, ArrayJ * dst);
	static ArrayJ onlyzero_overwrite_maximum_diamond(DeviceJ * device, ArrayJ * src, ArrayJ * flag, ArrayJ * dst);
	static ArrayJ onlyzero_overwrite_maximum(DeviceJ * device, ArrayJ * src, ArrayJ * flag, ArrayJ * dst, std::string connectivity);
	static ArrayJ power(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ power_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ range(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int start_x, int stop_x, int step_x, int start_y, int stop_y, int step_y, int start_z, int stop_z, int step_z);
	static ArrayJ read_values_from_positions(DeviceJ * device, ArrayJ * src, ArrayJ * list, ArrayJ * dst);
	static ArrayJ replace_values(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ replace_value(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float value_to_replace, float value_replacement);
	static ArrayJ replace_intensity(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float value_to_replace, float value_replacement);
	static ArrayJ replace_intensities(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ maximum_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ minimum_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ multiply_matrix(DeviceJ * device, ArrayJ * matrix1, ArrayJ * matrix2, ArrayJ * matrix_destination);
	static ArrayJ reciprocal(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ set(DeviceJ * device, ArrayJ * src, float scalar);
	static ArrayJ set_column(DeviceJ * device, ArrayJ * src, int column_index, float value);
	static ArrayJ set_image_borders(DeviceJ * device, ArrayJ * src, float value);
	static ArrayJ set_plane(DeviceJ * device, ArrayJ * src, int plane_index, float value);
	static ArrayJ set_ramp_x(DeviceJ * device, ArrayJ * src);
	static ArrayJ set_ramp_y(DeviceJ * device, ArrayJ * src);
	static ArrayJ set_ramp_z(DeviceJ * device, ArrayJ * src);
	static ArrayJ set_row(DeviceJ * device, ArrayJ * src, int row_index, float value);
	static ArrayJ set_nonzero_pixels_to_pixelindex(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int offset);
	static ArrayJ set_where_x_equals_y(DeviceJ * device, ArrayJ * src, float value);
	static ArrayJ set_where_x_greater_than_y(DeviceJ * device, ArrayJ * src, float value);
	static ArrayJ set_where_x_smaller_than_y(DeviceJ * device, ArrayJ * src, float value);
	static ArrayJ sign(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ smaller(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ smaller_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ smaller_or_equal(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ smaller_or_equal_constant(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ sobel(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ square_root(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ std_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ subtract_image_from_scalar(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float scalar);
	static ArrayJ sum_reduction_x(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int blocksize);
	static ArrayJ sum_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ sum_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ sum_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ transpose_xy(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ transpose_xz(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ transpose_yz(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ undefined_to_zero(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ variance_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ variance_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ variance_filter(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ write_values_to_positions(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ x_position_of_maximum_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ x_position_of_minimum_x_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ y_position_of_maximum_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ y_position_of_minimum_y_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ z_position_of_maximum_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ z_position_of_minimum_z_projection(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
};


class Tier2
{
public:
    static ArrayJ absolute_difference(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ add_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ bottom_hat_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ bottom_hat_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ bottom_hat(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ clip(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float min_intensity, float max_intensity);
	static ArrayJ closing_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius_x, int radius_y, int radius_z);
	static ArrayJ closing_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ grayscale_closing(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ closing(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst);
	static ArrayJ binary_closing(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ concatenate_along_x(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ concatenate_along_y(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ concatenate_along_z(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ count_touching_neighbors(DeviceJ * device, ArrayJ * touch_matrix, ArrayJ * touching_neighbors_count_destination, bool ignore_background);
	static ArrayJ crop_border(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int border_size);
	static ArrayJ divide_by_gaussian_background(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma_x, float sigma_y, float sigma_z);
	static ArrayJ degrees_to_radians(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ detect_maxima_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ detect_maxima(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ detect_minima_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ detect_minima(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ difference_of_gaussian(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma1_x, float sigma1_y, float sigma1_z, float sigma2_x, float sigma2_y, float sigma2_z);
	static ArrayJ extend_labeling_via_voronoi(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ invert(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ label_spots(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ large_hessian_eigenvalue(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static float maximum_of_all_pixels(DeviceJ * device, ArrayJ * src);
	static float minimum_of_all_pixels(DeviceJ * device, ArrayJ * src);
	static float minimum_of_masked_pixels(DeviceJ * device, ArrayJ * src, ArrayJ * mask);
	static ArrayJ opening_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ opening_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ grayscale_opening(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ opening(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst);
	static ArrayJ binary_opening(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ radians_to_degrees(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ reduce_labels_to_label_edges(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ small_hessian_eigenvalue(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ square(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ squared_difference(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ standard_deviation_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ standard_deviation_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ standard_deviation(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
	static ArrayJ subtract_gaussian_background(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma_x, float sigma_y, float sigma_z);
	static ArrayJ subtract_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ sub_stack(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int start_z, int end_z);
	static ArrayJ reduce_stack(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int reduction_factor, int offset);
	static float sum_of_all_pixels(DeviceJ * device, ArrayJ * src);
	static ArrayJ top_hat_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ top_hat_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z);
	static ArrayJ top_hat(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity);
};


class Tier3
{
public:
    static std::vector<float> bounding_box(DeviceJ * device, ArrayJ * src);
	static std::vector<float> center_of_mass(DeviceJ * device, ArrayJ * src);
	static ArrayJ remove_labels(DeviceJ * device, ArrayJ * src, ArrayJ * list, ArrayJ * dst);
	static ArrayJ exclude_labels(DeviceJ * device, ArrayJ * src, ArrayJ * list, ArrayJ * dst);
	static ArrayJ remove_labels_on_edges(DeviceJ * device, ArrayJ * src, ArrayJ * dst, bool exclude_x, bool exclude_y, bool exclude_z);
	static ArrayJ exclude_labels_on_edges(DeviceJ * device, ArrayJ * src, ArrayJ * dst, bool exclude_x, bool exclude_y, bool exclude_z);
	static ArrayJ flag_existing_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ gamma_correction(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float gamma);
	static ArrayJ generate_binary_overlap_matrix(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ generate_touch_matrix(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ histogram(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int num_bins, float minimum_intensity, float maximum_intensity);
	static float jaccard_index(DeviceJ * device, ArrayJ * src0, ArrayJ * src1);
	static ArrayJ labelled_spots_to_pointlist(DeviceJ * device, ArrayJ * label, ArrayJ * pointlist);
	static std::vector<float> maximum_position(DeviceJ * device, ArrayJ * src);
	static float mean_of_all_pixels(DeviceJ * device, ArrayJ * src);
	static std::vector<float> minimum_position(DeviceJ * device, ArrayJ * src);
	static ArrayJ morphological_chan_vese(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int num_iter, int smoothing, float lambda1, float lambda2);
	static std::unordered_map<std::string, std::vector<float>> statistics_of_labelled_pixels(DeviceJ * device, ArrayJ * intensity, ArrayJ * label);
	static std::unordered_map<std::string, std::vector<float>> statistics_of_background_and_labelled_pixels(DeviceJ * device, ArrayJ * intensity, ArrayJ * label);
};


class Tier4
{
public:
    static std::vector<float> label_bounding_box(DeviceJ * device, ArrayJ * src, int label_id);
	static float mean_squared_error(DeviceJ * device, ArrayJ * src0, ArrayJ * src1);
	static ArrayJ spots_to_pointlist(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ relabel_sequential(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int blocksize);
	static ArrayJ threshold_otsu(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ mean_intensity_map(DeviceJ * device, ArrayJ * src, ArrayJ * labels, ArrayJ * dst);
	static ArrayJ pixel_count_map(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ label_pixel_count_map(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ centroids_of_labels(DeviceJ * device, ArrayJ * label_image, ArrayJ * centroids_coordinates, bool include_background);
	static ArrayJ remove_labels_with_map_values_out_of_range(DeviceJ * device, ArrayJ * src, ArrayJ * values, ArrayJ * dst, float min_value, float max_value);
	static ArrayJ remove_labels_with_map_values_within_range(DeviceJ * device, ArrayJ * src, ArrayJ * values, ArrayJ * dst, float min_value, float max_value);
	static ArrayJ exclude_labels_with_map_values_out_of_range(DeviceJ * device, ArrayJ * values_map, ArrayJ * label_map_input, ArrayJ * dst, float minimum_value_range, float maximum_value_range);
	static ArrayJ exclude_labels_with_map_values_within_range(DeviceJ * device, ArrayJ * values_map, ArrayJ * label_map_input, ArrayJ * dst, float minimum_value_range, float maximum_value_range);
	static ArrayJ extension_ratio_map(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
};


class Tier5
{
public:
    static bool array_equal(DeviceJ * device, ArrayJ * src0, ArrayJ * src1);
	static ArrayJ combine_labels(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst);
	static ArrayJ connected_components_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, std::string connectivity);
	static ArrayJ connected_component_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, std::string connectivity);
	static ArrayJ reduce_labels_to_centroids(DeviceJ * device, ArrayJ * src, ArrayJ * dst);
	static ArrayJ filter_label_by_size(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float minimum_size, float maximum_size);
	static ArrayJ exclude_labels_outside_size_range(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float minimum_size, float maximum_size);
};


class Tier6
{
public:
    static ArrayJ dilate_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
	static ArrayJ erode_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius, bool relabel);
	static ArrayJ gauss_otsu_labeling(DeviceJ * device, ArrayJ * src0, ArrayJ * dst, float outline_sigma);
	static ArrayJ masked_voronoi_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * mask, ArrayJ * dst);
	static ArrayJ voronoi_labeling(DeviceJ * device, ArrayJ * input_binary, ArrayJ * output_labels);
	static ArrayJ remove_small_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float minimum_size);
	static ArrayJ exclude_small_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float maximum_size);
	static ArrayJ remove_large_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float maximum_size);
	static ArrayJ exclude_large_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float minimum_size);
};


class Tier7
{
public:
    static ArrayJ affine_transform(DeviceJ * device, ArrayJ * src, ArrayJ * dst, std::vector<float> * transform_matrix, bool interpolate, bool resize);
	static ArrayJ eroded_otsu_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int number_of_erosions, float outline_sigma);
	static ArrayJ rigid_transform(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float translate_x, float translate_y, float translate_z, float angle_x, float angle_y, float angle_z, bool centered, bool interpolate, bool resize);
	static ArrayJ rotate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float angle_x, float angle_y, float angle_z, bool centered, bool interpolate, bool resize);
	static ArrayJ scale(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float factor_x, float factor_y, float factor_z, bool centered, bool interpolate, bool resize);
	static ArrayJ translate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float translate_x, float translate_y, float translate_z, bool interpolate);
	static ArrayJ closing_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
	static ArrayJ erode_connected_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
	static ArrayJ opening_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
	static ArrayJ voronoi_otsu_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float spot_sigma, float outline_sigma);
};


class Tier8
{
public:
    static ArrayJ smooth_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
	static ArrayJ smooth_connected_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius);
};


#endif // __INCLUDE_KERNEL_HPP
