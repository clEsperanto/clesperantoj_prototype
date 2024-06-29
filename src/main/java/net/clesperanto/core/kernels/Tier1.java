package net.clesperanto.core.kernels;

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
		
	}
	
	
	public static Object absolute(DeviceJ, Object input) {
		
	}
	
	
	public static ArrayJ absolute(DeviceJ, Object input) {
		
	}
	
	
	public static Object absolute(DeviceJ, Object input, String returnType) {
		
	}

}
