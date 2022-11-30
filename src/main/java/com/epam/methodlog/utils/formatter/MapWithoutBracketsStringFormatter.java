package com.epam.methodlog.utils.formatter;

import java.util.Map;

public class MapWithoutBracketsStringFormatter<K, V> implements StringFormatter<Map<K, V>> {

    @Override
    public String format(Map<K, V> map) {
        String toStringMap = map.toString();
        return toStringMap.substring(1, toStringMap.length() - 1);
    }
}