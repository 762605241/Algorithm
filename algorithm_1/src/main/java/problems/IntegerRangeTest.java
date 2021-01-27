package problems;

public class IntegerRangeTest {
    /*
    public static void main(String[] args) {
        */

    /**
     * 当超过Long范围后，重新计算。也就是说，当1左移32位后，达到最大范围1后面全是0，再左移时，返回1，回到1位二进制，重新开始。
     * 问题：
     * 1、原理是什么？
     * 2、为什么使用Long.toBinaryString打印时，前面都是1？
     * 因为此时，达到Integer的最小负数，可以打印这个数字来看，那么这个数是一个负数，那么此时通过方法获取二进制的时候前面就都是1（负数如何表示参考：源码补码反码文章）
     *//*
        System.out.println(Integer.toBinaryString(1 << 31));
        System.out.println(1 << 31);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Long.toBinaryString(1 << 32));
        System.out.println(Long.toBinaryString(1 << 33));
        System.out.println(Long.toBinaryString(1 << 34));
        System.out.println(Long.toBinaryString(1 << 35));
        System.out.println(Long.toBinaryString(1 << 36));
        System.out.println(Long.toBinaryString(1 << 37));
        System.out.println(Long.toBinaryString(1 << 38));
        System.out.println(Long.toBinaryString(1 << 39));
        System.out.println(Long.toBinaryString(1 << 40));
        System.out.println(Long.toBinaryString(1 << 41));
        System.out.println(Long.toBinaryString(1 << 42));
        System.out.println(Long.toBinaryString(1 << 43));
        System.out.println(Long.toBinaryString(1 << 44));
        System.out.println(Long.toBinaryString(1 << 45));
        System.out.println(Long.toBinaryString(1 << 46));
        System.out.println(Long.toBinaryString(1 << 47));
        System.out.println(Long.toBinaryString(1 << 48));
        System.out.println(Long.toBinaryString(1 << 49));
        System.out.println(Long.toBinaryString(1 << 50));
        System.out.println(Long.toBinaryString(1 << 51));
        System.out.println(Long.toBinaryString(1 << 52));
        System.out.println(Long.toBinaryString(1 << 53));
        System.out.println(Long.toBinaryString(1 << 54));
        System.out.println(Long.toBinaryString(1 << 55));
        System.out.println(Long.toBinaryString(1 << 56));
        System.out.println(Long.toBinaryString(1 << 57));
        System.out.println(Long.toBinaryString(1 << 58));
        System.out.println(Long.toBinaryString(1 << 59));
        System.out.println(Long.toBinaryString(1 << 60));
        System.out.println(Long.toBinaryString(1 << 61));
        System.out.println(Long.toBinaryString(1 << 62));
        System.out.println(Long.toBinaryString(1 << 63));

    }
*/
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1));
    }

}
