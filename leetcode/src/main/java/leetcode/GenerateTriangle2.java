package leetcode;

import java.util.ArrayList;
import java.util.List;
//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
//
//
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。
//
// 示例:
//
// 输入: 3
//输出: [1,3,3,1]
//
//
// 进阶：
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？
// Related Topics 数组
// 👍 264 👎 0

public class GenerateTriangle2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> generate = generate2(3);
        for (int i : generate) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    /**
     * 19:58	info
     * 			解答成功:
     * 			执行耗时:1 ms,击败了75.68% 的Java用户
     * 			内存消耗:36.4 MB,击败了16.98% 的Java用户
     * @param rowIndex
     * @return
     */
    public static List<Integer> generate2(int rowIndex) {
        if (rowIndex == 0) {
            List<Integer> list = new ArrayList<>(1);
            list.add(1);
            return list;
        }
        List<Integer> preRow = generate2(rowIndex - 1);
        List<Integer> list = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i < rowIndex + 1; i++) {
            if (i == 0) {
                list.add(preRow.get(i));
            } else if (i == preRow.size()) {
                list.add(preRow.get(i - 1));
            } else {
                list.add(preRow.get(i - 1) + preRow.get(i));
            }
        }
        return list;
    }

    /**
     *19:48	info
     * 			解答成功:
     * 			执行耗时:4 ms,击败了30.33% 的Java用户
     * 			内存消耗:36.2 MB,击败了54.77% 的Java用户
     * @param rowIndex
     * @return
     */

    public static List<Integer> generate1(int rowIndex) {
        if (rowIndex == 0) {
            return null;
        }
        rowIndex++;
        List<List<Integer>> lists = new ArrayList<>(rowIndex);
        List<Integer> list = new ArrayList<>(rowIndex);
        int num = 0;
        list.add(1);
        lists.add(list);
        for (int i = 1; i < rowIndex; i++) {
            list = new ArrayList<>(rowIndex);
            for (int j = 0; j < rowIndex && j <= lists.get(i - 1).size(); j++) {
                if (j != 0) {
                    num += lists.get(i - 1).get(j - 1);
                }
                if (j < lists.get(i - 1).size()) {
                    num += lists.get(i - 1).get(j);
                }
                list.add(num);
                num = 0;
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }

}
