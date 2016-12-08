package com.test.demo.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * http://gold.xitu.io/post/5828ef582f301e0058586fde
 * Created on 2016/11/14.
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        useTreeMap("useTreeMap");
        useTreeMapWithCaseIgnore("useTreeMapWithCaseIgnore");
        useTreeMapWithComparator("useTreeMapWithComparator");
        useTreeMapWithCollections("useTreeMapWithCollections");
        useTreeMapWithCollectionsIgnore("useTreeMapWithCollectionsIgnore");
    }

    private static final void display(Map<String, String> map, String name) {
        System.out.println("-----------------" + name + " start--------------------");
        Set<Map.Entry<String, String>> kvs = map.entrySet();
        for (Map.Entry<String, String> kv : kvs) {
            System.out.println(kv.getKey() + "=" + kv.getValue());
        }
        System.out.println("-----------------" + name + " end----------------------\n");
    }

    /**
     * TreeMap基本用法，根据键排序
     */
    public static final void useTreeMap(String name) {
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("a", "aaa");
        treeMap.put("c", "ccc");
        treeMap.put("b", "bbb");
        treeMap.put("A", "AAA");

        display(treeMap, name);
    }

    /**
     * 忽视大小写
     */
    public static final void useTreeMapWithCaseIgnore(String name) {
        Map<String, String> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        treeMap.put("a", "aaa");
        treeMap.put("c", "ccc");
        treeMap.put("b", "bbb");
        treeMap.put("A", "AAA");
        treeMap.put("T", "TTT");

        display(treeMap, name);
    }

    /**
     * 使用比较器
     * @param name
     */
    public static final void useTreeMapWithComparator(String name) {
        Map<String, String> treeMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));
        treeMap.put("a", "aaa");
        treeMap.put("c", "ccc");
        treeMap.put("b", "bbb");
        treeMap.put("A", "AAA");
        treeMap.put("T", "TTT");

        display(treeMap, name);
    }

    /**
     * 使用Collections的比较器
     * @param name
     */
    public static final void useTreeMapWithCollections(String name) {
        Map<String, String> treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.put("a", "aaa");
        treeMap.put("c", "ccc");
        treeMap.put("b", "bbb");
        treeMap.put("A", "AAA");
        treeMap.put("T", "TTT");

        display(treeMap, name);
    }

    /**
     * 使用Collections的比较器加上忽视大小写
     * @param name
     */
    public static final void useTreeMapWithCollectionsIgnore(String name) {
        Map<String, String> treeMap = new TreeMap<>(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
        treeMap.put("a", "aaa");
        treeMap.put("c", "ccc");
        treeMap.put("b", "bbb");
        treeMap.put("A", "AAA");
        treeMap.put("T", "TTT");

        display(treeMap, name);
    }
}
