#ifndef __INCLUDE_KERNELJ_HPP
#define __INCLUDE_KERNELJ_HPP

#include "clesperantoj.hpp"

class Tier1
{
public:
    static ArrayJ absolute(const DeviceJ &dev, const ArrayJ &src, ArrayJ &dst);
    static ArrayJ absolute(const DeviceJ &dev, const ArrayJ &src);
    static void gaussianBlur(const DeviceJ &dev, const ArrayJ &src, const ArrayJ &dst, const float &sigmaX, const float &sigmaY, const float &sigmaZ);
    static void addImageAndScalar(const DeviceJ &dev, const ArrayJ &src, const ArrayJ &dst, const float &scalar);
};

#endif // __INCLUDE_KERNELJ_HPP
