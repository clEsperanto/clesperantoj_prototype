#include "clesperantoj.h"
#include <iostream>
#include <vector>

using namespace std;

int ObjectJ::getWidth() {
    return obj.Shape()[0];
}
int ObjectJ::getHeight() {
    return obj.Shape()[1];
}
int ObjectJ::getDepth() {
    return obj.Shape()[2];
}
const char* ObjectJ::getDataType() {
    return obj.GetDataType();
}

ClesperantoJInternal::ClesperantoJInternal()
{}

void ClesperantoJInternal::sayHello()
{
    std::cout<<"Hello Java, from c++\n";
}

void ClesperantoJInternal::gaussianBlur2d(float*in, float*out, int nx, int ny, float sx, float sy) {
    
    std::vector<float> vecData;
    vecData.resize(nx*ny);

    float * vecPointer = vecData.data();

    for (int i=0;i<nx*ny;i++) {
        vecPointer[i]=in[i];
    }

    std::array<size_t,3> dimensions = {(size_t)nx, (size_t)ny, 1};
    auto inGPU = cle.Push<float>(vecData, dimensions, "image");
    auto outGPU = cle.Create<float>(dimensions,"image");
    
    cle.GaussianBlur(inGPU, outGPU, 3,3,1);
    std::vector<float> outVec=cle.Pull<float>(outGPU); 
    
    float * temp = outVec.data();

    for (int i=0;i<nx*ny;i++) {
        out[i]=temp[i];
    }
}

void ClesperantoJInternal::gaussianBlur2d(ObjectJ in, ObjectJ out, float sx, float sy) {
    cle.GaussianBlur(in.obj, out.obj, sx, sy,1);
} 

ObjectJ ClesperantoJInternal::gaussian_blur(ObjectJ in, ObjectJ out, float sigma_x, float sigma_y, float sigma_z) {
    cle.GaussianBlur(in.obj, out.obj, sigma_x, sigma_y, sigma_z);
    return out;
}
