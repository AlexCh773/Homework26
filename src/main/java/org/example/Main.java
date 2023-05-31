package org.example;

public class Main {
    public static void main(String[] args) {
        ArrayIntegerList arrayIntegerList = new ArrayIntegerList(10);
        arrayIntegerList.add(4);
        arrayIntegerList.add(8);
        arrayIntegerList.add(1);
        arrayIntegerList.add(5);
        arrayIntegerList.add(2);
        arrayIntegerList.add(3);
        arrayIntegerList.add(7);
        arrayIntegerList.add(0);
        arrayIntegerList.add(9);
        arrayIntegerList.add(6);
        System.out.println(arrayIntegerList);
        arrayIntegerList.sort();
        System.out.println(arrayIntegerList);
        System.out.println(arrayIntegerList.contains(0));
        System.out.println(arrayIntegerList.contains(10));
    }
}