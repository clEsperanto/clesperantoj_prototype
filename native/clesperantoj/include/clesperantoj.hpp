#ifndef __INCLUDE_CLESPERANTOJ_HPP
#define __INCLUDE_CLESPERANTOJ_HPP

#include "clesperanto.hpp"

/**
 * @brief
 * ObjectJ just wraps an CLIc Object
 *
 * This is done so we can store Clic Objects on the java side
 *
 * However the Object itself is private so the java side doesn't need to wrap all of Object (and thus much of cl.cpp)
 *
 * Note we declare ClesperantoJ as a friend class so that ClesperantoJ can access the Object
 */
class ObjectJ
{
    friend class ClesperantoJInternal;

private:
    cle::Image obj;

public:
    int getWidth();
    int getHeight();
    int getDepth();
};

class ClesperantoJInternal
{

private:
    cle::Clesperanto cle;

public:
    ClesperantoJInternal();
    ~ClesperantoJInternal() = default;

    void sayHello();

    // template <typename T>
    // ObjectJ create(int nx, int ny, int nz);

    // template <typename T>
    // ObjectJ push(T *in, int nx, int ny, int nz);

    // template <typename T>
    // void pull(T *out, ObjectJ obj);

    // ObjectJ gaussian_blur(ObjectJ source, ObjectJ target, float sigma_x, float sigma_y, float sigma_z);

    // ObjectJ threshold_otsu(ObjectJ source, ObjectJ target);
};

// template <typename T>
// ObjectJ ClesperantoJInternal::create(int nx, int ny, int nz)
// {

//     std::array<size_t, 3> dimensions = {nx, ny, nz};
//     auto obj = cle.Create<T>(dimensions, "image");

//     ObjectJ objJ = ObjectJ();
//     objJ.obj = obj;

//     return objJ;
// }

// template <typename T>
// ObjectJ ClesperantoJInternal::push(T *in, int nx, int ny, int nz)
// {

//     std::array<size_t, 3> dimensions = {nx, ny, nz};
//     std::vector<T> vecData;
//     vecData.resize(nx * ny);

//     T *vecPointer = vecData.data();

//     for (int i = 0; i < nx * ny; i++)
//     {
//         vecPointer[i] = in[i];
//     }

//     auto obj = cle.Push<T>(vecData, dimensions, "image");

//     ObjectJ objJ = ObjectJ();
//     objJ.obj = obj;

//     return objJ;
// }

// template <typename T>
// void ClesperantoJInternal::pull(T *out, ObjectJ obj)
// {
//     std::vector<T> outVec = cle.Pull<T>(obj.obj);

//     T *temp = outVec.data();

//     for (int i = 0; i < outVec.size(); i++)
//     {
//         out[i] = temp[i];
//     }
// }

#endif // __INCLUDE_CLESPERANTOJ_HPP
