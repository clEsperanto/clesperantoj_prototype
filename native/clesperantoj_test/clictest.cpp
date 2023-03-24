#include <iostream>

#include "clesperantoj.hpp"

using namespace std;

int main()
{
    cout << "Hello World! (from native c++ clic test)" << endl;

    ClesperantoJInternal clesperanto;

    clesperanto.sayHello();

    clesperanto.getDeviceInfo();

    return 0;
}