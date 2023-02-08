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
    auto getWidth() -> size_t;
    auto getHeight() -> size_t;
    auto getDepth() -> size_t;
};

class ClesperantoJInternal
{

private:
    cle::Clesperanto cle;

public:
    ClesperantoJInternal();
    ~ClesperantoJInternal() = default;

    auto sayHello() -> void;
    auto getDeviceInfo() -> void;
};

#endif // __INCLUDE_CLESPERANTOJ_HPP
