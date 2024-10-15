
/*
 * This file is autogenerated. Do not edit manually.
 */
#include "kernelj.hpp"
#include "tier7.hpp"

ArrayJ Tier7::affine_transform(DeviceJ * device, ArrayJ * src, ArrayJ * dst, std::vector<float> * transform_matrix, bool interpolate, bool resize)
{
    return ArrayJ{cle::tier7::affine_transform_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), transform_matrix, interpolate, resize)};
}

ArrayJ Tier7::eroded_otsu_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int number_of_erosions, float outline_sigma)
{
    return ArrayJ{cle::tier7::eroded_otsu_labeling_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), number_of_erosions, outline_sigma)};
}

ArrayJ Tier7::rigid_transform(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float translate_x, float translate_y, float translate_z, float angle_x, float angle_y, float angle_z, bool centered, bool interpolate, bool resize)
{
    return ArrayJ{cle::tier7::rigid_transform_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), translate_x, translate_y, translate_z, angle_x, angle_y, angle_z, centered, interpolate, resize)};
}

ArrayJ Tier7::rotate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float angle_x, float angle_y, float angle_z, bool centered, bool interpolate, bool resize)
{
    return ArrayJ{cle::tier7::rotate_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), angle_x, angle_y, angle_z, centered, interpolate, resize)};
}

ArrayJ Tier7::scale(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float factor_x, float factor_y, float factor_z, bool centered, bool interpolate, bool resize)
{
    return ArrayJ{cle::tier7::scale_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), factor_x, factor_y, factor_z, centered, interpolate, resize)};
}

ArrayJ Tier7::translate(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float translate_x, float translate_y, float translate_z, bool interpolate)
{
    return ArrayJ{cle::tier7::translate_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), translate_x, translate_y, translate_z, interpolate)};
}

ArrayJ Tier7::closing_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius)
{
    return ArrayJ{cle::tier7::closing_labels_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius)};
}

ArrayJ Tier7::erode_connected_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius)
{
    return ArrayJ{cle::tier7::erode_connected_labels_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius)};
}

ArrayJ Tier7::opening_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius)
{
    return ArrayJ{cle::tier7::opening_labels_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius)};
}

ArrayJ Tier7::voronoi_otsu_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst, float spot_sigma, float outline_sigma)
{
    return ArrayJ{cle::tier7::voronoi_otsu_labeling_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), spot_sigma, outline_sigma)};
}

