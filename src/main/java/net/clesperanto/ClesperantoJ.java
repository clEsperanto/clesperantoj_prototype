package net.clesperanto;

import net.clesperanto._internals.jclic.StringVector;

import java.util.Arrays;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;

import net.clesperanto._internals.jclic.DeviceJ;
import net.clesperanto._internals.jclic.MemoryJ;
import net.clesperanto._internals.jclic.BackendJ;
import net.clesperanto._internals.jclic.ArrayJ;

import net.clesperanto._internals.kernelj.Tier1;

public class ClesperantoJ {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");

        System.out.println(System.getProperty("java.library.path"));

        BackendJ.setBackend("opencl");
        // BackendJ.setBackend("cuda");

        StringVector deviceList = DeviceJ.getAvailableDevices();
        for (int i = 0; i < deviceList.size(); i++) {
            System.out.println(deviceList.get(i) + " is available");
        }

        try (DeviceJ currentDevice = new DeviceJ()) {
            System.out.println("Device created");
            currentDevice.setDevice("TX", "all");
            System.out.println("device currently used is : " + currentDevice.getName());
            System.out.println(currentDevice.getInfo());
        }

        DeviceJ currentDevice = new DeviceJ();
        currentDevice.setDevice("TX", "all");

        ArrayJ input = MemoryJ.makeFloatBuffer(currentDevice, 3, 3, 2, 3, "image");
        ArrayJ output = MemoryJ.makeFloatBuffer(currentDevice, 3, 3, 2, 3, "image");

        float data[] = new float[3 * 3 * 2];
        float out[] = new float[3 * 3 * 2];
        Arrays.fill(data, -5);
        Arrays.fill(out, -1);

        MemoryJ.writeFloatBuffer(input, data, (long) data.length);
        // MemoryJ.writeFloatBuffer(output, out, (long) out.length);

        Tier1.absolute(currentDevice, input, output);

        MemoryJ.readFloatBuffer(output, out, (long) out.length);

        for (int i = 0; i < out.length; i++) {
            System.out.println("out[" + i + "] = " + out[i]);
        }

    }

}
