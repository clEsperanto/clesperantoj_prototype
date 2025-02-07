
/*
 * This file is autogenerated. Do not edit manually.
 */
#include "kernelj.hpp"
#include "tier2.hpp"

ArrayJ Tier2::absolute_difference(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::absolute_difference_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::add_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::add_images_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::bottom_hat_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::bottom_hat_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::bottom_hat_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::bottom_hat_sphere_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::bottom_hat(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::bottom_hat_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::clip(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float min_intensity, float max_intensity)
{
    return ArrayJ{cle::tier2::clip_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), min_intensity, max_intensity)};
}

ArrayJ Tier2::closing_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius_x, int radius_y, int radius_z)
{
    return ArrayJ{cle::tier2::closing_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::closing_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::closing_sphere_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::grayscale_closing(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::grayscale_closing_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::closing(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::closing_func(device->get(), src->get(), footprint->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::binary_closing(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::binary_closing_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::concatenate_along_x(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::concatenate_along_x_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::concatenate_along_y(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::concatenate_along_y_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::concatenate_along_z(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::concatenate_along_z_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::count_touching_neighbors(DeviceJ * device, ArrayJ * touch_matrix, ArrayJ * touching_neighbors_count_destination, bool ignore_background)
{
    return ArrayJ{cle::tier2::count_touching_neighbors_func(device->get(), touch_matrix->get(), touching_neighbors_count_destination == nullptr ? nullptr : touching_neighbors_count_destination->get(), ignore_background)};
}

ArrayJ Tier2::crop_border(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int border_size)
{
    return ArrayJ{cle::tier2::crop_border_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), border_size)};
}

ArrayJ Tier2::divide_by_gaussian_background(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma_x, float sigma_y, float sigma_z)
{
    return ArrayJ{cle::tier2::divide_by_gaussian_background_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), sigma_x, sigma_y, sigma_z)};
}

ArrayJ Tier2::degrees_to_radians(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::degrees_to_radians_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::detect_maxima_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::detect_maxima_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::detect_maxima(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::detect_maxima_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::detect_minima_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::detect_minima_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::detect_minima(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::detect_minima_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::difference_of_gaussian(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma1_x, float sigma1_y, float sigma1_z, float sigma2_x, float sigma2_y, float sigma2_z)
{
    return ArrayJ{cle::tier2::difference_of_gaussian_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), sigma1_x, sigma1_y, sigma1_z, sigma2_x, sigma2_y, sigma2_z)};
}

ArrayJ Tier2::extend_labeling_via_voronoi(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::extend_labeling_via_voronoi_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::invert(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::invert_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::label_spots(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::label_spots_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::large_hessian_eigenvalue(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::large_hessian_eigenvalue_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

float Tier2::maximum_of_all_pixels(DeviceJ * device, ArrayJ * src)
{
    return cle::tier2::maximum_of_all_pixels_func(device->get(), src->get());
}

float Tier2::minimum_of_all_pixels(DeviceJ * device, ArrayJ * src)
{
    return cle::tier2::minimum_of_all_pixels_func(device->get(), src->get());
}

float Tier2::minimum_of_masked_pixels(DeviceJ * device, ArrayJ * src, ArrayJ * mask)
{
    return cle::tier2::minimum_of_masked_pixels_func(device->get(), src->get(), mask->get());
}

ArrayJ Tier2::opening_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::opening_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::opening_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::opening_sphere_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::grayscale_opening(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::grayscale_opening_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::opening(DeviceJ * device, ArrayJ * src, ArrayJ * footprint, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::opening_func(device->get(), src->get(), footprint->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::binary_opening(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::binary_opening_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::radians_to_degrees(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::radians_to_degrees_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::reduce_labels_to_label_edges(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::reduce_labels_to_label_edges_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::small_hessian_eigenvalue(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::small_hessian_eigenvalue_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::square(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::square_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::squared_difference(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::squared_difference_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::standard_deviation_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::standard_deviation_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::standard_deviation_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::standard_deviation_sphere_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::standard_deviation(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::standard_deviation_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

ArrayJ Tier2::subtract_gaussian_background(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float sigma_x, float sigma_y, float sigma_z)
{
    return ArrayJ{cle::tier2::subtract_gaussian_background_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), sigma_x, sigma_y, sigma_z)};
}

ArrayJ Tier2::subtract_images(DeviceJ * device, ArrayJ * src0, ArrayJ * src1, ArrayJ * dst)
{
    return ArrayJ{cle::tier2::subtract_images_func(device->get(), src0->get(), src1->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier2::sub_stack(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int start_z, int end_z)
{
    return ArrayJ{cle::tier2::sub_stack_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), start_z, end_z)};
}

ArrayJ Tier2::reduce_stack(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int reduction_factor, int offset)
{
    return ArrayJ{cle::tier2::reduce_stack_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), reduction_factor, offset)};
}

float Tier2::sum_of_all_pixels(DeviceJ * device, ArrayJ * src)
{
    return cle::tier2::sum_of_all_pixels_func(device->get(), src == nullptr ? nullptr : src->get());
}

ArrayJ Tier2::top_hat_box(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::top_hat_box_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::top_hat_sphere(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z)
{
    return ArrayJ{cle::tier2::top_hat_sphere_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z)};
}

ArrayJ Tier2::top_hat(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float radius_x, float radius_y, float radius_z, std::string connectivity)
{
    return ArrayJ{cle::tier2::top_hat_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius_x, radius_y, radius_z, connectivity)};
}

