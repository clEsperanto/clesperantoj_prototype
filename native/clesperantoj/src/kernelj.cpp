#include "kernelj.hpp"
#include "tier1.hpp"

ArrayJ Tier1::absolute( DeviceJ* dev,  ArrayJ* src, ArrayJ* dst)
{
    return ArrayJ{cle::tier1::absolute_func(dev->get(), src->get(), dst ? dst->get() : nullptr)};
}

ArrayJ Tier1::gaussianBlur(DeviceJ* dev, ArrayJ* src, ArrayJ *dst, float sigmaX, float sigmaY, float sigmaZ)
{
    return ArrayJ{cle::tier1::gaussian_blur_func(dev->get(), src->get(), dst ? dst->get() : nullptr, sigmaX, sigmaY, sigmaZ)};
}

ArrayJ Tier1::addImageAndScalar(DeviceJ * dev, ArrayJ* src, ArrayJ* dst, float scalar)
{
    auto out = cle::tier1::add_image_and_scalar_func(dev->get(), src->get(), dst ? dst->get() : nullptr, scalar);
    return ArrayJ{out};
}
