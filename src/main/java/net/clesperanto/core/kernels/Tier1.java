package net.clesperanto.core.kernels;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;

public class Tier1 {
	
	public static final String RAI_CLS_NAME = "";
	
	public static final String RAI_CONVERTERS_CLS_NAME = "";
	
	public static final String IMAGEPLUS_CLS_NAME = "";
	
	public static final String IMAGEPLUS_CONVERTERS_CLS_NAME = "";
	
	public static final Class<?> RAI_CLASS;
	
	public static final Class<?> RAI_CONVERTERS_CLASS;
	
	public static final Class<?> IMAGEPLUS_CLASS;
	
	public static final Class<?> IMAGEPLUS_CONVERTERS_CLASS;
	
	/**
	 * Initialize the classes for ImgLib2 and ImagePlus if the exist
	 */
	static {
		RAI_CLASS = initializeClass(RAI_CLS_NAME);
		if (RAI_CLASS != null) RAI_CONVERTERS_CLASS = initializeClass(RAI_CONVERTERS_CLS_NAME);
		else RAI_CONVERTERS_CLASS = null;

		IMAGEPLUS_CLASS = initializeClass(IMAGEPLUS_CLS_NAME);
		if (IMAGEPLUS_CLASS != null) IMAGEPLUS_CONVERTERS_CLASS = initializeClass(IMAGEPLUS_CONVERTERS_CLS_NAME);
		else IMAGEPLUS_CONVERTERS_CLASS = null;
	}
	
	
	public static ArrayJ absolute(ArrayJ input) {
		ArrayJ out = MemoryJ.like(input);
		net.clesperanto._internals.kernelj.Tier1.absolute(input.getDevice().getRaw(), input.getRaw(), out.getRaw());
		return out;
	}
	
	
	public static void absolute(ArrayJ input, ArrayJ output) {
		if (input.getDevice().getName() != output.getDevice().getName())
			throw new IllegalArgumentException("The input and output should be on the same device. Input is on '"
					+ input.getDeviceName() + "' and output on'" + output.getDeviceName() + "'.");
		else if (input.getDevice().getBackend() != output.getDevice().getBackend())
			throw new IllegalArgumentException("The input and output must ise the same backend. Input uses '"
					+ input.getDevice().getBackend() + "' and output uses'" + output.getDevice().getBackend() + "'.");
		else if (input.getDepth() != output.getDepth() || input.getDepth() != output.getDepth()
					|| input.getDepth() != output.getDepth())
			throw new IllegalArgumentException("Dimensions of input and output ArrayJs must coincide. Input dimensions "
					+ "are " + Arrays.toString(input.getDimensions()) + " and output dimensions are "
					+ Arrays.toString(output.getDimensions()));
		
		
		net.clesperanto._internals.kernelj.Tier1.absolute(input.getDevice().getRaw(), input.getRaw(), output.getRaw());		
	}
	
	
	public static Object absolute(DeviceJ device, Object input) {
		if (input instanceof ArrayJ)
			return absolute((ArrayJ) input);
		if (RAI_CLASS != null && RAI_CONVERTERS_CLASS != null && RAI_CLASS.isInstance(input)) {
			Class<?>[] parameterTypes = new Class<?>[] { DeviceJ.class, RAI_CLASS, String.class };
			Method method = RAI_CONVERTERS_CLASS.getMethod("", parameterTypes);
            Object[] arguments = new Object[] { 10, 20 };
            ArrayJ arrayj = (ArrayJ) method.invoke(null, arguments);
			return absolute(arrayj);
		} else if (IMAGEPLUS_CLASS != null && IMAGEPLUS_CLASS.isInstance(input)) {
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
		
	}
	
	
	public static ArrayJ absolute(DeviceJ device, Object input) {
		if (input instanceof ArrayJ)
			return absolute((ArrayJ) input);
		if (RAI_CLASS != null && RAI_CONVERTERS_CLASS != null && RAI_CLASS.isInstance(input)) {
			Class<?>[] parameterTypes = new Class<?>[] { DeviceJ.class, RAI_CLASS, String.class };
			Method method = RAI_CONVERTERS_CLASS.getMethod("", parameterTypes);
            Object[] arguments = new Object[] { 10, 20 };
            ArrayJ arrayj = (ArrayJ) method.invoke(null, arguments);
			return absolute(arrayj);
		} else if (IMAGEPLUS_CLASS != null && IMAGEPLUS_CLASS.isInstance(input)) {
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}
	
	
	public static Object absolute(DeviceJ device, Object input, String returnType) {
		
	}
	
	private static boolean checkIfClassLoaded(String className) {
		try {
		    Class.forName(className, false, Tier1.class.getClassLoader());
		    return true;
		} catch (ClassNotFoundException e) {
		    return false;
		}
    }
	
	private static void initializeImgLib2RelatedClassesIfExist(String className1, String className2,
			Class<?> class1, Class<?> class2) {
		if (checkIfClassLoaded(className1)) {
			Class<?> cls;
			Class<?> cls2;
			try {
				cls = Class.forName(className1);
				cls2 = Class.forName(className2);
			} catch (ClassNotFoundException e) {
				cls = null;
				cls2 = null;
			}
			class1 = cls;
			class2 = cls2;
		} else {
			class1 = null;
			class2 = null;
		}
	}
	
	private static Class<?> initializeClass(String classname) {
		if (checkIfClassLoaded(classname)) {
			Class<?> cls;
			try {
				cls = Class.forName(classname);
			} catch (ClassNotFoundException e) {
				cls = null;
			}
			return cls;
		} else {
			return null;
		}
	}

}
