#include "clesperantoj.h"
#include <iostream>
#include <vector>

using namespace std;

ClesperantoJ::ClesperantoJ()
{}

void ClesperantoJ::sayHello() 
{
    std::cout<<"Hello Java, from c++\n";
}

void ClesperantoJ::gaussianBlur2d(float*in, float*out, int nx, int ny, float sx, float sy) {
    
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

void ClesperantoJ::gaussianBlur2d(ObjectJ in, ObjectJ out, float sx, float sy) {
    cle.GaussianBlur(in.obj, out.obj, sx, sy,1);
} 
