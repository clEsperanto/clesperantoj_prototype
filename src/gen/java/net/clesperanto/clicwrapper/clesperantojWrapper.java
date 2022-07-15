// Targeted by JavaCPP version 1.5: DO NOT EDIT THIS FILE

package net.clesperanto.clicwrapper;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

public class clesperantojWrapper extends net.clesperanto.clicwrapper.clesperantoj {
    static { Loader.load(); }

// Parsed from clesperantoj.h

// #include "clesperanto.hpp"

/**
 * \brief 
 * ObjectJ just wraps an CLIc Object
 * 
 * This is done so we can store Clic Objects on the java side
 * 
 * However the Object itself is private so the java side doesn't need to wrap all of Object (and thus much of cl.cpp)
 * 
 * Note we declare ClesperantoJ as a friend class so that ClesperantoJ can access the Object
 */
public static class ObjectJ extends Pointer {
    static { Loader.load(); }
    /** Default native constructor. */
    public ObjectJ() { super((Pointer)null); allocate(); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ObjectJ(long size) { super((Pointer)null); allocateArray(size); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ObjectJ(Pointer p) { super(p); }
    private native void allocate();
    private native void allocateArray(long size);
    @Override public ObjectJ position(long position) {
        return (ObjectJ)super.position(position);
    }

        public native int getWidth();
        public native int getHeight();
        public native int getDepth();
}

@NoOffset public static class ClesperantoJInternal extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public ClesperantoJInternal(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public ClesperantoJInternal(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public ClesperantoJInternal position(long position) {
        return (ClesperantoJInternal)super.position(position);
    }

    public ClesperantoJInternal() { super((Pointer)null); allocate(); }
    private native void allocate();

    public native void sayHello();

    public native @ByVal @Name("create<float>") ObjectJ FloatCreate(int nx, int ny, int nz);

    public native @ByVal @Name("create<short>") ObjectJ ShortCreate(int nx, int ny, int nz);

    public native @ByVal @Name("push<float>") ObjectJ FloatPush(FloatPointer in, int nx, int ny);
    public native @ByVal @Name("push<float>") ObjectJ FloatPush(FloatBuffer in, int nx, int ny);
    public native @ByVal @Name("push<float>") ObjectJ FloatPush(float[] in, int nx, int ny);

    public native @ByVal @Name("push<short>") ObjectJ ShortPush(ShortPointer in, int nx, int ny);
    public native @ByVal @Name("push<short>") ObjectJ ShortPush(ShortBuffer in, int nx, int ny);
    public native @ByVal @Name("push<short>") ObjectJ ShortPush(short[] in, int nx, int ny);

    public native @Name("pull<float>") void FloatPull(FloatPointer out, @ByVal ObjectJ obj);
    public native @Name("pull<float>") void FloatPull(FloatBuffer out, @ByVal ObjectJ obj);
    public native @Name("pull<float>") void FloatPull(float[] out, @ByVal ObjectJ obj);

    public native @Name("pull<short>") void ShortPull(ShortPointer out, @ByVal ObjectJ obj);
    public native @Name("pull<short>") void ShortPull(ShortBuffer out, @ByVal ObjectJ obj);
    public native @Name("pull<short>") void ShortPull(short[] out, @ByVal ObjectJ obj);

    // non templated version of gaussian blur
    public native void gaussianBlur2d(FloatPointer in, FloatPointer out, int nr, int nc, float sx, float sy);
    public native void gaussianBlur2d(FloatBuffer in, FloatBuffer out, int nr, int nc, float sx, float sy);
    public native void gaussianBlur2d(float[] in, float[] out, int nr, int nc, float sx, float sy);
    
    public native void gaussianBlur2d(@ByVal ObjectJ in, @ByVal ObjectJ out, float sx, float sy);

    public native @ByVal ObjectJ gaussian_blur(@ByVal ObjectJ in, @ByVal ObjectJ out, float sigma_x, float sigma_y, float sigma_z);

    // templated version
    public native @Name("guassianBlur2dT<float>") void FloatGaussianBlur2dT(FloatPointer in, FloatPointer out, int nr, int nc, float sx, float sy);
    public native @Name("guassianBlur2dT<float>") void FloatGaussianBlur2dT(FloatBuffer in, FloatBuffer out, int nr, int nc, float sx, float sy);
    public native @Name("guassianBlur2dT<float>") void FloatGaussianBlur2dT(float[] in, float[] out, int nr, int nc, float sx, float sy);
    public native @Name("guassianBlur2dT<short>") void ShortGaussianBlur2dT(ShortPointer in, ShortPointer out, int nr, int nc, float sx, float sy);
    public native @Name("guassianBlur2dT<short>") void ShortGaussianBlur2dT(ShortBuffer in, ShortBuffer out, int nr, int nc, float sx, float sy);
    public native @Name("guassianBlur2dT<short>") void ShortGaussianBlur2dT(short[] in, short[] out, int nr, int nc, float sx, float sy);

}











}