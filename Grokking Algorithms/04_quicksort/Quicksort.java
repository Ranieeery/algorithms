import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Quicksort {
    public static void main(String[] args) {
        int[] arr = { 3, 4, 12, 8, 49, 1 };

        System.out.println(Arrays.toString(quicksort(arr)));
    }

    public static int[] quicksort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        } else {
            int pivot = arr[0];

            List<Integer> min = Arrays.stream(arr).boxed().skip(1).filter(x -> x <= pivot).collect(Collectors.toList());
            List<Integer> max = Arrays.stream(arr).boxed().skip(1).filter(x -> x > pivot).collect(Collectors.toList());

            int[] sortedMin = min.stream().mapToInt(Integer::intValue).toArray();
            int[] sortedMax = max.stream().mapToInt(Integer::intValue).toArray();

            int[] resultMin = quicksort(sortedMin);
            int[] resultMax = quicksort(sortedMax);

            int[] result = new int[resultMin.length + 1 + resultMax.length];
            System.arraycopy(resultMin, 0, result, 0, resultMin.length);
            result[resultMin.length] = pivot;
            System.arraycopy(resultMax, 0, result, resultMin.length + 1, resultMax.length);

            return result;
        }
    }
}
