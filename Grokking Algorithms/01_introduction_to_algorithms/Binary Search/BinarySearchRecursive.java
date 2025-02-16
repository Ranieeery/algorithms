import java.lang.Math;

public class BinarySearchRecursive {
    public static void main(String[] args) {
        int[] list = {1, 3, 5, 7, 9};
        int min = 0;
        int max = list.length - 1;

        System.out.println(binarySearchRecursive(list, 3, min, max));
    }

    public static int binarySearchRecursive(int[] list, int number, int min, int max) {

        if (min <= max) {
            int mid = Math.round((min + max) / 2);
            int guess = list[mid];

            if(guess == number){
                return mid;
            }

            if (guess > number) {
                max = mid - 1;
                return binarySearchRecursive(list, number, min, max);
            } else {
                min = mid + 1;
                return binarySearchRecursive(list, number, min, max);
            }
        }

        return 0;
    }
}