package seedu.todo.commons.util;

import com.google.common.base.CharMatcher;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {
    public static boolean containsIgnoreCase(String source, String query) {
        String[] split = source.toLowerCase().split("\\s+");
        List<String> strings = Arrays.asList(split);
        return strings.stream().filter(s -> s.equals(query.toLowerCase())).count() > 0;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t){
        assert t != null;
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if s represents an unsigned integer e.g. 1, 2, 3, ... <br>
     *   Will return false for null, empty string, "-1", "0", "+1", and " 2 " (untrimmed) "3 0" (contains whitespace).
     * @param s Should be trimmed.
     */
    public static boolean isUnsignedInteger(String s){
        return s != null && s.matches("^0*[1-9]\\d*$");
    }

    //@@author A0135817B
    /**
     * Returns true if the string is null, of length zero, or contains only whitespace 
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0 || CharMatcher.whitespace().matchesAllOf(s);
    }

    //@@author A0135805H
    /**
     * Partitions the string into three parts:
     *      string[0 .. position - 1], string[position], string[position + 1 .. length - 1], all index inclusive
     * @param string to be partitioned
     * @param position location where the string should be partitioned
     * @return a String array containing the three elements stated above,
     *         where each element must not be null, but can have empty string.
     */
    public static String[] partitionStringAtPosition(String string, int position) {
        String[] stringArray = new String[3];
        if (string == null || string.isEmpty() || position < 0 || position >= string.length()) {
            stringArray[0] = "";
            stringArray[1] = "";
            stringArray[2] = "";
        } else {
            stringArray[0] = string.substring(0, position);
            stringArray[1] = string.substring(position, position + 1);
            stringArray[2] = string.substring(position + 1, string.length());
        }
        return stringArray;
    }
}