package org.example;

import java.util.Random;

public class ComparisonOfSorts {

    public static void compareSorts() {
        int[] randomArray1 = new int[100_000];
        Random random = new Random();
        for (int i = 0; i < randomArray1.length; i++) {
            randomArray1[i] = random.nextInt(100_000);
        }
        int[] randomArray2 = randomArray1.clone();
        int[] randomArray3 = randomArray1.clone();
        long start1 = System.currentTimeMillis();
        sortBubble(randomArray1);
        System.out.println("bubble sorting method: " + (System.currentTimeMillis() - start1));
        long start2 = System.currentTimeMillis();
        sortSelection(randomArray2);
        System.out.println("selection sorting method: " + (System.currentTimeMillis() - start2));
        long start3 = System.currentTimeMillis();
        sortInsertion(randomArray3);
        System.out.println("insertion sorting method: " + (System.currentTimeMillis() - start3));
    }

    private static void sortSelection(int[] array) {
        int min;
        int indexMin = -1;
        for (int i = 0; i < array.length - 1; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    indexMin = j;
                    min = array[j];
                }
            }
            swapElements(array, i, indexMin);
        }
    }

    private static void sortBubble(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
