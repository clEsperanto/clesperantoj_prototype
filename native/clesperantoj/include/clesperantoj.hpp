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
    DeviceJ(const std::shared_ptr<cle::Device> &device);

    static std::vector<std::string> getAvailableDevices(const std::string &deviceType = "all");

    void setDevice(const std::string &deviceName = "", const std::string &deviceType = "all");
    std::string getName() const;
    std::string getInfo() const;

    std::shared_ptr<cle::Device> get() const;

    bool operator==(const DeviceJ& other) const;
};

enum class DTypeJ
{
    INT8,
    UINT8,
    INT16,
    UINT16,
    INT32,
    UINT32,
    FLOAT,
    UNKNOWN,

    INT = INT32,
    INDEX = UINT32,
    LABEL = UINT32,
    BINARY = UINT8,
};

enum class MTypeJ
{
    BUFFER,
    IMAGE
};

class ArrayJ
{

private:
    std::shared_ptr<cle::Array> array_;

    friend class MemoryJ;

protected:
    void writeFrom( void *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth) const;
    void readTo( void *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth) const;

public:
    ArrayJ() = default;
    ArrayJ(const std::shared_ptr<cle::Array> &array);

    size_t getWidth() const;
    size_t getHeight() const;
    size_t getDepth() const;
    unsigned int getDimension() const;

    void fillMemory(float value);
    void copyDataTo(ArrayJ &dst);

    std::shared_ptr<cle::Array> get() const;

    static ArrayJ create( const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const DTypeJ &data_type, const MTypeJ &memory_type, const DeviceJ &device);
    DTypeJ dtype() const;
    MTypeJ mtype() const;
    DeviceJ device() const;
};

class MemoryJ
{
public:
    static void writeFromFloat(const ArrayJ &array, float *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void writeFromByte(const ArrayJ &array, char *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void writeFromShort(const ArrayJ &array, short *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void writeFromInt(const ArrayJ &array, int *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);

    static void readToFloat(const ArrayJ &array, float *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void readToByte(const ArrayJ &array, char *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void readToShort(const ArrayJ &array, short *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
    static void readToInt(const ArrayJ &array, int *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth);
};

class UtilsJ
{
public:
    static std::vector<std::string> getKeys(const std::unordered_map<std::string, std::vector<float>> &map);
    static std::vector<ArrayJ> toArrayJVector(const std::vector<std::shared_ptr<cle::Array>> &arr);
};

#endif // __INCLUDE_CLESPERANTOJ_HPP
