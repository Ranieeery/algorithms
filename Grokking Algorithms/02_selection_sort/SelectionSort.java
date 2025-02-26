public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 3, 6, 2, 10 };

        System.out.println(selectionSort(arr));
    }

    public static int findSmallest(int[] arr) {
        int smallest = arr[0];
        int smallestIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static int[] selectionSort(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int smallestIndex = findSmallest(arr);
            newArr[i] = arr[smallestIndex];
            arr[smallestIndex] = Integer.MAX_VALUE;
        }
        return newArr;
    }
}