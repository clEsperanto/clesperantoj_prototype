package net.clesperanto;

import net.clesperanto._internals.jclic.StringVector;

import java.util.Arrays;
import java.util.List;

import net.clesperanto.core.DeviceJ;
import net.clesperanto.core.MemoryJ;
import net.clesperanto.core.BackendJ;
import net.clesperanto.core.ArrayJ;

import net.clesperanto.kernels.Tier1;

public class ClesperantoJ {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");

        System.out.println(System.getProperty("java.library.path"));

        BackendJ.setBackend("opencl");
        // BackendJ.setBackend("cuda");

        List<String> deviceList = DeviceJ.getAvailableDevices();
        for (int i = 0; i < deviceList.size(); i++) {
            System.out.println(deviceList.get(i) + " is available");
        }

        DeviceJ currentDevice = DeviceJ.getDefaultDevice();
        System.out.println(currentDevice.getInfo());

        ArrayJ input = MemoryJ.makeFloatBuffer(currentDevice, 3, 3, 2, 3, "buffer");
        // ArrayJ output = MemoryJ.makeFloatBuffer(currentDevice, 3, 3, 2, 3, "buffer");

        float data[] = new float[3 * 3 * 2];
        float out[] = new float[3 * 3 * 2];
        Arrays.fill(data, -5);
        Arrays.fill(out, -1);

        MemoryJ.writeFloatBuffer(input, data, (long) data.length);
        // MemoryJ.writeFloatBuffer(output, out, (long) out.length);

        ArrayJ output = Tier1.absolute(currentDevice, input, null);
        // ArrayJ output = Tier1.addImagesWeighted(currentDevice, input, input, null, 1,
        // 1);

        MemoryJ.readFloatBuffer(output, out, (long) out.length);

        for (int i = 0; i < out.length; i++) {
            System.out.println("out[" + i + "] = " + out[i]);
        }

    }

}
