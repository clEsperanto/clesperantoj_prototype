#include <iostream>

#include "clesperanto.hpp"

#include <opencv2/core/core.hpp>
#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui/highgui.hpp>

using namespace std;
using namespace cv;

void testFilterFloat(Mat img, cle::Clesperanto cle) {

    uchar * data = img.data;

    std::vector<float> vecData;
    cout<<"rows "<<img.rows<<"\n";
    cout<<"cols"<<img.cols<<"\n";
    cout<<"rows*cols"<<img.rows*img.cols<<"\n"<<flush;

    vecData.resize(img.rows*img.cols);

    float * vecPointer = vecData.data();

    for (int i=0;i<img.rows*img.cols;i++) {
        vecPointer[i]=data[i];
    }
    
    imshow("Window", img);

    for (int i=0;i<1000;i++) {
        cout<<"test "<<i<<" "<<(int)(img.data[i])<<"\n"<<flush;
    }

    std::array<size_t,3> dimensions = {img.cols, img.rows, 1};
    auto in = cle.Push<float>(vecData, dimensions, "image");
    auto out = cle.Create<float>(dimensions,"image");

    //cle.ThresholdOtsu(in, out);
    cle.GaussianBlur(in, out, 3,3,1);
    std::vector<float> outVec=cle.Pull<float>(out); 
    
    float * temp = outVec.data();

    float * outData = new float[img.rows*img.cols];

    for (int i=0;i<img.rows*img.cols;i++) {
        outData[i]=temp[i]/255.;
    }

    Mat outMat(img.rows, img.cols, CV_32F, outData);

    imshow("Filtered", outMat);

    waitKey(0);

}

void testFilterShort(Mat img, cle::Clesperanto cle) {
  
    uchar * data = img.data;

    std::vector<short> vecData;
    cout<<"rows "<<img.rows<<"\n";
    cout<<"cols"<<img.cols<<"\n";
    cout<<"rows*cols"<<img.rows*img.cols<<"\n"<<flush;

    vecData.resize(img.rows*img.cols);

    short * vecPointer = vecData.data();

    for (int i=0;i<img.rows*img.cols;i++) {
        vecPointer[i]=data[i];
    }
    
    imshow("Window", img);

    for (int i=0;i<1000;i++) {
        cout<<"test "<<i<<" "<<(int)(img.data[i])<<"\n"<<flush;
    }

    std::array<size_t,3> dimensions = {img.cols, img.rows, 1};
    auto in = cle.Push<short>(vecData, dimensions, "image");
}

/**
 * Test clic
 **/
int main() {
    cout<<"Test clic\n"<<flush;

    // Initialisation of clEsperanto with default device
    cle::Clesperanto cle;

    // load test images. 
    Mat img = imread("C:/Users/bnort/work/ImageJ2022/clij/clesperantoj_prototype/imgs/boats.tif", IMREAD_GRAYSCALE);
 
    //testFilterFloat(img, cle);  
    testFilterShort(img, cle);  

} 