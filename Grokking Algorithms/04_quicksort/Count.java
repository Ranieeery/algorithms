import java.util.Arrays;

public class Count {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 12, 8, 49, 1 };

        System.out.println(count(arr));
    }

    public static int count(int[] arr) {
        int total = 1;

        if (arr.length == 0) {
            return 0;
        }

        return count(Arrays.copyOfRange(arr, 1, arr.length)) + total;
    }
}
