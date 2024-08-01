package net.clesperanto.core;

import java.util.ArrayList;
import java.util.HashMap;

import net.clesperanto._internals.jclic.StringVector;
import net.clesperanto._internals.jclic.FloatVector;
import net.clesperanto._internals.jclic.ArrayJVector;
import net.clesperanto._internals.jclic.FloatVectorMap;

import net.clesperanto._internals.jclic.UtilsJ;

public class Utils {

	public static ArrayList<String> toArrayList(StringVector vector) {
		ArrayList<String> arr = new ArrayList<String>((int) vector.size());
		for (int i = 0; i < vector.size(); i++) {
			arr.add(vector.get(i));
		}
		return arr;
	}

	public static ArrayList<Float> toArrayList(FloatVector vector) {
		ArrayList<Float> arr = new ArrayList<Float>((int) vector.size());
		for (int i = 0; i < vector.size(); i++) {
			arr.add(vector.get(i));
		}
		return arr;
	}

	public static ArrayList<ArrayJ> toArrayList(ArrayJVector vector) {
		ArrayList<ArrayJ> arr = new ArrayList<ArrayJ>((int) vector.size());
		for (int i = 0; i < vector.size(); i++) {
			DeviceJ device = DeviceJ.getDeviceWithDefaultBackend(vector.get(i).getDevice(), "all");
			arr.add(new ArrayJ(vector.get(i), device));
		}
		return arr;
	}

	public static HashMap<String, ArrayList<Float>> toHashMap(FloatVectorMap map) {
		HashMap<String, ArrayList<Float>> hashMap = new HashMap<String, ArrayList<Float>>((int) map.size());
		ArrayList<String> keys = toArrayList(UtilsJ.getKeys(map));
		for (String string : keys) {
			hashMap.put(string, toArrayList(map.get(string)));
		}
		return hashMap;
	}

	public static FloatVector toVector(ArrayList<Float> array) {
		Float[] floatArray = new Float[array.size()];
		floatArray = array.toArray(floatArray);
		FloatVector vector = new FloatVector(array.size());
		for (int i = 0; i < array.size(); i++) {
			vector.put(i, floatArray[i]);
		}
		return vector;
	}

}
