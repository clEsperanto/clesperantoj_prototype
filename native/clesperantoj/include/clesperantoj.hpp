#ifndef __INCLUDE_CLESPERANTOJ_HPP
#define __INCLUDE_CLESPERANTOJ_HPP

#include <memory>
#include <string>
#include <vector>

#include "array.hpp"
#include "backend.hpp"
#include "device.hpp"
#include "utils.hpp"

class BackendJ
{
public:
    static void setBackend(const std::string &backendName);
};

class DeviceJ
{
    friend class BackendJ;

private:
    std::shared_ptr<cle::Device> device_;

public:
    DeviceJ();

    static std::vector<std::string> getAvailableDevices(const std::string &deviceType = "all");

    void setDevice(const std::string &deviceName = "", const std::string &deviceType = "all");
    std::string getName();
    std::string getInfo();

    std::shared_ptr<cle::Device> get() const;
};
class ArrayJ
{

private:
    std::shared_ptr<cle::Array> array_;

    friend class MemoryJ;

protected:
    static ArrayJ create(size_t width, size_t height, size_t depth, size_t dimension, const cle::dType &data_type, const cle::mType &memory_type, const DeviceJ &device);
    void write(void *data) const;
    void read(void *data) const;

public:
    ArrayJ() = default;
    ArrayJ(const std::shared_ptr<cle::Array> &array);

    size_t getWidth();
    size_t getHeight();
    size_t getDepth();
    unsigned int getDimension();

    std::string getDataType();
    std::string getMemoryType();
    std::string getDevice();

    void fillMemory(float value);
    void copyDataTo(ArrayJ &dst);

    std::shared_ptr<cle::Array> get() const;
};

class MemoryJ
{
public:
    static ArrayJ makeFloatBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);

    static void writeFloatBuffer(const ArrayJ &array, float *data, const size_t &size);
    static void writeIntBuffer(const ArrayJ &array, int *data, const size_t &size);

    static void readFloatBuffer(const ArrayJ &array, float *data, const size_t &size);
    static void readIntBuffer(const ArrayJ &array, int *data, const size_t &size);
};

#endif // __INCLUDE_CLESPERANTOJ_HPP
