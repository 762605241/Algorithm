package problems;

import java.util.Arrays;

public class PrefixArray {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = prefixArray(a);
        System.out.println(Arrays.toString(b));
    }

    /**
     * 前缀和数组
     * @param a
     * @return
     */
    public static int[] prefixArray(int[] a) {
        int[] b = new int[a.length];
        b[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] + a[i];
        }
        return b;
    }
}
