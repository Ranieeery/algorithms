import java.util.Arrays;

public class Max {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 12, 8, 49, 1 };

        System.out.println(max(arr));
        System.out.println(maxAnswer(arr));
    }

    // My
    public static int max(int[] arr) {
        return max(arr, arr[0]);
    }

    public static int max(int[] arr, int max) {
        if (arr.length == 0) {
            return max;
        }

        if (arr[0] > max) {
            max = arr[0];
        }

        return max(Arrays.copyOfRange(arr, 1, arr.length), max);
    }

    // Answer
    public static int maxAnswer(int[] arr) {
        if (arr.length == 2) {
            return arr[0] > arr[1] ? arr[0] : arr[1];
        }

        int max = max(Arrays.copyOfRange(arr, 1, arr.length));
        return arr[0] > max ? arr[0] : max;
    }
}
