package net.clesperanto.converters;

import net.clesperanto.ClesperantoJ;
import org.scijava.plugin.SciJavaPlugin;

public interface ConverterPlugin<S, T> extends SciJavaPlugin {
    void setCLE(ClesperantoJ cle);
    T convert(S source);
    Class<S> getSourceType();
    Class<T> getTargetType();
}
