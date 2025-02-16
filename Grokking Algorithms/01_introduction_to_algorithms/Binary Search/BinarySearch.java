import java.lang.Math;
import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] list = { 15, 27, 39, 77, 92, 108, 121, 135, 200, 432, 12, 8, 1, 0 };
        list = Arrays.stream(list).sorted().toArray();

        System.out.println(binarySearch(list, 77));
    }

    public static int binarySearch(int[] list, int number) {
        int min = 0;
        int max = list.length - 1;

        while (min <= max) {
            int mid = Math.round((min + max) / 2);
            int guess = list[mid];

            if (guess == number) {
                return mid;
            }

            if (guess > number) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return 0;
    }
}