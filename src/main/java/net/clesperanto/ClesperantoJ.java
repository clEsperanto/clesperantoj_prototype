package net.clesperanto;

import net.clesperanto.wrapper.clesperantoj.StringVector;

import java.util.Arrays;

import org.bytedeco.javacpp.LongPointer;

import net.clesperanto.wrapper.clesperantoj.ProcessorJ;
import net.clesperanto.wrapper.kernelj.Tier1;
import net.clesperanto.wrapper.clesperantoj.BufferJ;
import net.clesperanto.wrapper.clesperantoj.MemoryJ;

public class ClesperantoJ {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");

        try (ProcessorJ proc = new ProcessorJ()) {
            StringVector deviceList = proc.getAvailableDevices();
            for (int i = 0; i < deviceList.size(); i++) {
                System.out.println(deviceList.get(i) + " is available");
            }
            System.out.println("device currently used is : " + proc.getDevice());

            proc.setDevice("Intel");
            System.out.println("device currently used is : " + proc.getDevice());
        }

        try (BufferJ buffer = new BufferJ()) {
            System.out.println("Buffer created");
        }

        ProcessorJ proc = new ProcessorJ("TX");

        try (BufferJ buffer = MemoryJ.makeFloatBuffer(proc, 3, 3, 2, "buffer")) {

            System.out.println("Buffer created");
            System.out.println("Buffer shape is (" + buffer.getWidth() + ", " + buffer.getHeight() + ", "
                    + buffer.getDepth() + ")");
            System.out.println("Buffer data type is " + buffer.getDataType());
            System.out.println("Buffer mem type is " + buffer.getMemoryType());
            System.out.println("Buffer device is " + buffer.getDevice());

            float data[] = new float[3 * 3 * 2];
            float out[] = new float[3 * 3 * 2];
            Arrays.fill(data, 5);
            Arrays.fill(out, 1);

            for (int i = 0; i < out.length; i++) {
                System.out.println("before out[" + i + "] = " + out[i]);
            }

            MemoryJ.writeFloatBuffer(buffer, data, (long) data.length);
            MemoryJ.readFloatBuffer(buffer, out, (long) out.length);

            for (int i = 0; i < out.length; i++) {
                System.out.println("after out[" + i + "] = " + out[i]);
            }

            buffer.fillMemory(10.0f);
            MemoryJ.readFloatBuffer(buffer, out, (long) out.length);
            for (int i = 0; i < out.length; i++) {
                System.out.println("out[" + i + "] = " + out[i]);
            }
        }

        BufferJ input = MemoryJ.makeFloatBuffer(proc, 3, 3, 2, "buffer");
        BufferJ output = MemoryJ.makeFloatBuffer(proc, 3, 3, 2, "buffer");

        float data[] = new float[3 * 3 * 2];
        float out[] = new float[3 * 3 * 2];
        Arrays.fill(data, 5);
        Arrays.fill(out, 1);

        MemoryJ.writeFloatBuffer(input, data, (long) data.length);
        MemoryJ.writeFloatBuffer(output, out, (long) out.length);

        Tier1.addImageAndScalar(proc, input, output, 10.0f);

        MemoryJ.readFloatBuffer(output, out, (long) out.length);

        for (int i = 0; i < out.length; i++) {
            System.out.println("out[" + i + "] = " + out[i]);
        }
    }

}
