#include "clesperantoj.hpp"

#include <iostream>
#include <vector>

using namespace std;

auto ObjectJ::getWidth() -> size_t
{
    return obj.Shape()[0];
}
auto ObjectJ::getHeight() -> size_t
{
    return obj.Shape()[1];
}
auto ObjectJ::getDepth() -> size_t
{
    return obj.Shape()[2];
}

ClesperantoJInternal::ClesperantoJInternal()
{
}

auto ClesperantoJInternal::sayHello() -> void
{
    std::cout << "Hello Java, from c++" << std::endl;
}

auto ClesperantoJInternal::getDeviceInfo() -> void
{
    std::cout << cle.Info() << std::endl;
}
