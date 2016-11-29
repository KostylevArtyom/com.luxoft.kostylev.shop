package models.utils;

public final class Constants {
    public static final String SHOW_ITEMS_SEPARATOR = ", ";
    public static final String SHOW_CLASS_WRAPPER_LEFT = "(";
    public static final String SHOW_CLASS_WRAPPER_RIGHT = ")";
    public static final String SHOW_CLASS_SEPARATOR = ": ";
    public static final String STORE_SEPARATOR = ";";

    public static String wrapString(String s) {
        return SHOW_CLASS_WRAPPER_LEFT + s + SHOW_CLASS_WRAPPER_RIGHT;
    }
}