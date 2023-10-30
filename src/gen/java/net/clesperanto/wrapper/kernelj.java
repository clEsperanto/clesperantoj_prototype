// Targeted by JavaCPP version 1.5.8: DO NOT EDIT THIS FILE

package net.clesperanto.wrapper;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.presets.javacpp.*;
import static net.clesperanto.wrapper.clesperantoj.*;

public class kernelj extends net.clesperanto.presets.kernelj {
    static { Loader.load(); }

// Parsed from kernelj.hpp

// #ifndef __INCLUDE_KERNELJ_HPP
// #define __INCLUDE_KERNELJ_HPP

// #include "clesperantoj.hpp"

public static class Tier1 extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public Tier1() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Tier1(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Tier1(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public Tier1 position(long position) {
        return (Tier1)super.position(position);
    }
    @Override public Tier1 getPointer(long i) {
        return new Tier1((Pointer)this).offsetAddress(i);
    }

    public static native void absolute(@Const @ByRef DeviceJ dev, @Const @ByRef ArrayJ src, @Const @ByRef ArrayJ dst);
    public static native void gaussianBlur(@Const @ByRef DeviceJ dev, @Const @ByRef ArrayJ src, @Const @ByRef ArrayJ dst, float sigmaX, float sigmaY, float sigmaZ);
    public static native void addImageAndScalar(@Const @ByRef DeviceJ dev, @Const @ByRef ArrayJ src, @Const @ByRef ArrayJ dst, float scalar);
}

// #endif // __INCLUDE_KERNELJ_HPP


}
