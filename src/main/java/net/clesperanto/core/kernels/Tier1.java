package net.clesperanto.core.kernels;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import net.clesperanto.core.ArrayJ;
import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;

public class Tier1 {
	
	
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
	
	
	public static Object absoluteReturnSameType(DeviceJ device, Object input) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (input instanceof ArrayJ)
			return absolute((ArrayJ) input);
		if (DepsManager.RAI_CLASS != null && DepsManager.RAI_CONVERTERS_CLASS != null && DepsManager.RAI_2_ARRAYJ_METHOD != null
				&& DepsManager.RAI_CLASS.isInstance(input)) {
            Object[] arguments = new Object[] { DepsManager.RAI_CLASS.cast(input), device, "buffer" };
            ArrayJ arrayj = (ArrayJ) DepsManager.RAI_2_ARRAYJ_METHOD.invoke(null, arguments);
            arrayj = absolute(arrayj);
            return DepsManager.ARRAYJ_2_RAI_METHOD.invoke(null, new Object[] {arrayj});
		} else if (DepsManager.IMAGEPLUS_CLASS != null && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
		
	}
	
	/**
	 * TODO remove exceptions
	 * @param device
	 * @param input
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static ArrayJ absoluteReturnArrayJ(DeviceJ device, Object input) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (input instanceof ArrayJ)
			return absolute((ArrayJ) input);
		if (DepsManager.RAI_CLASS != null && DepsManager.RAI_CONVERTERS_CLASS != null && DepsManager.RAI_2_ARRAYJ_METHOD != null
				&& DepsManager.RAI_CLASS.isInstance(input)) {
            Object[] arguments = new Object[] { DepsManager.RAI_CLASS.cast(input), device, "buffer" };
            ArrayJ arrayj = (ArrayJ) DepsManager.RAI_2_ARRAYJ_METHOD.invoke(null, arguments);
			return absolute(arrayj);
		} else if (DepsManager.IMAGEPLUS_CLASS != null && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}
	
	
	public static Object absolute(DeviceJ device, Object input, String returnType) {
		return null;
	}

}
