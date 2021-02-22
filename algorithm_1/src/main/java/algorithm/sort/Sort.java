package algorithm.sort;

public class Sort {
    public static void main(String[] args) {
        int[] a = {3, 99, 43, 1, 0, 34, 9, 33, 12, 1, 43};
        mergeSort2(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }


    /**
     * 归并排序
     * 非递归实现
     * 通过 步长 概念来分段排序
     * 步长 一次 为 1、2、4 …… 2^n
     * 每次从0开始，分别找两组步长个数的元素，分别作为左部分和右部分，合并
     * 步长=1 每次取一个元素作为左部分，再取一个元素作为右部分，两部分都有序，合并。依次两两取出，遍历整个数组。
     * 步长=2 每次取两个元素作为左部分，再取两个元素作为右部分，步长=1时进行了排序，两部分都有序，合并。遍历整个数组。
     * 步长递增，直到步长大于数组长度，结束。
     * <p>
     * 将边界判断的加法和乘法，换成减法和除法，可以避免加法和乘法导致越界问题（超过整数最大值，变成负数）
     *
     * @param array
     */
    public static void mergeSort2(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int step = 1;
        int l = 0; // 左边界
        int m = 0; // 中点
        int r = 0; // 右边界
        int n = array.length; // 数组长度

        while (step < n) {
            l = 0;
            while (l < n) {
                // l + step < n 左边界和右边界的中点是否越界
                if (n - l >= step) {
                    m = l + step - 1;
                } else {
                    m = n - 1;
                }
                // 中点已经是数组最后一个元素，即无右边界
                if (m == n - 1) {
                    break;
                }
                // 计算右边界下表，m + step 即右边界下表，如果越界则右边界为数组最后一个元素，未越界则是m + step
                if (n - 1 - m >= step) {
                    r = m + step;
                } else {
                    r = n - 1;
                }
                merge(array, l, m, r);
                if (r == n - 1) {
                    break;
                } else {
                    // 下一个左边界
                    l = r + 1;
                }
            }
            if (step > n / 2) {
                break;
            } else {
                step *= 2;
            }
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
        mergeSort(array, 0, array.length - 1);
    }

    /**
     * 归并排序
     * 递归实现
     *
     * @param array
     * @param L
     * @param R
     */
    private static void mergeSort(int[] array, int L, int R) {
        if (L == R) {
            return;
        }

        // 等同于 int mid = (L + R) / 2;都是求中点位置。
        // L + R 当下标很大，可能会造成越界。
        // 所以使用下面的求中点方法。
        int mid = L + ((R - L) >> 1);
        mergeSort(array, L, mid);
        mergeSort(array, mid + 1, R);
        merge(array, L, mid, R);
    }

    public static void merge(int[] array, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            temp[i++] = array[p1] <= array[p2] ? array[p1++] : array[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = array[p1++];
        }
        while (p2 <= R) {
            temp[i++] = array[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            array[L + i] = temp[i];
        }
    }


    /**
     * 快速排序
     *
     * @param array
     */
    public static void quickSort(int[] array) {

    }


    /**
     * 选择排序
     * 思路：每次在数组中选择最小的，依次放在数组的头部
     *
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
     *
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
     *
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
