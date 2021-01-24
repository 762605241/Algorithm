package sort;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] a = { 3, 99, 43, 1, 0, 34, 9, 33, 12, 1, 43 };
        insertSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

    /**
     * 选择排序
     * 思路：每次在数组中选择最小的，依次放在数组的头部
     * @param a
     */
    public static void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] <= a[min]) {
                    min = j;
                }
            }
            if (i != min) {
                a[i] = a[i] + a[min];
                a[min] = a[i] - a[min];
                a[i] = a[i] - a[min];
            }
        }
    }

    /**
     * 冒泡排序
     * 思路：依次比较相邻两数的大小，根据大小交换位置。每一轮结束后，当前最大值即放在数组末尾
     * @param a
     */
    public static void bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j + 1] < a[j]) {
                    a[j + 1] = a[j] ^ a[j + 1];
                    a[j] = a[j] ^ a[j + 1];
                    a[j + 1] = a[j] ^ a[j + 1];
                }
            }
        }
    }


    /**
     * 插入排序
     * 思路：每次选择一个数，将该数插入，前面有序区间内，且保证插入后整个区间内有序。
     * @param a
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i;
            while (j > 0 && a[j] < a[j - 1]) {
                a[j] = a[j] ^ a[j - 1];
                a[j - 1] = a[j] ^ a[j - 1];
                a[j] = a[j] ^ a[j - 1];
                j--;
            }
        }
    }
}
