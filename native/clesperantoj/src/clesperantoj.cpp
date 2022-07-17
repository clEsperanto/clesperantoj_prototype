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

ObjectJ ClesperantoJInternal::gaussian_blur(ObjectJ source, ObjectJ target, float sigma_x, float sigma_y, float sigma_z) {
    cle.GaussianBlur(source.obj, target.obj, sigma_x, sigma_y, sigma_z);
    return target;
}

ObjectJ ClesperantoJInternal::threshold_otsu(ObjectJ source, ObjectJ target) {
    cle.ThresholdOtsu(source.obj, target.obj);
    return target;
}

ObjectJ ClesperantoJInternal::connected_component_labeling_box(ObjectJ source, ObjectJ target) {
    cle.ConnectedComponentsLabelingBox(source.obj, target.obj);
    return target;
}
