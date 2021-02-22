package algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int maxValue = 10000;
        int maxLength = 100000;

        System.out.println("开始");
        for (int i = 0; i < 1000000; i++) {
            int [] a = randomArray(maxValue, maxLength);
            mergeSort2(a);
            if (!check(a)) {
                System.out.println("出错\t" + i);
                System.out.println(Arrays.toString(a));
                break;
            }
        }
        System.out.println("结束");

    }

    private static boolean check(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 归并排序
     * 递归
     *
     * @param array
     */
    public static void mergeSort2(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int l = 0;
        int m = 0;
        int r = 0;
        int step = 1;
        int length = array.length;
        while (step < length) {
            l = 0;
            while (l < length) {
                if ((l + step - 1) >= length) {
                    m = length - 1;
                } else {
                    m = l + step - 1;
                }
                if ((m + step) >= length) {
                    r = length - 1;
                } else {
                    r = m + step;
                }
                merge(l, m, r, array);
                l = r + 1;
            }
            step *= 2;
        }
    }


    /**
     * 归并排序
     *
     * @param array
     */
    public static void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        mergeSort(0, array.length - 1, array);
    }

    /**
     * 归并排序 递归实现
     *
     * @param l
     * @param r
     * @param array
     */
    private static void mergeSort(int l, int r, int[] array) {
        if (l == r) {
            return;
        }

        int m = l + ((r - l) >> 1);
        mergeSort(l, m, array);
        mergeSort(m + 1, r, array);
        merge(l, m, r, array);
    }

    private static void merge(int l, int m, int r, int[] array) {
        int[] temp = new int[r - l + 1];
        int i = l;
        int j = m + 1;
        int k = 0;
        while (i <= m && j <= r) {
            temp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= m) {
            temp[k++] = array[i++];
        }
        while (j <= r) {
            temp[k++] = array[j++];
        }
        for (int n = 0; n < temp.length; n++) {
            array[n + l] = temp[n];
        }
    }

    public static int[] randomArray(int maxValue, int maxLength) {
        int length = (int) (maxLength * Math.random());
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (maxValue * Math.random());
        }
        return array;
    }

}
