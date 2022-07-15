package net.clesperanto.converters;

import net.clesperanto.ClesperantoJ;
import net.imagej.ImageJService;
import org.scijava.plugin.AbstractPTService;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.PluginInfo;
import org.scijava.service.Service;

import java.util.HashMap;

@Plugin(type = Service.class)
public class ConverterService extends AbstractPTService<ConverterPlugin> implements ImageJService {
    private static ClesperantoJ cle;

    private HashMap<ClassPair, PluginInfo<ConverterPlugin>> converterPlugins = new HashMap<>();

    protected class ClassPair {
        Class a;
        Class b;

        public ClassPair(Class a, Class b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ClassPair)) {
                return false;
            }
            return ((ClassPair) obj).a == a && ((ClassPair) obj).b == b;
        }
    }

    @Override
    public void initialize() {
        for (final PluginInfo<ConverterPlugin> info : getPlugins()) {
            final ConverterPlugin plugin = pluginService().createInstance(info);
            converterPlugins.put(new ClassPair(plugin.getSourceType(), plugin.getTargetType()), info);
        }
    }

    @Override
    public Class<ConverterPlugin> getPluginType() {
        return ConverterPlugin.class;
    }

    private PluginInfo<ConverterPlugin> findPluginInfo(ClassPair pair) {
        for (ClassPair item : converterPlugins.keySet()) {
            if( item.a.isAssignableFrom(pair.a) && item.b == pair.b) {
                return converterPlugins.get(item);
            }
        }
        return null;
    }

    public <S, T> ConverterPlugin<S, T> getConverter(Class<S> a, Class<T> b) {
        PluginInfo<ConverterPlugin> info = findPluginInfo(new ClassPair(a, b));
        if (info == null) {
            throw new IllegalArgumentException("No converter found from " + a + " to " + b);
        }
        ConverterPlugin<S, T> converter = pluginService().createInstance(info);
        if (converter == null) {
            throw new IllegalArgumentException("Couldn't instantiate converter found from " + a + " to " + b);
        }
        converter.setCLE(getCLE());
        return converter;
    }

    public void setCLE(ClesperantoJ cle) {
        this.cle = cle;
    }

    public ClesperantoJ getCLE() {
        if (cle == null) {
            cle = ClesperantoJ.getInstance();
        }
        return cle;
    }
}
