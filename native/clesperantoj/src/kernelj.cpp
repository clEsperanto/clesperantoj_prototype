
#include "kernelj.hpp"

#include "cleKernelList.hpp"


    void Tier1::absolute(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::AbsoluteKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::addImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::AddImageAndScalarKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::addImagesWeighted(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst, const float & w1, const float & w2)
    {
        cle::AddImagesWeightedKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get(), w1, w2);
    }
    
    
    void Tier1::binaryAnd(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::BinaryAndKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::binaryEdgeDetection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::BinaryEdgeDetectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::binaryNot(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::BinaryNotKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::binaryOr(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::BinaryOrKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::binarySubtract(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::BinarySubtractKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::binaryXor(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::BinaryXorKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::blockEnumerate(const ProcessorJ & device, const BufferJ & src, const BufferJ & sum, const BufferJ & dst, const int & value)
    {
        cle::BlockEnumerateKernel_Call(device.getShared(), src.get(), sum.get(), dst.get(), value);
    }
    
    
    void Tier1::convolve(const ProcessorJ & device, const BufferJ & src, const BufferJ & convolve_kernel, const BufferJ & dst)
    {
        cle::ConvolveKernel_Call(device.getShared(), src.get(), convolve_kernel.get(), dst.get());
    }
    
    
    void Tier1::copy(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::CopyKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::crop(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & index0, const int & index1, const int & index2)
    {
        cle::CropKernel_Call(device.getShared(), src.get(), dst.get(), index0, index1, index2);
    }
    
    
    void Tier1::detectMaxima(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::DetectMaximaKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::dilateBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::DilateBoxKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::dilateSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::DilateSphereKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::divideImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & scalar)
    {
        cle::DivideImageAndScalarKernel_Call(device.getShared(), src.get(), dst.get(), scalar);
    }
    
    
    void Tier1::divideImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::DivideImagesKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::equalConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::EqualConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::equal(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::EqualKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::erodeBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::ErodeBoxKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::erodeSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::ErodeSphereKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::flagExistingLabels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::FlagExistingLabelsKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::gaussianBlur(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma_x, const float & sigma_y, const float & sigma_z)
    {
        cle::GaussianBlurKernel_Call(device.getShared(), src.get(), dst.get(), sigma_x, sigma_y, sigma_z);
    }
    
    
    void Tier1::gradientX(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::GradientXKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::gradientY(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::GradientYKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::gradientZ(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::GradientZKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::greaterConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::GreaterConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::greater(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::GreaterKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::greaterOrEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::GreaterOrEqualConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::greaterOrEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::GreaterOrEqualKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::laplaceBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::LaplaceBoxKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::mask(const ProcessorJ & device, const BufferJ & src, const BufferJ & mask, const BufferJ & dst)
    {
        cle::MaskKernel_Call(device.getShared(), src.get(), mask.get(), dst.get());
    }
    
    
    void Tier1::maximumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z)
    {
        cle::MaximumBoxKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::maximumImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::MaximumImagesKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::maximumSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z)
    {
        cle::MaximumSphereKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::maximumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MaximumXProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::maximumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MaximumYProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::maximumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MaximumZProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::meanBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z)
    {
        cle::MeanBoxKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::meanSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z)
    {
        cle::MeanSphereKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::minimumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z)
    {
        cle::MinimumBoxKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::minimumImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::MinimumImagesKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::minimumSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z)
    {
        cle::MinimumSphereKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    
    
    void Tier1::minimumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MinimumXProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::minimumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MinimumYProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::minimumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MinimumZProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::multiplyImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::MultiplyImageAndScalarKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::multiplyImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::MultiplyImagesKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::nonzeroMinimumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & flag)
    {
        cle::NonzeroMinimumBoxKernel_Call(device.getShared(), src.get(), dst.get(), flag.get());
    }
    
    
    void Tier1::notEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::NotEqualConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::notEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::NotEqualKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::onlyzeroOverwriteMaximumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst1, const BufferJ & dst2)
    {
        cle::OnlyzeroOverwriteMaximumBoxKernel_Call(device.getShared(), src.get(), dst1.get(), dst2.get());
    }
    
    
    void Tier1::onlyzeroOverwriteMaximumDiamond(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst1, const BufferJ & dst2)
    {
        cle::OnlyzeroOverwriteMaximumDiamondKernel_Call(device.getShared(), src.get(), dst1.get(), dst2.get());
    }
    
    
    void Tier1::powerImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::PowerImagesKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::power(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & scalar)
    {
        cle::PowerKernel_Call(device.getShared(), src.get(), dst.get(), scalar);
    }
    
    
    void Tier1::replaceIntensities(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & map)
    {
        cle::ReplaceIntensitiesKernel_Call(device.getShared(), src.get(), dst.get(), map.get());
    }
    
    
    void Tier1::replaceIntensity(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & in_value, const float & out_value)
    {
        cle::ReplaceIntensityKernel_Call(device.getShared(), src.get(), dst.get(), in_value, out_value);
    }
    
    
    void Tier1::setColumn(const ProcessorJ & device, const BufferJ & src, const int & index, const float & value)
    {
        cle::SetColumnKernel_Call(device.getShared(), src.get(), index, value);
    }
    
    
    void Tier1::set(const ProcessorJ & device, const BufferJ & src, const float & value)
    {
        cle::SetKernel_Call(device.getShared(), src.get(), value);
    }
    
    
    void Tier1::setNonzeroPixelsToPixelindex(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::SetNonzeroPixelsToPixelindexKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::smallerConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::SmallerConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::smaller(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::SmallerKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::smallerOrEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::SmallerOrEqualConstantKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::smallerOrEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst)
    {
        cle::SmallerOrEqualKernel_Call(device.getShared(), src1.get(), src2.get(), dst.get());
    }
    
    
    void Tier1::sobel(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::SobelKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::subtractImageFromScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value)
    {
        cle::SubtractImageFromScalarKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::sumReductionX(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & value)
    {
        cle::SumReductionXKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier1::sumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::SumXProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::sumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::SumYProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier1::sumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::SumZProjectionKernel_Call(device.getShared(), src.get(), dst.get());
    }
    

    void Tier2::dilateLabels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius)
    {
        cle::DilateLabelsKernel_Call(device.getShared(), src.get(), dst.get(), radius);
    }
    
    
    void Tier2::extendLabelingViaVoronoi(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::ExtendLabelingViaVoronoiKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier2::maximumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MaximumOfAllPixelsKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier2::minimumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::MinimumOfAllPixelsKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier2::sumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::SumOfAllPixelsKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier2::topHatBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z)
    {
        cle::TopHatBoxKernel_Call(device.getShared(), src.get(), dst.get(), radius_x, radius_y, radius_z);
    }
    

    void Tier3::closeIndexGapsInLabelMap(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & value)
    {
        cle::CloseIndexGapsInLabelMapKernel_Call(device.getShared(), src.get(), dst.get(), value);
    }
    
    
    void Tier3::differenceOfGaussian(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma1_x, const float & sigma1_y, const float & sigma1_z, const float & sigma2_x, const float & sigma2_y, const float & sigma2_z)
    {
        cle::DifferenceOfGaussianKernel_Call(device.getShared(), src.get(), dst.get(), sigma1_x, sigma1_y, sigma1_z, sigma2_x, sigma2_y, sigma2_z);
    }
    
    
    void Tier3::histogram(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const long & bin, const float & min_intensity, const float & max_intensity)
    {
        cle::HistogramKernel_Call(device.getShared(), src.get(), dst.get(), bin, min_intensity, max_intensity);
    }
    

    void Tier4::connectedComponentLabelingBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::ConnectedComponentLabelingBoxKernel_Call(device.getShared(), src.get(), dst.get());
    }
    
    
    void Tier4::thresholdOtsu(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst)
    {
        cle::ThresholdOtsuKernel_Call(device.getShared(), src.get(), dst.get());
    }
    

    void Tier5::maskedVoronoiLabeling(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & mask)
    {
        cle::MaskedVoronoiLabelingKernel_Call(device.getShared(), src.get(), dst.get(), mask.get());
    }
    

    void Tier6::voronoiOtsuLabeling(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma1, const float & sigma2)
    {
        cle::VoronoiOtsuLabelingKernel_Call(device.getShared(), src.get(), dst.get(), sigma1, sigma2);
    }
    
