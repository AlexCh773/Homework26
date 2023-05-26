package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStringListTest {
    public final ArrayStringList out = new ArrayStringList(5);
    public static String item1 = "dog";
    public static String item2 = "cat";
    public static String item3 = "fish";
    public static String item4 = " ";
    public static String item5 = "wulf";
    public static String item6 = "human";


    @AfterEach
    void afterEach() {
        out.clear();
    }

    public static Stream<Arguments> provideParamsForTestAdd1() {
        String[] expectedStringArray1 = {item1};
        String[] expectedStringArray2 = {item1, item2};
        String[] expectedStringArray3 = {item1, item2, item3, item4, item5, item6};

        return Stream.of(Arguments.of(expectedStringArray1, item1),
                Arguments.of(expectedStringArray2, item2),
                Arguments.of(expectedStringArray3, item6));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestAdd1")
    void testAdd(String[] expectedStringArray, String item) {
        for (int i = 0; i < expectedStringArray.length - 1; i++) {
            out.add(expectedStringArray[i]);
        }
        assertEquals(item, out.add(item));
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestAdd2() {
        String[] preliminaryStringArray1 = {item2};
        int index1 = 0;
        String[] expectedStringArray1 = {item1, item2};
        String[] preliminaryStringArray2 = {item1, item2, item3, item4, item5};
        int index2 = 4;
        String[] expectedStringArray2 = {item1, item2, item3, item4, item6, item5};
        String[] preliminaryStringArray3 = {item1, item2, item3, item4, item5};
        int index3 = 2;
        String[] expectedStringArray3 = {item1, item2, item6, item3, item4, item5};
        return Stream.of(
                Arguments.of(preliminaryStringArray1, expectedStringArray1, item1, index1),
                Arguments.of(preliminaryStringArray2, expectedStringArray2, item6, index2),
                Arguments.of(preliminaryStringArray3, expectedStringArray3, item6, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestAdd2")
    void testAdd(String[] preliminaryStringArray, String[] expectedStringArray, String item, int index) {
        for (String s : preliminaryStringArray) {
            out.add(s);
        }
        assertEquals(item, out.add(index, item));
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestSet() {
        String[] preliminaryStringArray1 = {item2};
        int index1 = 0;
        String[] expectedStringArray1 = {item1};
        String[] preliminaryStringArray2 = {item1, item2, item3, item4, item5};
        int index2 = 4;
        String[] expectedStringArray2 = {item1, item2, item3, item4, item6};
        String[] preliminaryStringArray3 = {item1, item2, item3, item4, item5};
        int index3 = 2;
        String[] expectedStringArray3 = {item1, item2, item6, item4, item5};
        return Stream.of(
                Arguments.of(preliminaryStringArray1, expectedStringArray1, item1, index1),
                Arguments.of(preliminaryStringArray2, expectedStringArray2, item6, index2),
                Arguments.of(preliminaryStringArray3, expectedStringArray3, item6, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestSet")
    void testSet(String[] preliminaryStringArray, String[] expectedStringArray, String item, int index) {
        for (String s : preliminaryStringArray) {
            out.add(s);
        }
        assertEquals(item, out.set(index, item));
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestRemove1() {
        String[] preliminaryStringArray1 = {item1};
        String[] expectedStringArray1 = {};
        String[] preliminaryStringArray2 = {item1, item2};
        String[] expectedStringArray2 = {item2};
        String[] preliminaryStringArray3 = {item1, item2, item3, item4, item5};
        String[] expectedStringArray3 = {item1, item2, item3, item4};
        return Stream.of(
                Arguments.of(preliminaryStringArray1, expectedStringArray1, item1),
                Arguments.of(preliminaryStringArray2, expectedStringArray2, item1),
                Arguments.of(preliminaryStringArray3, expectedStringArray3, item5));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestRemove1")
    void testRemove(String[] preliminaryStringArray, String[] expectedStringArray, String item) {
        for (String s : preliminaryStringArray) {
            out.add(s);
        }
        assertEquals(item, out.remove(item));
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestRemove2() {
        String[] preliminaryStringArray1 = {item1};
        int index1 = 0;
        String[] expectedStringArray1 = {};
        String[] preliminaryStringArray2 = {item1, item2};
        int index2 = 0;
        String[] expectedStringArray2 = {item2};
        String[] preliminaryStringArray3 = {item1, item2, item3, item4, item5};
        int index3 = 4;
        String[] expectedStringArray3 = {item1, item2, item3, item4};
        return Stream.of(
                Arguments.of(preliminaryStringArray1, expectedStringArray1, index1),
                Arguments.of(preliminaryStringArray2, expectedStringArray2, index2),
                Arguments.of(preliminaryStringArray3, expectedStringArray3, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestRemove2")
    void testRemove(String[] preliminaryStringArray, String[] expectedStringArray, int index) {
        for (String s : preliminaryStringArray) {
            out.add(s);
        }
        assertEquals(preliminaryStringArray[index], out.remove(index));
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestContains() {
        String[] stringArray1 = {};
        Boolean expectedBoolean1 = false;
        String[] stringArray2 = {item1};
        Boolean expectedBoolean2 = true;
        String[] stringArray3 = {item1};
        Boolean expectedBoolean3 = false;
        String[] stringArray4 = {item1, item2, item3};
        Boolean expectedBoolean4 = true;
        return Stream.of(
                Arguments.of(stringArray1, item1, expectedBoolean1),
                Arguments.of(stringArray2, item1, expectedBoolean2),
                Arguments.of(stringArray3, item3, expectedBoolean3),
                Arguments.of(stringArray4, item3, expectedBoolean4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestContains")
    void testContains(String[] stringArray, String item, Boolean expectedBoolean) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedBoolean, out.contains(item));
        assertArrayEquals(stringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestIndexOf() {
        String[] stringArray1 = {};
        int expectedResult1 = -1;
        String[] stringArray2 = {item1};
        int expectedResult2 = 0;
        String[] stringArray3 = {item1};
        int expectedResult3 = -1;
        String[] stringArray4 = {item3, item2, item3};
        int expectedResult4 = 0;
        return Stream.of(
                Arguments.of(stringArray1, item1, expectedResult1),
                Arguments.of(stringArray2, item1, expectedResult2),
                Arguments.of(stringArray3, item3, expectedResult3),
                Arguments.of(stringArray4, item3, expectedResult4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestIndexOf")
    void testIndexOf(String[] stringArray, String item, int expectedResult) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.indexOf(item));
        assertArrayEquals(stringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestLastIndexOf() {
        String[] stringArray1 = {};
        int expectedResult1 = -1;
        String[] stringArray2 = {item1};
        int expectedResult2 = 0;
        String[] stringArray3 = {item1};
        int expectedResult3 = -1;
        String[] stringArray4 = {item3, item2, item3};
        int expectedResult4 = 2;
        return Stream.of(
                Arguments.of(stringArray1, item1, expectedResult1),
                Arguments.of(stringArray2, item1, expectedResult2),
                Arguments.of(stringArray3, item3, expectedResult3),
                Arguments.of(stringArray4, item3, expectedResult4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestLastIndexOf")
    void testLastIndexOf(String[] stringArray, String item, int expectedResult) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.lastIndexOf(item));
        assertArrayEquals(stringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestGet() {
        String[] stringArray1 = {item1};
        int index1 = 0;
        String[] stringArray2 = {item1, item2, item3};
        int index2 = 1;
        String[] stringArray3 = {item3, item2, item3};
        int index3 = 2;
        return Stream.of(
                Arguments.of(stringArray1, item1, index1),
                Arguments.of(stringArray2, item2, index2),
                Arguments.of(stringArray3, item3, index3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestGet")
    void testGet(String[] stringArray, String expectedItem, int index) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedItem, out.get(index));
        assertArrayEquals(stringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestEquals() {
        String[] soursStringArray1 = {};
        String[] stringArrayToCompare1 = {};
        Boolean expectedBoolean1 = true;
        String[] soursStringArray2 = {item1, item2};
        String[] stringArrayToCompare2 = {item2};
        Boolean expectedBoolean2 = false;
        String[] soursStringArray3 = {item1, item2, item3, item4, item5};
        String[] stringArrayToCompare3 = {item1, item2, item3, item4, item5};
        Boolean expectedBoolean3 = true;
        String[] soursStringArray4 = {item1, item2};
        String[] stringArrayToCompare4 = {item1, item3};
        Boolean expectedBoolean4 = false;

        return Stream.of(
                Arguments.of(soursStringArray1, stringArrayToCompare1, expectedBoolean1),
                Arguments.of(soursStringArray2, stringArrayToCompare2, expectedBoolean2),
                Arguments.of(soursStringArray3, stringArrayToCompare3, expectedBoolean3),
                Arguments.of(soursStringArray4, stringArrayToCompare4, expectedBoolean4));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestEquals")
    void testEquals(String[] soursStringArray, String[] stringArrayToCompare, Boolean expectedBoolean) {
        for (String s : soursStringArray) {
            out.add(s);
        }
        ArrayStringList stringListToCompare = new ArrayStringList(10);
        for (String s : stringArrayToCompare) {
            stringListToCompare.add(s);
        }
        assertEquals(expectedBoolean, out.equals(stringListToCompare));
    }

    public static Stream<Arguments> provideParamsForTestSize() {
        String[] stringArray1 = {};
        int expectedResult1 = 0;
        String[] stringArray2 = {item1};
        int expectedResult2 = 1;
        String[] stringArray3 = {item3, item2, item3};
        int expectedResult3 = 3;
        return Stream.of(
                Arguments.of(stringArray1, expectedResult1),
                Arguments.of(stringArray2, expectedResult2),
                Arguments.of(stringArray3, expectedResult3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestSize")
    void testSize(String[] stringArray, int expectedResult) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.size());
        assertArrayEquals(stringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestIsEmpty() {
        String[] stringArray1 = {};
        Boolean expectedBoolean1 = true;
        String[] stringArray2 = {item1};
        Boolean expectedBoolean2 = false;
        String[] stringArray3 = {item3, item2, item3};
        Boolean expectedBoolean3 = false;
        return Stream.of(
                Arguments.of(stringArray1, expectedBoolean1),
                Arguments.of(stringArray2, expectedBoolean2),
                Arguments.of(stringArray3, expectedBoolean3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestIsEmpty")
    void testIsEmpty(String[] stringArray, Boolean expectedBoolean) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedBoolean, out.isEmpty());
    }

    public static Stream<Arguments> provideParamsForTestLength() {
        String[] stringArray1 = {};
        int expectedResult1 = 0;
        String[] stringArray2 = {item1};
        int expectedResult2 = 1;
        String[] stringArray3 = {item3, item2, item3};
        int expectedResult3 = 3;
        return Stream.of(
                Arguments.of(stringArray1, expectedResult1),
                Arguments.of(stringArray2, expectedResult2),
                Arguments.of(stringArray3, expectedResult3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestLength")
    void testLength(String[] stringArray, int expectedResult) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedResult, out.Length());
    }

    public static Stream<Arguments> provideParamsForTestClear() {
        String[] emptyArray = {};
        String[] stringArray1 = {};
        String[] stringArray2 = {item1};
        String[] stringArray3 = {item3, item2, item3};
        return Stream.of(
                Arguments.of(stringArray1, emptyArray),
                Arguments.of(stringArray2, emptyArray),
                Arguments.of(stringArray3, emptyArray));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestClear")
    void testClear(String[] stringArray, String[] emptyArray) {
        for (String s : stringArray) {
            out.add(s);
        }
        out.clear();
        assertArrayEquals(emptyArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestToArray() {
        String[] expectedStringArray1 = {};
        String[] stringArray1 = {};
        String[] expectedStringArray2 = {item1};
        String[] stringArray2 = {item1};
        String[] expectedStringArray3 = {item1, item2, item3};
        String[] stringArray3 = {item1, item2, item3};

        return Stream.of(
                Arguments.of(expectedStringArray1, stringArray1),
                Arguments.of(expectedStringArray2, stringArray2),
                Arguments.of(expectedStringArray3, stringArray3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestToArray")
    void testToArray(String[] expectedStringArray, String[] stringArray) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertArrayEquals(expectedStringArray, out.toArray());
    }

    public static Stream<Arguments> provideParamsForTestToString() {
        String expectedString1 = "[]";
        String[] stringArray1 = {};
        String expectedString2 = "[" + item1 + "]";
        String[] stringArray2 = {item1};
        String expectedString3 = "[" + item1 + "," + item2 + "," + item3 + "]";
        String[] stringArray3 = {item1, item2, item3};

        return Stream.of(
                Arguments.of(stringArray1, expectedString1),
                Arguments.of(stringArray2, expectedString2),
                Arguments.of(stringArray3, expectedString3));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestToString")
    void testToString(String[] stringArray, String expectedString) {
        for (String s : stringArray) {
            out.add(s);
        }
        assertEquals(expectedString, out.toString());
    }

    @Test
    void testArrayStringListNullPointerException() {
        assertThrows(ArrayStringListNullPointerException.class, ()->out.add(null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.add(0, null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.remove(null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.contains(null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.indexOf(null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.lastIndexOf(null));
        assertThrows(ArrayStringListNullPointerException.class, ()->out.equals(null));
    }

    @Test
    void testIndexOutOfBoundsException() {
        out.add(item1);
        out.add(item2);
        assertThrows(ArrayStringListIndexOutOfBoundsException.class, ()->out.add(2, item3));
        assertThrows(ArrayStringListIndexOutOfBoundsException.class, ()->out.set(2, item3));
        assertThrows(ArrayStringListIndexOutOfBoundsException.class, ()->out.remove(2));
        assertThrows(ArrayStringListIndexOutOfBoundsException.class, ()->out.get(2));
    }

    @Test
    void testArrayStringListElementNotFoundException() {
        assertThrows(ArrayStringListElementNotFoundException.class, ()->out.remove(item3));
        out.add(item3);
        out.add(item4);
        assertThrows(ArrayStringListElementNotFoundException.class, ()->out.remove(item1));

    }
}