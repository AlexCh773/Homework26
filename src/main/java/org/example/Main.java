package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayStringList list1 = new ArrayStringList(4);
        list1.add("dsl");
        list1.add("dfs");
        list1.add("sdfl");
        list1.add("dfg");
        System.out.println(list1.Length() + "\n" + list1);
        list1.set(1,"ter");
        System.out.println(list1.Length() + "\n" + list1);
        list1.add(3,"lll");
        System.out.println(list1.Length() + "\n" + list1);
        list1.remove("lll");
        System.out.println(list1.Length() + "\n" + list1);
        list1.remove(1);
        System.out.println(list1.Length() + "\n" + list1);
        list1.contains("dsl");
        System.out.println(list1.contains("dsl"));
        System.out.println(Arrays.toString(list1.toArray()));
        System.out.println(list1.isEmpty());
        ArrayStringList list2 = new ArrayStringList(10);
        list2.add("dsl");
        list2.add("dfs");
        list2.add("sdfl");
        list2.add("dfg");
        System.out.println(list2.Length() + "\n" + list2);
        System.out.println(list1.equals(list2));

    }
}