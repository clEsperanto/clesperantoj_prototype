package net.clesperanto.core.kernels;

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
	
	
	public static void absoluteInPlace(ArrayJ input, ArrayJ output) {
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
	
	
	public static Object absoluteReturnSameType(DeviceJ device, Object input) {
		if (input instanceof ArrayJ) {
			return absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
            ArrayJ arrayj = (ArrayJ) ObjectTypeConverter.convertImgLib2ToType(input, device, ObjectType.ARRAYJ);
            arrayj = absolute(arrayj);
            return ObjectTypeConverter.convertArrayJToType(arrayj, ObjectType.IMGLIB2);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
		
	}
	
	/**
	 * 
	 * @param device
	 * @param input
	 * @return
	 */
	public static ArrayJ absoluteReturnArrayJ(DeviceJ device, Object input) {
		if (input instanceof ArrayJ) {
			return absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
			ArrayJ arrayj = (ArrayJ) ObjectTypeConverter.convertImgLib2ToType(input, device, ObjectType.ARRAYJ);
			return absolute(arrayj);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}
	
	
	public static Object absoluteReturnWanted(DeviceJ device, Object input, ObjectType objectType) {
		ArrayJ outArrayJ;
		if (input instanceof ArrayJ) {
			outArrayJ = absolute((ArrayJ) input);
		} else if (DepsManager.IMGLIB2_AVAILABLE && DepsManager.RAI_CLASS.isInstance(input)) {
			ArrayJ inputArrayJ = (ArrayJ) ObjectTypeConverter.convertImagePlus2ToType(input, device, ObjectType.ARRAYJ);
			outArrayJ =  absolute(inputArrayJ);
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMAGEPLUS_CLASS.isInstance(input)) {
			return null;
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
		
		return ObjectTypeConverter.convertArrayJToType(outArrayJ, objectType);
	}
	
	
	public static void absoluteInPlace(DeviceJ device, Object input, Object output) {
		if (input instanceof ArrayJ && output instanceof ArrayJ) {
			absoluteInPlace((ArrayJ) input, (ArrayJ) output);
		} else if (DepsManager.IMGLIB2_AVAILABLE
				&& DepsManager.RAI_CLASS.isInstance(input) && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMGLIB2_AVAILABLE 
				&& DepsManager.RAI_CLASS.isInstance(input) && output instanceof ArrayJ) {
			// TODO do something
		} else if (DepsManager.IMGLIB2_AVAILABLE 
				 && input instanceof ArrayJ && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE 
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE 
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && output instanceof ArrayJ) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE 
				&& input instanceof ArrayJ && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMGLIB2_AVAILABLE 
				&& DepsManager.IMAGEPLUS_CLASS.isInstance(input) && DepsManager.RAI_CLASS.isInstance(output)) {
			// TODO do something
		} else if (DepsManager.IMAGEPLUS_AVAILABLE && DepsManager.IMGLIB2_AVAILABLE 
				&& DepsManager.RAI_CLASS.isInstance(input) && DepsManager.IMAGEPLUS_CLASS.isInstance(output)) {
			// TODO do something
		} else {
			throw new IllegalArgumentException("Unsupported input object: " + device.getClass().toString());
		}
	}

}
