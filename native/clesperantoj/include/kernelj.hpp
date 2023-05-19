#ifndef __INCLUDE_KERNELJ_HPP
#define __INCLUDE_KERNELJ_HPP

#include "clesperantoj.hpp"

class Tier1
{
public:
    static void addImageAndScalar(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &scalar);
    static void gaussianBlur(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &sigmaX, const float &sigmaY, const float &sigmaZ);
    static void meanBox(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const int &radiusX, const float &radiusY, const float &radiusZ);
};

#endif // __INCLUDE_KERNELJ_HPP
