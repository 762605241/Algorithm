package leetcode;

//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
//
//
//
// 示例 1：
//
//
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
//
//
// 示例 2：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 104
//
// Related Topics 数组 动态规划
// 👍 1454 👎 0
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    /**
     * 19:50	info
     * 解答成功:
     * 执行耗时:2 ms,击败了76.73% 的Java用户
     * 内存消耗:51.4 MB,击败了22.04% 的Java用户
     *
     * 总结：对于求数组中某两位的差值最大或最小问题，可以不用遍历之后所有元素找到最大或最小值，
     * 可以一边遍历数组，一边记录数组的最大或最小值，如果之后遍历的元素不是最大值或最小值，再和记录的最大或最小值进行比较
     * 限制：求之后元素的最大或最小值，不需要再重头遍历。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }
    /**
     * 方法一超时
     * @param prices
     * @return
     */
    /*public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int temp = 0;
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            temp = getMax(prices, i + 1);
            if (temp > prices[i] && max < (temp - prices[i])) {
                max = temp - prices[i];
            }
        }
        return max;
    }

    private static int getMax(int[] prices, int index) {
        int max = prices[index];
        for (int i = index + 1; i < prices.length; i++) {
            if (max < prices[i]) {
                max = prices[i];
            }
        }
        return max;
    }*/
}
