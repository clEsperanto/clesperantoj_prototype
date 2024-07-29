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

std::string DeviceJ::getName()
{
    return this->device_->getName();
}

std::string DeviceJ::getInfo()
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

void ArrayJ::read(void *data) const
{
    this->array_->readTo(data);
}

void ArrayJ::write(void *data) const
{
    this->array_->writeFrom(data);
}

size_t ArrayJ::getWidth()
{
    return this->array_->width();
}

size_t ArrayJ::getHeight()
{
    return this->array_->height();
}

size_t ArrayJ::getDepth()
{
    return this->array_->depth();
}

unsigned int ArrayJ::getDimension()
{
    return this->array_->dimension();
}

std::string ArrayJ::getDataType()
{
    std::ostringstream oss;
    oss << this->array_->dtype();
    return oss.str();
}

std::string ArrayJ::getMemoryType()
{
    std::ostringstream oss;
    oss << this->array_->mtype();
    return oss.str();
}

std::string ArrayJ::getDevice()
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

// // void Array::getShape(size_t *shape)
// // {
// //     shape[0] = this->array_.width();
// //     shape[1] = this->array_.height();
// //     shape[2] = this->array_.width();
// // }

ArrayJ MemoryJ::makeFloatBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::FLOAT, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::FLOAT, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT8, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT8, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeUByteBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT8, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT8, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT16, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT16, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeUShortBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT16, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT16, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT32, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::INT32, cle::mType::BUFFER, device);
    }
}

ArrayJ MemoryJ::makeUIntBuffer(const DeviceJ &device, const size_t &width, const size_t &height, const size_t &depth, const size_t &dimension, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT32, cle::mType::IMAGE, device);
    }
    else
    {
        return ArrayJ::create(width, height, depth, dimension, cle::dType::UINT32, cle::mType::BUFFER, device);
    }
}

void MemoryJ::writeFloatBuffer(const ArrayJ &array, float *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeByteBuffer(const ArrayJ &array, char *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeShortBuffer(const ArrayJ &array, short *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeIntBuffer(const ArrayJ &array, int *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::writeUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size)
{
    array.write(static_cast<void *>(data));
}

void MemoryJ::readFloatBuffer(const ArrayJ &array, float *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readByteBuffer(const ArrayJ &array, char *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readUByteBuffer(const ArrayJ &array, unsigned char *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readShortBuffer(const ArrayJ &array, short *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readUShortBuffer(const ArrayJ &array, unsigned short *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readIntBuffer(const ArrayJ &array, int *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}

void MemoryJ::readUIntBuffer(const ArrayJ &array, unsigned int *data, const size_t &size)
{
    array.read(static_cast<void *>(data));
}
