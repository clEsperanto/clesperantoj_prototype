package net.clesperanto.core.kernels;

import java.lang.reflect.InvocationTargetException;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

public class ObjectTypeConverter {

	
	public static Object convertArrayJToType(ArrayJ arrayj, ObjectType objectType) {
		switch (objectType) {
			case IMGLIB2:
				try {
					return DepsManager.ARRAYJ_2_RAI_METHOD.invoke(null, new Object[] {arrayj});
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Failed to access the method", e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Invalid arguments provided to the method", e);
				} catch (InvocationTargetException e) {
					Throwable cause = e.getCause();
				    if (cause instanceof RuntimeException) {
				        throw (RuntimeException) cause;
				    } else if (cause instanceof Error) {
				        throw (Error) cause;
				    } else if (cause instanceof Exception) {
				        throw new RuntimeException("Exception occurred inside the invoked method", cause);
				    } else {
				        throw new Error("Unhandled throwable type", cause);
				    }
				}
			case IMAGEPLUS:
				// TODO fill
				return null;
			case ARRAYJ:
			default:
				return arrayj;
		}
	}
	
	public static Object convertImgLib2ToType(Object array, DeviceJ device, ObjectType objectType) {
		if (DepsManager.RAI_CLASS == null)
			throw new IllegalArgumentException("Cannot work with ImgLib2 objects if the ImgLib2 dependencies are not on the classpath.");
		else if(!DepsManager.RAI_CLASS.isInstance(objectType))
			throw new IllegalArgumentException("The input argument 'array' is not an instance of an ImgLib2 RandomAccessibleInterval.");
			
		switch (objectType) {
			case IMGLIB2:
				return array;
			case IMAGEPLUS:
				// TODO fill
				return null;
			case ARRAYJ:
			default:
				try {
					Object[] arguments = new Object[] { DepsManager.RAI_CLASS.cast(array), device, "buffer" };
					return (ArrayJ) DepsManager.RAI_2_ARRAYJ_METHOD.invoke(null, arguments);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Failed to access the method", e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Invalid arguments provided to the method", e);
				} catch (InvocationTargetException e) {
					Throwable cause = e.getCause();
				    if (cause instanceof RuntimeException) {
				        throw (RuntimeException) cause;
				    } else if (cause instanceof Error) {
				        throw (Error) cause;
				    } else if (cause instanceof Exception) {
				        throw new RuntimeException("Exception occurred inside the invoked method", cause);
				    } else {
				        throw new Error("Unhandled throwable type", cause);
				    }
				}
		}
	}
	
	public static Object convertImagePlus2ToType(Object array, DeviceJ device, ObjectType objectType) {
		if (DepsManager.IMAGEPLUS_CLASS == null)
			throw new IllegalArgumentException("Cannot work with ImagePlus objects if the ImageJ dependencies are not on the classpath.");
		else if(!DepsManager.IMAGEPLUS_CLASS.isInstance(objectType))
			throw new IllegalArgumentException("The input argument 'array' is not an instance of an ImageJ ImagePlus.");
			
		switch (objectType) {
			case IMAGEPLUS:
				return array;
			case IMGLIB2:
				// TODO fill
				return null;
			case ARRAYJ:
			default:
				// TODO fill
				return null;
		}
	}
}
