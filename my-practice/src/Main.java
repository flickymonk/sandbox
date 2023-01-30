import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int min = min(234, 234234, 13, 14, 25, 3);
        System.out.println(min);
    }


    private static int min(int... ints) {
        int min = ints[0];
        for (int i = 1; i < ints.length; i++) {
            if (ints[i] < min) {
                min = ints[i];
            }
        }
        return min;
    }
}