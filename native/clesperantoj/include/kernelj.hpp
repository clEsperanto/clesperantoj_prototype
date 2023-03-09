#ifndef __INCLUDE_KERNELJ_HPP
#define __INCLUDE_KERNELJ_HPP

#include "clesperantoj.hpp"

class Tier1
{
public:
    static void addImageAndScalar(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &scalar);
};

#endif // __INCLUDE_KERNELJ_HPP
