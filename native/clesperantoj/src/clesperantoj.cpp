#include "clesperantoj.hpp"

#include <sstream>

void BackendJ::setBackend(const std::string &backendName)
{
    if (backendName.find("cuda") != std::string::npos)
    {
        cle::BackendManager::getInstance().setBackend("cuda");
        std::cout << "Using CUDA backend" << std::endl;
    }
    else
    {
        cle::BackendManager::getInstance().setBackend("opencl");
        std::cout << "Using OpenCL backend" << std::endl;
    }
}

DeviceJ::DeviceJ()
{
    this->device_ = cle::BackendManager::getInstance().getBackend().getDevice("", "all");
}

DeviceJ::DeviceJ(const std::shared_ptr<cle::Device> &device) : device_(device)
{
}

bool DeviceJ::operator==(const DeviceJ& other) const
{
    return (device_ == other.device_);
}

std::vector<std::string> DeviceJ::getAvailableDevices(const std::string &deviceType)
{
    return cle::BackendManager::getInstance().getBackend().getDevicesList(deviceType);
}

void DeviceJ::setDevice(const std::string &deviceName, const std::string &deviceType)
{
    this->device_ = cle::BackendManager::getInstance().getBackend().getDevice(deviceName, deviceType);
}

std::string DeviceJ::getName() const
{
    return this->device_->getName();
}

std::string DeviceJ::getInfo() const
{
    return this->device_->getInfo();
}

std::shared_ptr<cle::Device> DeviceJ::get() const
{
    return this->device_;
}

ArrayJ::ArrayJ(const std::shared_ptr<cle::Array> &array) : array_(array)
{
}

size_t ArrayJ::getWidth() const
{
    return this->array_->width();
}

size_t ArrayJ::getHeight() const
{
    return this->array_->height();
}

size_t ArrayJ::getDepth() const
{
    return this->array_->depth();
}

unsigned int ArrayJ::getDimension() const
{
    return this->array_->dimension();
}

std::shared_ptr<cle::Array> ArrayJ::get() const
{
    return this->array_;
}

void ArrayJ::fillMemory(float value)
{
    this->array_->fill(value);
}

void ArrayJ::copyDataTo(ArrayJ &dst)
{
    this->array_->copyTo(dst.get());
}


std::vector<std::string> UtilsJ::getKeys(const std::unordered_map<std::string, std::vector<float>> &map)
{
    std::vector<std::string> keys;
    keys.reserve(map.size());
    std::transform(map.begin(), map.end(), std::back_inserter(keys),
                   [](const auto &element)
                   { return element.first; });
    return keys;
}

std::vector<ArrayJ> UtilsJ::toArrayJVector(const std::vector<std::shared_ptr<cle::Array>> &arr)
{
    std::vector<ArrayJ> result(arr.size());
    std::transform(arr.begin(), arr.end(), std::back_inserter(result), [](const std::shared_ptr<cle::Array> &value)
                   { return ArrayJ{value}; });
    return result;
}

inline cle::dType to_cle_dType(const DTypeJ &dtype)
{
    switch (dtype)
    {
        case DTypeJ::INT8:    return cle::dType::INT8;
        case DTypeJ::UINT8:   return cle::dType::UINT8;
        case DTypeJ::INT16:   return cle::dType::INT16;
        case DTypeJ::UINT16:  return cle::dType::UINT16;
        case DTypeJ::INT32:   return cle::dType::INT32;
        case DTypeJ::UINT32:  return cle::dType::UINT32;
        case DTypeJ::FLOAT:   return cle::dType::FLOAT;
        case DTypeJ::UNKNOWN:
        default:              return cle::dType::UNKNOWN;
    }
}

inline DTypeJ from_cle_dType(const cle::dType &dtype)
{
    switch (dtype)
    {
        case cle::dType::INT8:      return DTypeJ::INT8;
        case cle::dType::UINT8:     return DTypeJ::UINT8;
        case cle::dType::INT16:     return DTypeJ::INT16;
        case cle::dType::UINT16:    return DTypeJ::UINT16;
        case cle::dType::INT32:     return DTypeJ::INT32;
        case cle::dType::UINT32:    return DTypeJ::UINT32;
        case cle::dType::FLOAT:     return DTypeJ::FLOAT;
//        case cle::dType::UNKNOWN: // TODO: uncomment after https://github.com/clEsperanto/CLIc/pull/353 is merged, released, and we depend on that version
        default:              return DTypeJ::UNKNOWN;
    }
}

inline cle::mType to_cle_mType(const MTypeJ &mtype)
{
    switch (mtype)
    {
        case MTypeJ::IMAGE:  return cle::mType::IMAGE;
        case MTypeJ::BUFFER:
        default:             return cle::mType::BUFFER;
    }
}

inline MTypeJ from_cle_mType(const cle::mType &mtype)
{
    switch (mtype)
    {
        case cle::mType::IMAGE:  return MTypeJ::IMAGE;
        case cle::mType::BUFFER:
        default:                 return MTypeJ::BUFFER;
    }
}

ArrayJ ArrayJ::create(
    const size_t &width,
    const size_t &height,
    const size_t &depth,
    const size_t &dimension,
    const DTypeJ &data_type,
    const MTypeJ &memory_type,
    const DeviceJ &device)
{
    auto data = cle::Array::create(width, height, depth, dimension, to_cle_dType(data_type), to_cle_mType(memory_type), device.get());
    return ArrayJ{data};
}

void ArrayJ::writeFrom(
    void *data,
    const size_t &origin_x,
    const size_t &origin_y,
    const size_t &origin_z,
    const size_t &width,
    const size_t &height,
    const size_t &depth) const
{
    std::array<size_t, 3> origin = {origin_x, origin_y, origin_z};
    std::array<size_t, 3> region = {width, height, depth};
    this->array_->writeFrom(data, region, origin);
}

void ArrayJ::readTo(
    void *data,
    const size_t &origin_x,
    const size_t &origin_y,
    const size_t &origin_z,
    const size_t &width,
    const size_t &height,
    const size_t &depth) const
{
    std::array<size_t, 3> origin = {origin_x, origin_y, origin_z};
    std::array<size_t, 3> region = {width, height, depth};
    this->array_->readTo(data, region, origin);
}

DTypeJ ArrayJ::dtype() const
{
    return from_cle_dType(this->array_->dtype());
}

MTypeJ ArrayJ::mtype() const
{
    return from_cle_mType(this->array_->mtype());
}

DeviceJ ArrayJ::device() const
{
    return DeviceJ{this->array_->device()};
}

void MemoryJ::readToFloat(const ArrayJ &array, float *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.readTo(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::readToByte(const ArrayJ &array, char *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.readTo(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::readToShort(const ArrayJ &array, short *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.readTo(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::readToInt(const ArrayJ &array, int *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.readTo(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::writeFromFloat(const ArrayJ &array, float *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.writeFrom(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::writeFromByte(const ArrayJ &array, char *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.writeFrom(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::writeFromShort(const ArrayJ &array, short *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.writeFrom(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

void MemoryJ::writeFromInt(const ArrayJ &array, int *data, const size_t &origin_x, const size_t &origin_y, const size_t &origin_z, const size_t &width, const size_t &height, const size_t &depth)
{
    array.writeFrom(static_cast<void *>(data), origin_x, origin_y, origin_z, width, height, depth);
}

