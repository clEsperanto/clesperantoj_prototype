package net.clesperanto.core.kernels;

public enum ObjectType {
    ARRAYJ("ArrayJ"),
    IMGLIB2("ImgLib2"),
    IMAGEPLUS("ImagePlus");

    private final String displayName;

    ObjectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
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
}
