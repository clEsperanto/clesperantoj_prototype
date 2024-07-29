#ifndef __INCLUDE_KERNELJ_HPP
#define __INCLUDE_KERNELJ_HPP

#include "clesperantoj.hpp"

class Tier1
{
public:
    static ArrayJ absolute( DeviceJ *dev, ArrayJ *src, ArrayJ *dst);
    static ArrayJ gaussianBlur( DeviceJ *dev, ArrayJ *src, ArrayJ *dst,  float sigmaX,  float sigmaY,  float sigmaZ);
    static ArrayJ addImageAndScalar( DeviceJ *dev, ArrayJ *src, ArrayJ *dst,  float scalar);
};

#endif // __INCLUDE_KERNELJ_HPP
