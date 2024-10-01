package algorithms;

import java.util.Arrays;

public class MergeSorting {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 2, 1, 4}; // Example array to be sorted

        System.out.println("Array before sorting:");
        printArray(array);

        array = sort(array);

        System.out.println("\nArray after sorting:");
        printArray(array);
    }

    public static int[] sort(int[] arr) {
        var len = arr.length;
        if (len < 2) {
            return arr;
        }
        var middle = len / 2;
        System.out.println(middle);
        var arr1 = sort(Arrays.copyOfRange(arr, 0, middle));
        var arr2 = sort(Arrays.copyOfRange(arr, middle, len));

        var res = new int[len];
        var ind1 = 0;
        var ind2 = 0;
        for(var i = 0; i < len; i++) {
            var var1 = arr1.length > ind1 ? arr1[ind1] : null;
            var var2 = arr2.length > ind2 ? arr2[ind2] : null;
            if (var1 != null && (var2 == null || var1 < var2)) {
                res[i] = var1;
                ind1++;
                continue;
            }

            if (var2 != null) {
                res[i] = var2;
                ind2++;
            }
        }

        return res;
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}