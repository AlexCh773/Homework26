package org.example;

import java.util.Arrays;

public class ArrayIntegerList implements CustomList<Integer> {
    private int currentNumberOfItems;
    private Integer[] integerArray;

    public ArrayIntegerList(int arraySize) {
        integerArray = new Integer[arraySize];
        currentNumberOfItems = 0;

    }

    @Override
    public Integer add(Integer item) {
        checkParameterForNull(item);
        if (integerArray.length == currentNumberOfItems) {
            increaseArraySize();
        }
        integerArray[currentNumberOfItems] = item;
        currentNumberOfItems++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkParameterForNull(item);
        if (integerArray.length == currentNumberOfItems) {
            increaseArraySize();
        }
        if (index >= currentNumberOfItems) {
            throw new ArrayIntegerListIndexOutOfBoundsException();
        }
        Integer[] arrayToShiftRight = Arrays.copyOfRange(integerArray, index, currentNumberOfItems);
        integerArray[index] = item;
        System.arraycopy(arrayToShiftRight, 0, integerArray, index + 1, arrayToShiftRight.length);
        currentNumberOfItems++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayIntegerListIndexOutOfBoundsException();
        }
        integerArray[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkParameterForNull(item);
        if (integerArray.length < 1) {
            throw new ArrayIntegerListElementNotFoundException();
        }
        for (int i = 0; i < currentNumberOfItems; i++) {
            if (integerArray[i].equals(item)) {
                if (i == integerArray.length - 1) {
                    integerArray[i] = null;
                } else {
                    Integer[] arrayToShiftLeft = Arrays.copyOfRange(integerArray, i + 1, currentNumberOfItems);
                    System.arraycopy(arrayToShiftLeft, 0, integerArray, i, arrayToShiftLeft.length);
                }
                currentNumberOfItems--;
                return item;
            }
        }
        throw new ArrayIntegerListElementNotFoundException();
    }

    @Override
    public Integer remove(int index) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayIntegerListIndexOutOfBoundsException();
        }
        Integer itemToDelete = integerArray[index];
        Integer[] arrayToShiftLeft = Arrays.copyOfRange(integerArray, index + 1, currentNumberOfItems);
        System.arraycopy(arrayToShiftLeft, 0, integerArray, index, arrayToShiftLeft.length);
        currentNumberOfItems--;
        return itemToDelete;
    }

    @Override
    public boolean contains(Integer item) {
        checkParameterForNull(item);
        return binarySearch(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkParameterForNull(item);
        for (int i = 0; i < currentNumberOfItems; i++) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkParameterForNull(item);
        if (integerArray.length < 1) {
            return -1;
        }
        for (int i = currentNumberOfItems - 1; i >= 0; i--) {
            if (integerArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= currentNumberOfItems || index < 0) {
            throw new ArrayIntegerListIndexOutOfBoundsException();
        }
        return integerArray[index];
    }

    @Override
    public boolean equals(CustomList otherList) {
        checkParameterForNull(otherList);
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
            integerArray[i] = null;
        }
        currentNumberOfItems = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOfRange(integerArray, 0, currentNumberOfItems);
    }

    private void increaseArraySize() {
        Integer[] newIntegerList = new Integer[currentNumberOfItems * 2 + 1];
        if (currentNumberOfItems >= 0) System.arraycopy(integerArray, 0, newIntegerList, 0, currentNumberOfItems);
        integerArray = newIntegerList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < currentNumberOfItems; i++) {
            result.append(integerArray[i]);
            if (i < currentNumberOfItems - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }

    private static void checkParameterForNull(Object item) {
        if (item == null) {
            throw new ArrayIntegerListNullPointerException();
        }
    }

    private int binarySearch(Integer item) {
        sort();
        if (currentNumberOfItems < 1) {
            return -1;
        }
        int startingIndex = 0;
        int finalIndex = currentNumberOfItems - 1;
        int rangeSize = currentNumberOfItems;
        int indexForComparison;
        while (rangeSize >= 1) {
            rangeSize /= 2;
            indexForComparison = startingIndex + rangeSize;
            if (integerArray[indexForComparison] == item) {
                return indexForComparison;
            } else if (rangeSize == 0) {
                return -1;
            } else if (integerArray[indexForComparison] > item) {
                finalIndex = indexForComparison - 1;
            } else if (integerArray[indexForComparison] < item) {
                startingIndex = indexForComparison + 1;
            }
            rangeSize = finalIndex - startingIndex + 1;
        }
        return -1;
    }

    public void sort() {
        quickSort(integerArray, 0, currentNumberOfItems - 1);
    }

    private static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
