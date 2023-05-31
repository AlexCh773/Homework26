package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIntegerListTest {
    public final ArrayIntegerList out = new ArrayIntegerList(5);
    public static Integer item1 = 3;
    public static Integer item2 = 6;
    public static Integer item3 = 1;
    public static Integer item4 = -7;
    public static Integer item5 = 4;
    public static Integer item6 = 0;


    @AfterEach
    void afterEach() {
        out.clear();
    }

    public static Stream<Arguments> provideParamsForTestAdd1() {
        Integer[] expectedIntegerArray1 = {item1};
        Integer[] expectedIntegerArray2 = {item1, item2};
        Integer[] expectedIntegerArray3 = {item1, item2, item3, item4, item5, item6};

        return Stream.of(Arguments.of(expectedIntegerArray1, item1),
                Arguments.of(expectedIntegerArray2, item2),
                Arguments.of(expectedIntegerArray3, item6));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestAdd1")
    void testAdd(Integer[] expectedIntegerArray, Integer item) {
        for (int i = 0; i < expectedIntegerArray.length - 1; i++) {
            out.add(expectedIntegerArray[i]);
        }
        assertEquals(item, out.add(item));
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestAdd2() {
        Integer[] preliminaryIntegerArray1 = {item2};
        int index1 = 0;
        Integer[] expectedIntegerArray1 = {item1, item2};
        Integer[] preliminaryIntegerArray2 = {item1, item2, item3, item4, item5};
        int index2 = 4;
        Integer[] expectedIntegerArray2 = {item1, item2, item3, item4, item6, item5};
        Integer[] preliminaryIntegerArray3 = {item1, item2, item3, item4, item5};
        int index3 = 2;
        Integer[] expectedIntegerArray3 = {item1, item2, item6, item3, item4, item5};
        return Stream.of(
                Arguments.of(preliminaryIntegerArray1, expectedIntegerArray1, item1, index1),
                Arguments.of(preliminaryIntegerArray2, expectedIntegerArray2, item6, index2),
                Arguments.of(preliminaryIntegerArray3, expectedIntegerArray3, item6, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestAdd2")
    void testAdd(Integer[] preliminaryIntegerArray, Integer[] expectedIntegerArray, Integer item, int index) {
        for (Integer s : preliminaryIntegerArray) {
            out.add(s);
        }
        assertEquals(item, out.add(index, item));
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestSet() {
        Integer[] preliminaryIntegerArray1 = {item2};
        int index1 = 0;
        Integer[] expectedIntegerArray1 = {item1};
        Integer[] preliminaryIntegerArray2 = {item1, item2, item3, item4, item5};
        int index2 = 4;
        Integer[] expectedIntegerArray2 = {item1, item2, item3, item4, item6};
        Integer[] preliminaryIntegerArray3 = {item1, item2, item3, item4, item5};
        int index3 = 2;
        Integer[] expectedIntegerArray3 = {item1, item2, item6, item4, item5};
        return Stream.of(
                Arguments.of(preliminaryIntegerArray1, expectedIntegerArray1, item1, index1),
                Arguments.of(preliminaryIntegerArray2, expectedIntegerArray2, item6, index2),
                Arguments.of(preliminaryIntegerArray3, expectedIntegerArray3, item6, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestSet")
    void testSet(Integer[] preliminaryIntegerArray, Integer[] expectedIntegerArray, Integer item, int index) {
        for (Integer s : preliminaryIntegerArray) {
            out.add(s);
        }
        assertEquals(item, out.set(index, item));
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestRemove1() {
        Integer[] preliminaryIntegerArray1 = {item1};
        Integer[] expectedIntegerArray1 = {};
        Integer[] preliminaryIntegerArray2 = {item1, item2};
        Integer[] expectedIntegerArray2 = {item2};
        Integer[] preliminaryIntegerArray3 = {item1, item2, item3, item4, item5};
        Integer[] expectedIntegerArray3 = {item1, item2, item3, item4};
        return Stream.of(
                Arguments.of(preliminaryIntegerArray1, expectedIntegerArray1, item1),
                Arguments.of(preliminaryIntegerArray2, expectedIntegerArray2, item1),
                Arguments.of(preliminaryIntegerArray3, expectedIntegerArray3, item5));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestRemove1")
    void testRemove(Integer[] preliminaryIntegerArray, Integer[] expectedIntegerArray, Integer item) {
        for (Integer s : preliminaryIntegerArray) {
            out.add(s);
        }
        assertEquals(item, out.remove(item));
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestRemove2() {
        Integer[] preliminaryIntegerArray1 = {item1};
        int index1 = 0;
        Integer[] expectedIntegerArray1 = {};
        Integer[] preliminaryIntegerArray2 = {item1, item2};
        int index2 = 0;
        Integer[] expectedIntegerArray2 = {item2};
        Integer[] preliminaryIntegerArray3 = {item1, item2, item3, item4, item5};
        int index3 = 4;
        Integer[] expectedIntegerArray3 = {item1, item2, item3, item4};
        return Stream.of(
                Arguments.of(preliminaryIntegerArray1, expectedIntegerArray1, index1),
                Arguments.of(preliminaryIntegerArray2, expectedIntegerArray2, index2),
                Arguments.of(preliminaryIntegerArray3, expectedIntegerArray3, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestRemove2")
    void testRemove(Integer[] preliminaryIntegerArray, Integer[] expectedIntegerArray, int index) {
        for (Integer s : preliminaryIntegerArray) {
            out.add(s);
        }
        assertEquals(preliminaryIntegerArray[index], out.remove(index));
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestContains() {
        Integer[] integerArray1 = {};
        Boolean expectedBoolean1 = false;
        Integer[] integerArray2 = {item1};
        Boolean expectedBoolean2 = true;
        Integer[] integerArray3 = {item1};
        Boolean expectedBoolean3 = false;
        Integer[] integerArray4 = {item1, item2, item3};
        Boolean expectedBoolean4 = true;
        return Stream.of(
                Arguments.of(integerArray1, item1, expectedBoolean1),
                Arguments.of(integerArray2, item1, expectedBoolean2),
                Arguments.of(integerArray3, item3, expectedBoolean3),
                Arguments.of(integerArray4, item3, expectedBoolean4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestContains")
    void testContains(Integer[] integerArray, Integer item, Boolean expectedBoolean) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedBoolean, out.contains(item));
    }

    public static Stream<Arguments> provideParamsForTestIndexOf() {
        Integer[] integerArray1 = {};
        int expectedResult1 = -1;
        Integer[] integerArray2 = {item1};
        int expectedResult2 = 0;
        Integer[] integerArray3 = {item1};
        int expectedResult3 = -1;
        Integer[] integerArray4 = {item3, item2, item3};
        int expectedResult4 = 0;
        return Stream.of(
                Arguments.of(integerArray1, item1, expectedResult1),
                Arguments.of(integerArray2, item1, expectedResult2),
                Arguments.of(integerArray3, item3, expectedResult3),
                Arguments.of(integerArray4, item3, expectedResult4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestIndexOf")
    void testIndexOf(Integer[] integerArray, Integer item, int expectedResult) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.indexOf(item));
        assertArrayEquals(integerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestLastIndexOf() {
        Integer[] integerArray1 = {};
        int expectedResult1 = -1;
        Integer[] integerArray2 = {item1};
        int expectedResult2 = 0;
        Integer[] integerArray3 = {item1};
        int expectedResult3 = -1;
        Integer[] integerArray4 = {item3, item2, item3};
        int expectedResult4 = 2;
        return Stream.of(
                Arguments.of(integerArray1, item1, expectedResult1),
                Arguments.of(integerArray2, item1, expectedResult2),
                Arguments.of(integerArray3, item3, expectedResult3),
                Arguments.of(integerArray4, item3, expectedResult4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestLastIndexOf")
    void testLastIndexOf(Integer[] integerArray, Integer item, int expectedResult) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.lastIndexOf(item));
        assertArrayEquals(integerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestGet() {
        Integer[] integerArray1 = {item1};
        int index1 = 0;
        Integer[] integerArray2 = {item1, item2, item3};
        int index2 = 1;
        Integer[] integerArray3 = {item3, item2, item3};
        int index3 = 2;
        return Stream.of(
                Arguments.of(integerArray1, item1, index1),
                Arguments.of(integerArray2, item2, index2),
                Arguments.of(integerArray3, item3, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestGet")
    void testGet(Integer[] integerArray, Integer expectedItem, int index) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedItem, out.get(index));
        assertArrayEquals(integerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestEquals() {
        Integer[] soursIntegerArray1 = {};
        Integer[] integerArrayToCompare1 = {};
        Boolean expectedBoolean1 = true;
        Integer[] soursIntegerArray2 = {item1, item2};
        Integer[] integerArrayToCompare2 = {item2};
        Boolean expectedBoolean2 = false;
        Integer[] soursIntegerArray3 = {item1, item2, item3, item4, item5};
        Integer[] integerArrayToCompare3 = {item1, item2, item3, item4, item5};
        Boolean expectedBoolean3 = true;
        Integer[] soursIntegerArray4 = {item1, item2};
        Integer[] integerArrayToCompare4 = {item1, item3};
        Boolean expectedBoolean4 = false;

        return Stream.of(
                Arguments.of(soursIntegerArray1, integerArrayToCompare1, expectedBoolean1),
                Arguments.of(soursIntegerArray2, integerArrayToCompare2, expectedBoolean2),
                Arguments.of(soursIntegerArray3, integerArrayToCompare3, expectedBoolean3),
                Arguments.of(soursIntegerArray4, integerArrayToCompare4, expectedBoolean4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestEquals")
    void testEquals(Integer[] soursIntegerArray, Integer[] integerArrayToCompare, Boolean expectedBoolean) {
        for (Integer s : soursIntegerArray) {
            out.add(s);
        }
        ArrayIntegerList IntegerListToCompare = new ArrayIntegerList(10);
        for (Integer s : integerArrayToCompare) {
            IntegerListToCompare.add(s);
        }
        assertEquals(expectedBoolean, out.equals(IntegerListToCompare));
    }

    public static Stream<Arguments> provideParamsForTestSize() {
        Integer[] integerArray1 = {};
        int expectedResult1 = 0;
        Integer[] integerArray2 = {item1};
        int expectedResult2 = 1;
        Integer[] integerArray3 = {item3, item2, item3};
        int expectedResult3 = 3;
        return Stream.of(
                Arguments.of(integerArray1, expectedResult1),
                Arguments.of(integerArray2, expectedResult2),
                Arguments.of(integerArray3, expectedResult3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestSize")
    void testSize(Integer[] integerArray, int expectedResult) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.size());
        assertArrayEquals(integerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestIsEmpty() {
        Integer[] integerArray1 = {};
        Boolean expectedBoolean1 = true;
        Integer[] integerArray2 = {item1};
        Boolean expectedBoolean2 = false;
        Integer[] integerArray3 = {item3, item2, item3};
        Boolean expectedBoolean3 = false;
        return Stream.of(
                Arguments.of(integerArray1, expectedBoolean1),
                Arguments.of(integerArray2, expectedBoolean2),
                Arguments.of(integerArray3, expectedBoolean3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestIsEmpty")
    void testIsEmpty(Integer[] integerArray, Boolean expectedBoolean) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedBoolean, out.isEmpty());
    }

    public static Stream<Arguments> provideParamsForTestLength() {
        Integer[] integerArray1 = {};
        int expectedResult1 = 0;
        Integer[] integerArray2 = {item1};
        int expectedResult2 = 1;
        Integer[] integerArray3 = {item3, item2, item3};
        int expectedResult3 = 3;
        return Stream.of(
                Arguments.of(integerArray1, expectedResult1),
                Arguments.of(integerArray2, expectedResult2),
                Arguments.of(integerArray3, expectedResult3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestLength")
    void testLength(Integer[] integerArray, int expectedResult) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.Length());
    }

    public static Stream<Arguments> provideParamsForTestClear() {
        Integer[] emptyArray = {};
        Integer[] integerArray1 = {};
        Integer[] integerArray2 = {item1};
        Integer[] integerArray3 = {item3, item2, item3};
        return Stream.of(
                Arguments.of(integerArray1, emptyArray),
                Arguments.of(integerArray2, emptyArray),
                Arguments.of(integerArray3, emptyArray));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestClear")
    void testClear(Integer[] integerArray, Integer[] emptyArray) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        out.clear();
        assertArrayEquals(emptyArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestToArray() {
        Integer[] expectedIntegerArray1 = {};
        Integer[] integerArray1 = {};
        Integer[] expectedIntegerArray2 = {item1};
        Integer[] integerArray2 = {item1};
        Integer[] expectedIntegerArray3 = {item1, item2, item3};
        Integer[] integerArray3 = {item1, item2, item3};

        return Stream.of(
                Arguments.of(expectedIntegerArray1, integerArray1),
                Arguments.of(expectedIntegerArray2, integerArray2),
                Arguments.of(expectedIntegerArray3, integerArray3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestToArray")
    void testToArray(Integer[] expectedIntegerArray, Integer[] integerArray) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertArrayEquals(expectedIntegerArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestToString() {
        String expectedString1 = "[]";
        Integer[] integerArray1 = {};
        String expectedString2 = "[" + item1 + "]";
        Integer[] integerArray2 = {item1};
        String expectedString3 = "[" + item1 + "," + item2 + "," + item3 + "]";
        Integer[] integerArray3 = {item1, item2, item3};

        return Stream.of(
                Arguments.of(integerArray1, expectedString1),
                Arguments.of(integerArray2, expectedString2),
                Arguments.of(integerArray3, expectedString3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestToString")
    void testToString(Integer[] integerArray, String expectedString) {
        for (Integer s : integerArray) {
            out.add(s);
        }
        assertEquals(expectedString, out.toString());
    }

    @Test
    void testArrayIntegerListNullPointerException() {
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.add(null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.add(0, null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.remove(null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.contains(null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.indexOf(null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.lastIndexOf(null));
        assertThrows(ArrayIntegerListNullPointerException.class, ()->out.equals(null));
    }

    @Test
    void testIndexOutOfBoundsException() {
        out.add(item1);
        out.add(item2);
        assertThrows(ArrayIntegerListIndexOutOfBoundsException.class, ()->out.add(2, item3));
        assertThrows(ArrayIntegerListIndexOutOfBoundsException.class, ()->out.set(2, item3));
        assertThrows(ArrayIntegerListIndexOutOfBoundsException.class, ()->out.remove(2));
        assertThrows(ArrayIntegerListIndexOutOfBoundsException.class, ()->out.get(2));
    }

    @Test
    void testArrayIntegerListElementNotFoundException() {
        assertThrows(ArrayIntegerListElementNotFoundException.class, ()->out.remove(item3));
        out.add(item3);
        out.add(item4);
        assertThrows(ArrayIntegerListElementNotFoundException.class, ()->out.remove(item1));

    }
}