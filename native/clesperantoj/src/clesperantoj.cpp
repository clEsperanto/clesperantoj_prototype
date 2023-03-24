#include "clesperantoj.hpp"

#include "cleMemory.hpp"
#include "cleTypes.hpp"

ProcessorJ::ProcessorJ() : proc(cle::Processor(""))
{
}

ProcessorJ::ProcessorJ(const std::string &name) : proc(cle::Processor(name))
{
}

ProcessorJ::ProcessorJ(const cle::Processor &proc) : proc(proc)
{
}

std::vector<std::string> ProcessorJ::getAvailableDevices()
{
    return cle::Processor::ListAvailableDevices();
}

void ProcessorJ::setDevice(const std::string &name)
{
    this->proc.SelectDevice(name);
}

std::string ProcessorJ::getDevice()
{
    return this->proc.GetDeviceName();
}

cle::Processor ProcessorJ::get() const
{
    return this->proc;
}

std::shared_ptr<cle::Processor> ProcessorJ::getShared() const
{
    return std::make_shared<cle::Processor>(this->proc);
}

BufferJ::BufferJ() : buffer(cle::Image())
{
}

BufferJ::BufferJ(const cle::Image &buffer) : buffer(buffer)
{
}

size_t BufferJ::getWidth()
{
    return this->buffer.Shape()[0];
}

size_t BufferJ::getHeight()
{
    return this->buffer.Shape()[1];
}

size_t BufferJ::getDepth()
{
    return this->buffer.Shape()[2];
}

void BufferJ::getShape(size_t *shape)
{
    shape[0] = this->buffer.Shape()[0];
    shape[1] = this->buffer.Shape()[1];
    shape[2] = this->buffer.Shape()[2];
}

unsigned int BufferJ::getDimension()
{
    return this->buffer.Ndim();
}

std::string BufferJ::getDataType()
{
    return DataTypeToString(this->buffer.GetDataType());
}

std::string BufferJ::getMemoryType()
{
    return MemoryTypeToString(this->buffer.GetMemoryType());
}

std::string BufferJ::getDevice()
{
    return this->buffer.GetDevice()->GetDeviceName();
}

void BufferJ::fillMemory(float value)
{
    this->buffer.Fill(value);
}

void BufferJ::copyDataTo(BufferJ &dst)
{
    this->buffer.CopyDataTo(dst.get());
}

cle::Image BufferJ::get() const
{
    return this->buffer;
}

std::shared_ptr<cle::Image> BufferJ::getShared() const
{
    return std::make_shared<cle::Image>(this->buffer);
}

BufferJ MemoryJ::makeFloatBuffer(const ProcessorJ &proc, const size_t &width, const size_t &height, const size_t &depth, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        cle::Image image = cle::Memory::AllocateImageMemory(proc.getShared(), {width, height, depth}, cle::DataType::FLOAT32);
        return BufferJ{image};
    }
    else
    {
        cle::Image image = cle::Memory::AllocateBufferMemory(proc.getShared(), {width, height, depth}, cle::DataType::FLOAT32);
        return BufferJ{image};
    }
}

BufferJ MemoryJ::makeIntBuffer(const ProcessorJ &proc, const size_t &width, const size_t &height, const size_t &depth, const std::string &memory_type)
{
    if (memory_type == "image")
    {
        cle::Image image = cle::Memory::AllocateImageMemory(proc.getShared(), {width, height, depth}, cle::DataType::FLOAT32);
        return BufferJ{image};
    }
    else
    {
        cle::Image image = cle::Memory::AllocateBufferMemory(proc.getShared(), {width, height, depth}, cle::DataType::FLOAT32);
        return BufferJ{image};
    }
}

void MemoryJ::writeFloatBuffer(const BufferJ &buffer, float *data, const size_t &size)
{
    cle::Memory::WriteObject<float>(buffer.get(), data, size);
}

void MemoryJ::writeIntBuffer(const BufferJ &buffer, int *data, const size_t &size)
{
    cle::Memory::WriteObject<int>(buffer.get(), data, size);
}

void MemoryJ::readFloatBuffer(const BufferJ &buffer, float *data, const size_t &size)
{
    cle::Memory::ReadObject<float>(buffer.get(), data, size);
}

void MemoryJ::readIntBuffer(const BufferJ &buffer, int *data, const size_t &size)
{
    cle::Memory::ReadObject<int>(buffer.get(), data, size);
}
