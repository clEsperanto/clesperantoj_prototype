package net.clesperanto.converters;

import net.clesperanto.ClesperantoJ;

public abstract class AbstractConverter<S, T> implements ConverterPlugin<S, T> {
    protected ClesperantoJ cle;

    @Override
    public void setCLE(ClesperantoJ cle) {
        this.cle = cle;
    }
}
