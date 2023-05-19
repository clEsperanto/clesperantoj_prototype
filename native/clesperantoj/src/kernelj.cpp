#include "kernelj.hpp"

#include "cleKernelList.hpp"

void Tier1::addImageAndScalar(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &scalar)
{
    cle::AddImageAndScalarKernel_Call(proc.getShared(), src.get(), dst.get(), scalar);
}

void Tier1::gaussianBlur(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const float &sigmaX, const float &sigmaY, const float &sigmaZ)
{
    cle::GaussianBlurKernel_Call(proc.getShared(), src.get(), dst.get(), sigmaX, sigmaY, sigmaZ);
}

void Tier1::meanBox(const ProcessorJ &proc, const BufferJ &src, const BufferJ &dst, const int &radiusX, const float &radiusY, const float &radiusZ)
{
    cle::MeanBoxKernel_Call(proc.getShared(), src.get(), dst.get(), radiusX, radiusY, radiusZ);
}
