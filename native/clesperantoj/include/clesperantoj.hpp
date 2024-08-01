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
    std::string getName() const;
    std::string getInfo() const;

    std::shared_ptr<cle::Device> get() const;
};
class ArrayJ
{

private:
    std::shared_ptr<cle::Array> array_;

    friend class MemoryJ;

protected:
    static ArrayJ create(size_t width, size_t height, size_t depth, size_t dimension, const cle::dType &data_type, const cle::mType &memory_type, const DeviceJ &device);
    void writeFrom(void *data, std::array<size_t, 3> &region, std::array<size_t, 3> &origin) const;
    void readTo(void *data, std::array<size_t, 3> &region, std::array<size_t, 3> &origin) const;

public:
    ArrayJ() = default;
    ArrayJ(const std::shared_ptr<cle::Array> &array);

    size_t getWidth() const;
    size_t getHeight() const;
    size_t getDepth() const;
    unsigned int getDimension() const;

    std::string getDataType() const;
    std::string getMemoryType() const;
    std::string getDevice() const;

    void fillMemory(float value);
    void copyDataTo(ArrayJ &dst);

    std::shared_ptr<cle::Array> get() const;
};

class MemoryJ
{
public:
    static ArrayJ makeFloatBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeUByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeUShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);
    static ArrayJ makeUIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type);

    static void writeFloatBuffer(const ArrayJ &array, float *data, const size_t &size);
    static void writeByteBuffer(const ArrayJ &array, char *data, const size_t &size);
    static void writeUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size);
    static void writeShortBuffer(const ArrayJ &array, short *data, const size_t &size);
    static void writeUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size);
    static void writeIntBuffer(const ArrayJ &array, int *data, const size_t &size);
    static void writeUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size);

    static void readFloatBuffer(const ArrayJ &array, float *data, const size_t &size);
    static void readByteBuffer(const ArrayJ &array, char *data, const size_t &size);
    static void readUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size);
    static void readShortBuffer(const ArrayJ &array, short *data, const size_t &size);
    static void readUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size);
    static void readIntBuffer(const ArrayJ &array, int *data, const size_t &size);
    static void readUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size);
};

class UtilsJ
{
public:
    static std::vector<std::string> getKeys(const std::unordered_map<std::string, std::vector<float>> &map);
    static std::vector<ArrayJ> toArrayJVector(const std::vector<std::shared_ptr<cle::Array>> &arr);
};

#endif // __INCLUDE_CLESPERANTOJ_HPP
