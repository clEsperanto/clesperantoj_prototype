#ifndef __INCLUDE_CLESPERANTOJ_HPP
#define __INCLUDE_CLESPERANTOJ_HPP

#include <memory>
#include <string>
#include <vector>

#include "cleImage.hpp"
#include "cleProcessor.hpp"

class ProcessorJ
{
    friend class BufferJ;
    friend class MemoryJ;

private:
    cle::Processor proc;
    ProcessorJ(const cle::Processor &proc);

public:
    ProcessorJ();
    ProcessorJ(const std::string &name);
    std::vector<std::string> getAvailableDevices();
    void setDevice(const std::string &name);
    std::string getDevice();

    cle::Processor get() const;
    std::shared_ptr<cle::Processor> getShared() const;
};

class BufferJ
{
    friend class MemoryJ;

private:
    cle::Image buffer;
    BufferJ(const cle::Image &buffer);

public:
    BufferJ();

    size_t getWidth();
    size_t getHeight();
    size_t getDepth();
    void getShape(size_t *shape);
    unsigned int getDimension();

    std::string getDataType();
    std::string getMemoryType();
    std::string getDevice();

    void fillMemory(float value);
    void copyDataTo(BufferJ &dst);

    cle::Image get() const;
    std::shared_ptr<cle::Image> getShared() const;
};

class MemoryJ
{
public:
    static BufferJ makeFloatBuffer(const ProcessorJ &device, const size_t &width, const size_t &height, const size_t &depth, const std::string &memory_type);
    static BufferJ makeIntBuffer(const ProcessorJ &device, const size_t &width, const size_t &height, const size_t &depth, const std::string &memory_type);

    static void writeFloatBuffer(const BufferJ &buffer, float *data, const size_t &size);
    static void writeIntBuffer(const BufferJ &buffer, int *data, const size_t &size);

    static void readFloatBuffer(const BufferJ &buffer, float *data, const size_t &size);
    static void readIntBuffer(const BufferJ &buffer, int *data, const size_t &size);
};

#endif // __INCLUDE_CLESPERANTOJ_HPP
