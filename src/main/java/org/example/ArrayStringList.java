package org.example;

import java.util.Arrays;

public class ArrayStringList implements StringList {
    private int currentNumberOfItems;
    private String[] stringArray;

    public ArrayStringList(int arraySize) {
        stringArray = new String[arraySize];
        currentNumberOfItems = 0;

    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        if (stringArray.length == currentNumberOfItems) {
            increaseArraySize();
        }
        stringArray[currentNumberOfItems] = item;
        currentNumberOfItems++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        if (stringArray.length == currentNumberOfItems) {
            increaseArraySize();
        }
        if (index >= currentNumberOfItems) {
            throw new ArrayStringListIndexOutOfBoundsException();
        }
        String[] arrayToShiftRight = Arrays.copyOfRange(stringArray, index, currentNumberOfItems);
        stringArray[index] = item;
        System.arraycopy(arrayToShiftRight, 0, stringArray, index + 1, arrayToShiftRight.length);
        currentNumberOfItems++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayStringListIndexOutOfBoundsException();
        }
        stringArray[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        if (stringArray.length < 1) {
            throw new ArrayStringListElementNotFoundException();
        }
        for (int i = 0; i < currentNumberOfItems; i++) {
            if (stringArray[i].equals(item)) {
                if (i == stringArray.length - 1) {
                    stringArray[i] = null;
                } else {
                    String[] arrayToShiftLeft = Arrays.copyOfRange(stringArray, i + 1, currentNumberOfItems);
                    System.arraycopy(arrayToShiftLeft, 0, stringArray, i, arrayToShiftLeft.length);
                }
                currentNumberOfItems--;
                return item;
            }
        }
        throw new ArrayStringListElementNotFoundException();
    }

    @Override
    public String remove(int index) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayStringListIndexOutOfBoundsException();
        }
        String itemToDelete = stringArray[index];
        String[] arrayToShiftLeft = Arrays.copyOfRange(stringArray, index + 1, currentNumberOfItems);
        System.arraycopy(arrayToShiftLeft, 0, stringArray, index, arrayToShiftLeft.length);
        currentNumberOfItems--;
        return itemToDelete;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        for (int i = 0; i < currentNumberOfItems; i++) {
            if (stringArray[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        for (int i = 0; i < currentNumberOfItems; i++) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new ArrayStringListNullPointerException();
        }
        if (stringArray.length < 1) {
            return -1;
        }
        for (int i = currentNumberOfItems - 1; i >= 0; i--) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayStringListIndexOutOfBoundsException();
        }
        return stringArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new ArrayStringListNullPointerException();
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return currentNumberOfItems;
    }

    @Override
    public boolean isEmpty() {
        return currentNumberOfItems == 0;
    }

    public int Length() {
        return currentNumberOfItems;
    }

    @Override
    public void clear() {
        for (int i = 0; i < currentNumberOfItems; i++) {
            stringArray[i] = null;
        }
        currentNumberOfItems = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOfRange(stringArray, 0, currentNumberOfItems);
    }

    private void increaseArraySize() {
        String[] newStringList = new String[currentNumberOfItems * 2 + 1];
        if (currentNumberOfItems >= 0) System.arraycopy(stringArray, 0, newStringList, 0, currentNumberOfItems);
        stringArray = newStringList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < currentNumberOfItems; i++) {
            result.append(stringArray[i]);
            if (i < currentNumberOfItems - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }
}
