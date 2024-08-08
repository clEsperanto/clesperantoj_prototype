
#include "kernelj.hpp"
#include "tier4.hpp"

std::vector<float> Tier4::label_bounding_box(DeviceJ * device, ArrayJ * src, int label_id)
{
    return cle::tier4::label_bounding_box_func(device->get(), src->get(), label_id);
}

float Tier4::mean_squared_error(DeviceJ * device, ArrayJ * src0, ArrayJ * src1)
{
    return cle::tier4::mean_squared_error_func(device->get(), src0->get(), src1->get());
}

ArrayJ Tier4::spots_to_pointlist(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier4::spots_to_pointlist_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

ArrayJ Tier4::relabel_sequential(DeviceJ * device, ArrayJ * src, ArrayJ * dst, int blocksize)
{
    return ArrayJ{cle::tier4::relabel_sequential_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get(), blocksize)};
}

ArrayJ Tier4::threshold_otsu(DeviceJ * device, ArrayJ * src, ArrayJ * dst)
{
    return ArrayJ{cle::tier4::threshold_otsu_func(device->get(), src->get(), dst == nullptr ? nullptr : dst->get())};
}

