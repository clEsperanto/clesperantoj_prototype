package net.clesperanto.core.kernels;

import net.clesperanto._internals.jclic.ArrayJ;

public enum ObjectType {
    ARRAYJ("ArrayJ", true, ArrayJ.class),
    IMGLIB2("ImgLib2", DepsManager.IMGLIB2_AVAILABLE, DepsManager.RAI_CLASS),
    IMAGEPLUS("ImagePlus", DepsManager.IMAGEPLUS_AVAILABLE, DepsManager.IMAGEPLUS_CLASS);

    private final String displayName;
    private final boolean classExists;
    private final Class<?> cls;

    ObjectType(String displayName, boolean classExists, Class<?> cls) {
        this.displayName = displayName;
        this.cls = cls;
        this.classExists = classExists;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public boolean canBeUsed() {
    	return this.classExists;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    public boolean requiresExternalDeps() {
        return this == IMGLIB2 || this == IMAGEPLUS;
    }

    public static ObjectType fromString(String text) {
        for (ObjectType type : ObjectType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text '" + text + "' found");
    }

    public static ObjectType fromObject(Object object) {
        for (ObjectType type : ObjectType.values()) {
            if (type.classExists && type.cls.isInstance(object)) {
                return type;
            }
        }
        throw new IllegalArgumentException("The object does not correspond to any of the supported loaded types. "
        		+ "The supported types are 'net.clesperanto.core.ArrayJ', '" + DepsManager.RAI_CLS_NAME
        		+ "' and '" + DepsManager.IMAGEPLUS_CLS_NAME + "'. Of them, 'net.clesperanto.core.ArrayJ' "
				+ "is loaded, '" + DepsManager.RAI_CLS_NAME + "' is " + (DepsManager.IMGLIB2_AVAILABLE ? "" : "not")
				+ " loaded and " + "'" + DepsManager.IMAGEPLUS_CLS_NAME  + "' is " 
				+ (DepsManager.IMGLIB2_AVAILABLE ? "" : "not") + " loaded.");
    }
}
