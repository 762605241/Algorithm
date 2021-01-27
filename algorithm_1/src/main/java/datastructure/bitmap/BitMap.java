package datastructure.bitmap;

import java.util.Arrays;
import java.util.HashSet;

public class BitMap {
    public static void main(String[] args) {
        /**
         * 使用位图保存一组整型数，并可以判断某个值是否出现，并可增加某个值
         */
        System.out.println("测试开始");
        int max = 63;
        BitMapData bitMapData = new BitMapData(max);
        HashSet<Integer> hashSet = new HashSet<Integer>();
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.33) {
                bitMapData.add(num);
                hashSet.add(num);
            } else if (decide < 0.66) {
                bitMapData.delete(num);
                hashSet.remove(num);
            } else {
                if (bitMapData.isContains(num) != hashSet.contains(num)) {
                    System.out.println("Oops!-1");
                    System.out.println(Arrays.toString(bitMapData.bitsMap));
                    System.out.println(Long.toBinaryString(bitMapData.bitsMap[0]));
                    System.out.println(hashSet);
                    System.out.println(num);
                    System.out.println(bitMapData.isContains(num));
                    System.out.println(hashSet.contains(num));
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMapData.isContains(num) != hashSet.contains(num)) {
                System.out.println("Oops!-2");
            }
        }
        System.out.println("测试结束");
    }

    public static class BitMapData {

        /**
         * 用来保存数据
         * 1、数组中一个数表示一个范围的整型值，0位置：0~63，1位置64~127,依次类推
         * 2、一个长整型的二进制的每一位，记录对应范围内的每一个值是否存在，
         * 0位置长整型，64位二进制分别对应0~63的每一个数字是否存在，存在为1，不存在为0
         */
        public long[] bitsMap;

        /**
         * 初始化集合
         * 通过传入值，计算集合的最大范围，即最大值应保存在哪个位置的二进制上。
         *
         * @param maxNum 传入要保存整型的最大值
         */
        public BitMapData(Integer maxNum) {
            bitsMap = new long[(maxNum + 64) >> 6];
        }


        /**
         * 添加一个整型数
         * 原理：让这个整型数的对应下标（对应某个长整型的某位二进制为1）设置为1
         *
         * @param num
         */
        public void add(int num) {
            /**
             * 根据出入值计算对应下标
             * num >> 6 ： 计算出传入值对应数组中哪个下标的。
             * 1 << (num & 63) ： 计算出在长整型的二进制中应在位置
             * 或运算，将对应位置设置成1，原先没有：0或1=1，原先有：1或1=1
             */
//            bitsMap[num >> 6] |= (1 << (num % 64));
            bitsMap[num >> 6] |= (1L << (num & 63));//实际上相当于num % (length - 1)取余数，(64 - 1)但&计算速度更快
        }

        /**
         * 删除一个整型数
         * 原理：让这个整型数的对应下标（对应某个长整型的某位二进制为1）设置为0
         *
         * @param num
         */
        public void delete(int num) {
            /**
             *^运算，相同为0，不同为1
             * 将原有1改变成0，即删除目标数。原有0保持不变。
             */
//            bitsMap[num >> 6] ^= (1L << (num & 63));
            bitsMap[num >> 6] &= ~(1L << (num & 63));
        }


        /**
         * 判断某个整型是否以保存
         *
         * @param num
         * @return
         */
        public boolean isContains(int num) {
            /**
             * 如果传入值，超过存储范围，即不能保存该数值，返回false
             */
//            if (num >> 6 >= bitsMap.length) {
//                return false;
//            }
            /**
             * 计算出对应位置，在对应位置与1，进行&运算，判断结果是否为0
             * &运算：同为1则为1，否则为0
             * 若结果为1，这证明该位置是1，已经保存了该整型值，若结果为0，则证明该位置是0，没有保存
             */
            if ((bitsMap[num >> 6] & (1L << (num & 63))) != 0) {
                return true;
            }
            return false;
        }
    }
}
