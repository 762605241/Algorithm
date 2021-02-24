package leetcode;

//给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
//
//
// 示例 1:
//
// 输入: [7,1,5,3,6,4]
//输出: 7
//解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
//
//
// 示例 2:
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3:
//
// 输入: [7,6,4,3,1]
//输出: 0
//解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 3 * 10 ^ 4
// 0 <= prices[i] <= 10 ^ 4
//
// Related Topics 贪心算法 数组
// 👍 1103 👎 0
public class MaxProfit2 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    /**
     * 20:07	info
     * 解答成功:
     * 执行耗时:1 ms,击败了99.46% 的Java用户
     * 内存消耗:38.4 MB,击败了23.99% 的Java用户
     *
     * @param prices
     * @return
     */
    /*public static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int tempMax = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                tempMax += prices[i] - min;
                max = prices[i] - min;
                min = prices[i];
                max = 0;
            }
        }
        return tempMax;
    }*/

    /**
     * 20:13	info
     * 解答成功:
     * 执行耗时:1 ms,击败了99.46% 的Java用户
     * 内存消耗:38.5 MB,击败了9.69% 的Java用户
     *
     * 优化 只要比当前买的低就卖出，然后再以最低价格买入。
     * 相当于先从最低价买入（第一个最低价，或差值是正的）,然后在之后的最大元素出卖掉。
     * 再找到一个最小值买入，如此循环直至结束。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}