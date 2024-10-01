package algorithms;

public class InsertSorting {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 2, 1, 4}; // Example array to be sorted

        System.out.println("Array before sorting:");
        printArray(array);

        insertSort(array);

        System.out.println("\nArray after sorting:");
        printArray(array);
    }

    public static void insertSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            var key = arr[i];
            var j = i - 1;
            while (j >= 0) {
                if (key < arr[j]) {
                    arr[j + 1] = arr[j];
                    arr[j] = key;
                }

                j = j - 1;
            }
        }
    }

    // Utility method to print an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}