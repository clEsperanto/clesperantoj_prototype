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

ArrayJ ArrayJ::create(size_t width, size_t height, size_t depth, size_t dimension, const cle::dType &data_type, const cle::mType &memory_type, const DeviceJ &device)
{
    auto data = cle::Array::create(width, height, depth, dimension, data_type, memory_type, device.get());
    return ArrayJ{data};
}

void ArrayJ::readTo(void *data, std::array<size_t,3>& region, std::array<size_t,3>& origin) const
{
    this->array_->readTo(data, region, origin);
}

void ArrayJ::writeFrom(void *data, std::array<size_t,3>& region, std::array<size_t,3>& origin) const
{
    this->array_->writeFrom(data, region, origin);
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

std::string ArrayJ::getDataType() const
{
    std::ostringstream oss;
    oss << this->array_->dtype();
    return oss.str();
}

std::string ArrayJ::getMemoryType() const
{
    std::ostringstream oss;
    oss << this->array_->mtype();
    return oss.str();
}

std::string ArrayJ::getDevice() const
{
    return this->array_->device()->getName();
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

ArrayJ MemoryJ::makeFloatBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::FLOAT, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::INT8, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeUByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT8, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::INT16, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeUShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT16, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::INT32, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

ArrayJ MemoryJ::makeUIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT32, memory_type == "image" ? cle::mType::IMAGE : cle::mType::BUFFER, device);
}

void MemoryJ::writeFloatBuffer(const ArrayJ &array, float *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeByteBuffer(const ArrayJ &array, char *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeShortBuffer(const ArrayJ &array, short *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeIntBuffer(const ArrayJ &array, int *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::writeUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.writeFrom(static_cast<void *>(data), region, origin);
}

void MemoryJ::readFloatBuffer(const ArrayJ &array, float *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readByteBuffer(const ArrayJ &array, char *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readShortBuffer(const ArrayJ &array, short *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readIntBuffer(const ArrayJ &array, int *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}

void MemoryJ::readUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size)
{
    std::array<size_t,3> region = {array.getWidth(), array.getHeight(), array.getDepth()};
    std::array<size_t,3> origin = {0, 0, 0};
    array.readTo(static_cast<void *>(data), region, origin);
}
