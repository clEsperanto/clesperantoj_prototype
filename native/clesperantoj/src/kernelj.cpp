#include "kernelj.hpp"

#include "cleAddImageAndScalarKernel.hpp"

void Tier1::addImageAndScalar(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &scalar)
{
    cle::AddImageAndScalarKernel_Call(proc.getShared(), src.get(), dst.get(), scalar);
}