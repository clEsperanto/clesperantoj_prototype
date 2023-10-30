#include "kernelj.hpp"
#include "tier1.hpp"

void Tier1::absolute(const DeviceJ &dev, const ArrayJ &src, const ArrayJ &dst)
{
    cle::tier1::absolute_func(dev.get(), src.get(), dst.get());
}

void Tier1::gaussianBlur(const DeviceJ &dev, const ArrayJ &src, const ArrayJ &dst, const float &sigmaX, const float &sigmaY, const float &sigmaZ)
{
    cle::tier1::gaussian_blur_func(dev.get(), src.get(), dst.get(), sigmaX, sigmaY, sigmaZ);
}

void Tier1::addImageAndScalar(const DeviceJ &dev, const ArrayJ &src, const ArrayJ &dst, const float &scalar)
{
    cle::tier1::add_image_and_scalar_func(dev.get(), src.get(), dst.get(), scalar);
}

