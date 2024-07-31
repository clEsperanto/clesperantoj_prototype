package net.clesperanto.core;

import java.util.ArrayList;

import net.clesperanto._internals.jclic.StringVector;

public class Utils {

	public static ArrayList<String> toArrayList(StringVector vector){
		ArrayList<String> arr = new ArrayList<String>();
		for (int i = 0; i < vector.size(); i ++) {
			arr.add(vector.get(i));
		}
		return arr;
	}

	public static ArrayList<Float> toArrayList(FloatVector vector){
		ArrayList<Float> arr = new ArrayList<Float>();
		for (int i = 0; i < vector.size(); i ++) {
			arr.add(vector.get(i));
		}
		return arr;
	}

	public static ArrayList<ArrayJ> toArrayList(ArrayJVector vector){
		ArrayList<ArrayJ> arr = new ArrayList<ArrayJ>();
		for (int i = 0; i < vector.size(); i ++) {
			DeviceJ device =  DeviceJ.getDeviceWithDefaultBackend(vector.get(i).getDevice(), "all");
			arr.add(new ArrayJ(vector.get(i), device));
		}
		return arr;
	}

	public static FloatVector toVector(ArrayList<Float> array) {
		Float[] floatArray = new Float[array.size()];
        floatArray = array.toArray(floatArray);
        return new FloatVector(floatArray);
	}

}
