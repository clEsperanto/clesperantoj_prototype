package net.clesperanto.core.kernels;

import java.lang.reflect.Method;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;

public class DepsManager {

	private static final String RAI_CLS_NAME = "net.imglib2.RandomAccessibleInterval";

	private static final String RAI_CONVERTERS_CLS_NAME = "net.clesperanto.imglib2.Converters";

	private static final String IMAGEPLUS_CLS_NAME = "";

	private static final String IMAGEPLUS_CONVERTERS_CLS_NAME = "";

	protected static final Class<?> RAI_CLASS;

	protected static final Class<?> RAI_CONVERTERS_CLASS;

	protected static final Class<?> IMAGEPLUS_CLASS;

	protected static final Class<?> IMAGEPLUS_CONVERTERS_CLASS;

	private static final String RAI_2_ARRAYJ_METHOD_NAME = "copyImgLib2ToArrayJ";

	protected static final Method RAI_2_ARRAYJ_METHOD;

	private static final String ARRAYJ_2_RAI_METHOD_NAME = "copyImgLib2ToArrayJ";

	protected static final Method ARRAYJ_2_RAI_METHOD;

	/**
	 * Initialize the classes for ImgLib2 and ImagePlus if the exist
	 */
	static {
		RAI_CLASS = initializeClass(RAI_CLS_NAME);
		if (RAI_CLASS != null) RAI_CONVERTERS_CLASS = initializeClass(RAI_CONVERTERS_CLS_NAME);
		else RAI_CONVERTERS_CLASS = null;

		if (RAI_CLASS != null && RAI_CONVERTERS_CLASS != null) {
			Class<?>[] parameterTypes = new Class<?>[] { RAI_CLASS, DeviceJ.class, String.class };
			RAI_2_ARRAYJ_METHOD = initializeMethod(RAI_CONVERTERS_CLASS, RAI_2_ARRAYJ_METHOD_NAME, parameterTypes);

			Class<?>[] parameterTypes2 = new Class<?>[] { ArrayJ.class };
			ARRAYJ_2_RAI_METHOD = initializeMethod(RAI_CONVERTERS_CLASS, ARRAYJ_2_RAI_METHOD_NAME, parameterTypes2);
		} else {
			RAI_2_ARRAYJ_METHOD = null;
			ARRAYJ_2_RAI_METHOD = null;
		}

		IMAGEPLUS_CLASS = initializeClass(IMAGEPLUS_CLS_NAME);
		if (IMAGEPLUS_CLASS != null) IMAGEPLUS_CONVERTERS_CLASS = initializeClass(IMAGEPLUS_CONVERTERS_CLS_NAME);
		else IMAGEPLUS_CONVERTERS_CLASS = null;
	}

	protected static final boolean IMGLIB2_AVAILABLE = (RAI_CLASS != null) && (RAI_CONVERTERS_CLASS != null)
														&& (RAI_2_ARRAYJ_METHOD != null) && (ARRAYJ_2_RAI_METHOD != null);

	protected static final boolean IMAGEPLUS_AVAILABLE = (IMAGEPLUS_CLASS != null) && (IMAGEPLUS_CONVERTERS_CLASS != null)
														&& (RAI_2_ARRAYJ_METHOD != null) && (ARRAYJ_2_RAI_METHOD != null);


	private static boolean checkIfClassLoaded(String className) {
		try {
		    Class.forName(className, false, DepsManager.class.getClassLoader());
		    return true;
		} catch (ClassNotFoundException e) {
		    return false;
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

	private static Method initializeMethod(Class<?> cls, String methodName, Class<?>[] argumentTypes) {
		Method method;
		try {
			method = cls.getMethod(methodName, argumentTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			method = null;
		}
		return method;
	}

}
