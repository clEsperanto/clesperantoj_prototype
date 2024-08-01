
#include "kernelj.hpp"
#include "tier6.hpp"

ArrayJ Tier6::dilate_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius)
{
    return ArrayJ{cle::tier6::dilate_labels_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius)};
}

ArrayJ Tier6::erode_labels(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int radius, bool relabel)
{
    return ArrayJ{cle::tier6::erode_labels_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), radius, relabel)};
}

ArrayJ Tier6::gauss_otsu_labeling(DeviceJ * device, ArrayJ * src0, ArrayJ * dst, float outline_sigma)
{
    return ArrayJ{cle::tier6::gauss_otsu_labeling_func(device->get(), src0->get(), dst == nullptr ? nullptr : dst->get(), outline_sigma)};
}

ArrayJ Tier6::masked_voronoi_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * mask, ArrayJ * dst)
{
    return ArrayJ{cle::tier6::masked_voronoi_labeling_func(device->get(), src->get(), mask->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier6::voronoi_labeling(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier6::voronoi_labeling_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

