
#ifndef __INCLUDE_KERNELJ_HPP
#define __INCLUDE_KERNELJ_HPP

#include "clesperantoj.hpp"


class Tier1
{
public:
        static void absolute(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void addImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void addImagesWeighted(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst, const float & w1, const float & w2);
        static void binaryAnd(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void binaryEdgeDetection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void binaryNot(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void binaryOr(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void binarySubtract(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void binaryXor(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void blockEnumerate(const ProcessorJ & device, const BufferJ & src, const BufferJ & sum, const BufferJ & dst, const int & value);
        static void convolve(const ProcessorJ & device, const BufferJ & src, const BufferJ & convolve_kernel, const BufferJ & dst);
        static void copy(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void crop(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & index0, const int & index1, const int & index2);
        static void detectMaxima(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void dilateBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void dilateSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void divideImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & scalar);
        static void divideImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void equalConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void equal(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void erodeBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void erodeSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void flagExistingLabels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void gaussianBlur(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma_x, const float & sigma_y, const float & sigma_z);
        static void gradientX(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void gradientY(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void gradientZ(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void greaterConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void greater(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void greaterOrEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void greaterOrEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void laplaceBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void mask(const ProcessorJ & device, const BufferJ & src, const BufferJ & mask, const BufferJ & dst);
        static void maximumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z);
        static void maximumImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void maximumSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z);
        static void maximumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void maximumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void maximumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void meanBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z);
        static void meanSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z);
        static void minimumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z);
        static void minimumImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void minimumSphere(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius_x, const float & radius_y, const float & radius_z);
        static void minimumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void minimumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void minimumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void multiplyImageAndScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void multiplyImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void nonzeroMinimumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & flag);
        static void notEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void notEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void onlyzeroOverwriteMaximumBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst1, const BufferJ & dst2);
        static void onlyzeroOverwriteMaximumDiamond(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst1, const BufferJ & dst2);
        static void powerImages(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void power(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & scalar);
        static void replaceIntensities(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & map);
        static void replaceIntensity(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & in_value, const float & out_value);
        static void setColumn(const ProcessorJ & device, const BufferJ & src, const int & index, const float & value);
        static void set(const ProcessorJ & device, const BufferJ & src, const float & value);
        static void setNonzeroPixelsToPixelindex(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void smallerConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void smaller(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void smallerOrEqualConstant(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void smallerOrEqual(const ProcessorJ & device, const BufferJ & src1, const BufferJ & src2, const BufferJ & dst);
        static void sobel(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void subtractImageFromScalar(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & value);
        static void sumReductionX(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & value);
        static void sumXProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void sumYProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void sumZProjection(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
};
    

class Tier2
{
public:
        static void dilateLabels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & radius);
        static void extendLabelingViaVoronoi(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void maximumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void minimumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void sumOfAllPixels(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void topHatBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & radius_x, const int & radius_y, const int & radius_z);
};
    

class Tier3
{
public:
        static void closeIndexGapsInLabelMap(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & value);
        static void differenceOfGaussian(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma1_x, const float & sigma1_y, const float & sigma1_z, const float & sigma2_x, const float & sigma2_y, const float & sigma2_z);
        static void histogram(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const int & bin, const float & min_intensity, const float & max_intensity);
};
    

class Tier4
{
public:
        static void connectedComponentLabelingBox(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
        static void thresholdOtsu(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst);
};
    

class Tier5
{
public:
        static void maskedVoronoiLabeling(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const BufferJ & mask);
};
    

class Tier6
{
public:
        static void voronoiOtsuLabeling(const ProcessorJ & device, const BufferJ & src, const BufferJ & dst, const float & sigma1, const float & sigma2);
};
    

#endif // __INCLUDE_KERNELJ_HPP
    