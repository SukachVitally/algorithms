public class BubbleSorting {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 2, 1, 4}; // Example array to be sorted

        System.out.println("Array before sorting:");
        printArray(array);

        bubbleSort(array);

        System.out.println("\nArray after sorting:");
        printArray(array);
    }

    // Bubble sort algorithm
    public static void bubbleSort(int[] arr) {
        for (var i = 0; i < arr.length; i++ ) {
            for (var j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    var temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
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