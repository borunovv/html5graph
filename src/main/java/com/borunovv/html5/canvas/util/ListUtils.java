package com.borunovv.html5.canvas.util;

import java.util.List;

public class ListUtils {
    public static void toString(StringBuilder sb, List<String> list, String prefix, String postfix) {
        for (String line : list) {
            sb.append(prefix).append(line).append(postfix);
        }
    }
}
