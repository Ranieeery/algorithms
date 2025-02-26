public class Fatorial {
    public static void main(String[] args) {
        System.out.println(fatorial(31));
    }

    public static long fatorial(int x) {
        if (x == 1) {
            return 1;
        } else {
            return x * fatorial(x - 1);
        }
    }
}
