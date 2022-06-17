#include "clesperanto.hpp"

class ClesperantoJ
{

private:
    cle::Clesperanto cle;

public:
    ClesperantoJ();
    ~ClesperantoJ() = default;

    void sayHello();

    // non templated version of gaussian blur
    void gaussianBlur2d(float*in, float*out, int nr, int nc, float sx, float sy);

    // templated version
    template<typename T>
    void guassianBlur2dT(T*in, T*out, int nr, int nc, float sx, float sy);
};


template<typename T>
void ClesperantoJ::guassianBlur2dT(T*in, T*out, int nx, int ny, float sx, float sy)   {
    
    std::cout<<"test templated guassian blur\n"<<std::flush;

    std::vector<T> vecData;
    vecData.resize(nx*ny);

    T * vecPointer = vecData.data();

    for (int i=0;i<nx*ny;i++) {
        vecPointer[i]=in[i];
    }

    std::array<size_t,3> dimensions = {nx, ny, 1};
    auto inGPU = cle.Push<T>(vecData, dimensions, "image");
    auto outGPU = cle.Create<T>(dimensions,"image");
    
    cle.GaussianBlur(inGPU, outGPU, 3,3,1);
    std::vector<T> outVec=cle.Pull<T>(outGPU); 
    
    T * temp = outVec.data();

    for (int i=0;i<nx*ny;i++) {
        out[i]=temp[i];
    }
}
