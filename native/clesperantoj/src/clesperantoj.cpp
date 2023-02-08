#include "clesperantoj.hpp"

#include <iostream>
#include <vector>

using namespace std;

auto ObjectJ::getWidth() -> int
{
    return obj.Shape()[0];
}
auto ObjectJ::getHeight() -> int
{
    return obj.Shape()[1];
}
auto ObjectJ::getDepth() -> int
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
