package net.clesperanto;

import net.clesperanto.clicwrapper.clesperantojWrapper.ClesperantoJInternal;

public class ClesperantoJ {

    public static void main(String[] args) {
        System.out.println("Hello World! Native Java");

        ClesperantoJInternal _native = new ClesperantoJInternal();
        _native.sayHello();
    }
}
