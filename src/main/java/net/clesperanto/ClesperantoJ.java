package net.clesperanto;

import net.clesperanto.clicwrapper.clesperantojWrapper.ClesperantoJInternal;
import net.clesperanto.clicwrapper.clesperantojWrapper.ObjectJ;

public class ClesperantoJ {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");

        ClesperantoJInternal _native = new ClesperantoJInternal();
        _native.sayHello();
        _native.getDeviceInfo();

        try (ObjectJ object = new ObjectJ()) {
            System.out.println("Object width: " + object.getWidth());
        }
    }
}
