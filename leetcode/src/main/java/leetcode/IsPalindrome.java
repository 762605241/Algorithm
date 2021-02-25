package leetcode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。
//
// 示例 1:
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
//
//
// 示例 2:
//
// 输入: "race a car"
//输出: false
//
// Related Topics 双指针 字符串
// 👍 335 👎 0
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal -- Panama"));
        String pattern = "[\\s|!|@|#|$|%|^|&|*|(|)|_|+|=|{|}|:|\"|<|>|?|\\[|\\]|;|'|,|.|/|" +
                "！|@|#|￥|%|…|…|&|*|（|）|—|+|：|“|《|》|？|=|【|】|；|‘|，|。|、|’|\\-|`|~|·]*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher("A man, a plan, a canal -- Panama");
        System.out.println(m.replaceAll(""));
    }


    /**
     * 方法一：定义两个指针，分别指向字符串第一个和最后一个字符，依次向中间遍历，
     * 如果是符合或空格，则跳过，如果是字母或数字则两者对比，判断是否相同
     *
     * 20:33	info
     * 解答成功:
     * 执行耗时:4 ms,击败了64.03% 的Java用户
     * 内存消耗:38.8 MB,击败了19.13% 的Java用户
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'
                    || s.charAt(i) >= '0' && s.charAt(i) <= '9'
                    || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                    || s.charAt(i) == 32) {
                i++;
                continue;
            }
            if (!(s.charAt(j) >= 'a' && s.charAt(j) <= 'z'
                    || s.charAt(j) >= '0' && s.charAt(j) <= '9'
                    || s.charAt(j) >= 'A' && s.charAt(j) <= 'Z')
                    || s.charAt(j) == 32) {
                j--;
                continue;
            }
            // 注意0P 之间正好差32，所以不能使用+32判断两字符是否相同
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 方法二
     * 使用正则表达式替换所有符号和空格
     * 替换后字符与其反转后忽略大小写比较
     *
     * 20:31	info
     * 解答成功:
     * 执行耗时:80 ms,击败了5.01% 的Java用户
     * 内存消耗:40.7 MB,击败了5.05% 的Java用户
     *
     * 正则表达式太慢了
     * @param s
     * @return
     */
    /*public static boolean isPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        String pattern = "[\\s|!|@|#|$|%|^|&|*|(|)|_|+|=|{|}|:|\"|<|>|?|\\[|\\]|;|'|,|.|/|" +
                "！|@|#|￥|%|…|…|&|*|（|）|—|+|：|“|《|》|？|=|【|】|；|‘|，|。|、|’|\\-|`|~|·]*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(s);
        s = m.replaceAll("").toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }*/
}
