#include "clesperantoj.hpp"

#include <iostream>
#include <vector>

using namespace std;

int ObjectJ::getWidth()
{
    return obj.Shape()[0];
}
int ObjectJ::getHeight()
{
    return obj.Shape()[1];
}
int ObjectJ::getDepth()
{
    return obj.Shape()[2];
}

ClesperantoJInternal::ClesperantoJInternal()
{
}

void ClesperantoJInternal::sayHello()
{
    std::cout << "Hello Java, from c++" << std::endl;
}

void ClesperantoJInternal::getDeviceInfo()
{
    std::cout << cle.Info() << std::endl;
}
