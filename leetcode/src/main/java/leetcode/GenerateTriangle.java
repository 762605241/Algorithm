package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
//
//
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。
//
// 示例:
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
// Related Topics 数组
// 👍 454 👎 0

public class GenerateTriangle {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> generate = generate2(5);
        for (List<Integer> list : generate) {
            for (int i : list) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 19:40	info
     * 			解答成功:
     * 			执行耗时:1 ms,击败了46.51% 的Java用户
     * 			内存消耗:36 MB,击败了98.95% 的Java用户
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate2(int numRows) {
        {
            if (numRows == 0) {
                return null;
            }
            List<List<Integer>> lists = new ArrayList<>(numRows);
            List<Integer> list = new ArrayList<>(numRows);
            int num = 0;
            list.add(1);
            lists.add(list);
            for (int i = 1; i < numRows; i++) {
                list = new ArrayList<>(numRows);
                for (int j = 0; j < numRows && j <= lists.get(i - 1).size(); j++) {
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
            return lists;
        }
    }

    /**
     *      19:21	info
     *      解答成功:
     *      执行耗时:0 ms,击败了100.00% 的Java用户
     *      内存消耗:36.7 MB,击败了5.02% 的Java用户
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return null;
        }
        List<List<Integer>> lists = new ArrayList<>(numRows);
        int[][] array = new int[numRows][numRows * 2 - 1];
        int m = (2 * numRows - 1) / 2;
        array[0][m] = 1;
        for (int i = 1; i < numRows; i++) {
            for (int j = m; j < 2 * numRows - 1; j++) {
                array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
            }
        }
        List<Integer> list = null;
        for (int i = 0; i < numRows; i++) {
            list = new ArrayList<>(numRows);
            for (int j = m; j < 2 * numRows - 1; j++) {
                if (array[i][j] != 0) {
                    list.add(array[i][j]);
                }
            }
            lists.add(list);
        }
        return lists;
    }
}
