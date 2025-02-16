import java.lang.Math;

public class BinarySearch {
    public static void main(String[] args) {
        int[] list = {1, 3, 5, 7, 9};

        System.out.println(BinarySearch(list, 3));
    }

    public static int BinarySearch(int[] list, int number) {
        int min = 0;
        int max = list.length - 1;

        while(min <= max) {
            int mid = Math.round((min + max) / 2);
            int guess = list[mid];

            if(guess == number){
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