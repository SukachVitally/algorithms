package algorithms;

import java.util.Arrays;

public class QuickSorting {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 2, 1, 4}; // Example array to be sorted

        System.out.println("Array before sorting:");
        printArray(array);

        sort(array);

        System.out.println("\nArray after sorting:");
        printArray(array);
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    private static void quickSort(int[] arr, int from, int to) {
        if (to - from < 2) {
            return;
        }
        var pivotIndex = from;
        for (var i = from + 1; i < to; i++) {
            if (arr[pivotIndex] > arr[i]) {
                var temp = arr[pivotIndex];
                arr[pivotIndex] = arr[i];
                arr[i] = temp;
            }
        }
        quickSort(arr, from, pivotIndex);
        quickSort(arr, pivotIndex + 1, to);
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}