import java.util.Arrays;

public class Sum {
    public static void main(String[] args) {
        int[] list = { 2, 4, 6 };

        System.out.println(sum(list));
    }

    public static int sum(int[] arr) {
        int total = 0;

        if (arr.length == 0) {
            return 0;
        }

        total = arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));

        return total;
    }
}
