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

    std::array<size_t,3> dimensions = {nx, ny, 1};
    auto inGPU = cle.Push<float>(vecData, dimensions, "image");
    auto outGPU = cle.Create<float>(dimensions,"image");
    
    cle.GaussianBlur(inGPU, outGPU, 3,3,1);
    std::vector<float> outVec=cle.Pull<float>(outGPU); 
    
    float * temp = outVec.data();

    for (int i=0;i<nx*ny;i++) {
        out[i]=temp[i];
    }
}

void ClesperantoJ::guassianBlur2dLongLong(long long in, long long out, int nx, int ny, float sx, float xy) {
    cl::Memory in_mem = cl::Memory((cl_mem)in,false);
    cl::Memory out_mem = cl::Memory((cl_mem)out,false);

    std::array<size_t,3> dimensions = {nx, ny, 1};
    cle::Object::DataType type=cle::Object::DataType::FLOAT;
    auto inGPU = cle.Wrap(in_mem, dimensions, "image");
    auto outGPU = cle.Wrap(out_mem, dimensions, "image");

    cle.GaussianBlur(inGPU, outGPU, 3,3,1);
    //Object(in_mem, dimensions, cle. Template2DataType<Type>());
    
    //cl::Memory out_mem = (cl::Memory)out;
}
