package views.utils;

import javax.swing.*;

public class CheckCorrectValueUtils {
    public static Integer parseInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer parsePositiveInteger(String s) {
        Integer i = parseInteger(s);
        if ((i == null) || (i <= 0))
            return null;
        else
            return i;
    }

    public static Integer parseNotNegativeInteger(String s) {
        Integer i = parseInteger(s);
        if ((i == null) || (i < 0))
            return null;
        else
            return i;
    }

    public static Double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double parsePositiveDouble(String s) {
        Double d = parseDouble(s);
        if ((d == null) || (d <= 0))
            return null;
        else
            return d;
    }

    public static String parseNotEmptyString(String s) {
        if ((s != null) && (s.length() > 0))
            return s;
        else
            return null;
    }

    public static boolean checkJComboBoxSelectCorrect(JComboBox cb) {
        if (cb.getSelectedItem() != null)
            return true;
        else
            return false;
    }
}