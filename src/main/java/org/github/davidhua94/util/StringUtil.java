package org.github.davidhua94.util;

/**
 * @author David Hua
 * @date 2021/3/26
 * @desc
 */
public final class StringUtil {
    private StringUtil() {}

    public static boolean notBlank(String str) {
        return str != null && !"".equals(str.trim());
    }
}
