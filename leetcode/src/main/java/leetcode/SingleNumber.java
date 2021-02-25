package leetcode;
//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明：
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
//
// 示例 1:
//
// 输入: [2,2,1]
//输出: 1
//
//
// 示例 2:
//
// 输入: [4,1,2,1,2]
//输出: 4
// Related Topics 位运算 哈希表
// 👍 1710 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleNumber {
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(singleNumber(nums));
    }

    /**
     * 方法一：将数组排序，依次遍历数组如果当前元素既不等于前一个元素也不等于后一个元素即返回结果
     * 20:51	info
     * 解答成功:
     * 执行耗时:7 ms,击败了31.72% 的Java用户
     * 内存消耗:38.8 MB,击败了34.20% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        if (nums[0] != nums[1]) {
            return nums[0];
        }
        if (nums[nums.length - 2] != nums[nums.length - 1]) {
            return nums[nums.length - 1];
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 方法二
     * 通过依次异或每个元素，得到最后的值，即出现过依次的值，即结果
     * 一个数异或两个相同的数，结果是其本身
     *
     * 20:56	info
     * 解答成功:
     * 执行耗时:1 ms,击败了99.99% 的Java用户
     * 内存消耗:38.5 MB,击败了87.19% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }
}
