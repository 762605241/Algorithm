package problems;

import java.util.ArrayList;

/**
 * 使用位运算实现 加减乘除 取模
 * 原理：加法原则：对应位置进行加和，若果有进位，则加到到高位中。
 *      那么使用位运算代替加法，要解决两个问题：
 *          1、如何计算进位
 *              二进制中出现进位的形式只有1+1，可以使用&运算来表示出哪一位需要进位。a&b的二进制结果对应哪一位相加值后需要进位。
 *              再将进位加到更高为的对位和中，直至所有进位结果等于0，没有进位，此时的和就是a+b的结果。
 *          2、如何计算对位的加和
 *              在问题1，已经解决了哪一位需要进位。如果出现进位，那么也就表示该位上加和后的结果是0，若不出现进位，则是1或者0。
 *              可以使用^运算来计算对位的加和，即^运算就是无进位的相加。相同就是0，不同就是1，即0+0=0，1+1=0,0+1=1,1+0=0。符合加法规则。
 * 加法：a + b
 * 减法：可以将减法转换成加法计算。a - b = a + (-b)
 * 乘法：可以将乘法转换成一个数的累加。a * b = b个a相加
 * 除法：可以将除法转换成多次减法，再将减法转换成加法。a / b = a减去多个b，知道差小于b
 */
public class Operation {

    /**
     * 加法
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        // 表示两数相加后的和，初始状态是a
        int sum = a;
        // 表示进位，初始状态是b，也就是把b当做加和后的进位
        int carry = b;
        // 循环条件：只要进位不为0，则继续加
        /**
         * 步骤：
         *  1、计算两数二进制对位加和
         *  2、计算两数二进制对位是否需要进位
         *  3、将进位信息左移一位，计算到下一次加和中
         *  4、替换操作数信息，将上一次加和信息赋值给加数a，将上一次进位信息赋值给加数b。将a+b转换成 ab和(不算进位) + ab进位
         *  5、当进位信息不等于0是，继续计算加和，直至进位信息等于0，加和结束，sum返回结果
         */
        while (carry != 0) {
            // 计算两数对应二进制位加和
            sum = a ^ b;
            // 记录每一位的进位信息，<<将每一位的进位信息提高一位，加入到下次对位计算中
            carry = (a & b) << 1;
            // a 重新赋值加和结果
            a = sum;
            // 记录进位
            b = carry;
        }
        return sum;
    }

    /**
     * 减法
     * @param a
     * @param b
     * @return
     */
    public static int sub(int a, int b) {
        // 被减数取相反数，使用位运算取反~b+1即b的相反数
        b = add(~b, 1);
        // 计算a + (-b)
        return add(a, b);
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static int mul(int a, int b) {
        /**
         * 方法一：将乘法转换成累加，a累加b-1次，即a*b的结果
         */
//        int sum = a;
//        do {
//            // 累加a
//            sum = add(sum, a);
//        } while (--b != 1);
//        return sum;
        // 优化
        /**
         * 方法二：使用类似10进制乘法方法，b的每一位乘以a的每一位，加和即a*b的结果。
         * 当b的二进制位是1时，结果加上当前a的值。当b的二进制位是0时，可以忽略，不用进行加和，因为a与0想成即是0。
         * b二进制第0位分别于a的二进制每位相乘，b的二进制是1，则结果加上a，
         * b二进制第1位分别于a的二进制每位想成，b的二进制是1，则结果加上(a<<1)，类似于b的十位数成以a时，要成10一样，需要向前进一位，再相加。
         * 依次想成，知道b=0。
         *
         * 比方法1效率大大提高
         */
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        // 循环减去a，知道差小于b
        /**
         * 这种方法只支持：a，b都是正数
         */
//        if (a < b) {
//            return 0;
//        }
//        int i = 0;
//        do {
//            a = sub(a, b);
//            i++;
//        } while (a >= b);
//        return i;
        /**
         * 优化
         * a / b = c可以理解为 b * c = a。
         * 乘法中b * c 是c的二进制中，每一个为1的位上，与b的二进制每一位相乘的累加和。
         * 只有c的二进制位上是1，才会去乘以b。
         * 所以问题变成如何确定c中哪一位是1。
         *
         * 将b左移，到刚好小于a的位置，下一次移动b就大于a，然后移动的位置的二进制位是1，即结果的对应位置也是1。
         * 将b赋值b-左移之后的b，在重新计算下一个位置，直至b=0;
         *
         * 具体实现使用a进行右移，来计算结果1位置。
         * 原因：左移操作可能触发符号位的计算，使b左移变成负数，永远小于a。让a进行右移，去匹配b。不用担心符号位，从正数变成负数了
         *
         */
        if (b == 0) {
            System.out.println("/ by zero");
            throw new ArithmeticException("/ by zero");
        }

        if (a == 0 || a < b) {
            return 0;
        }
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            // ab都是系统最小值，结果为1
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            // a不是系统最小值，b是系统最小值，不论a是多少，那么结果都是0。
            // 去掉符号为，b比系统最大值还大1，无论a是多少，都不可能大于b，所以结果是0。
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                // 当a是系统最小值，如果除以-1，那么结果就是a的相反数，但是系统没有这个数。所以规定结果是系统最大值。
                return Integer.MAX_VALUE;
            } else {
                /**
                 * 当a是系统最小值，b不是系统最小值，且也不是-1。
                 * 最小值是负数，因为系统没有最大值与之对应，即没有其相反数与之对应。
                 *  先对a进行加一，让其相反数可以在系统中找到，即系统最大值。此时就可以使用自定义的除法进行计算。
                 *  因为对a进行了减一操作，所以结果会小于等于正确结果。要计算相差多少。
                 *  使用a减去结果与b的乘积，得到差值，即剩余没有用来做被除数的部分
                 *  再用剩余部分除以b得到，再加上原有结果，即a除以b的最终结果
                 */
                int result = divNum(add(a, 1), b);
                return add(result, divNum(sub(a, mul(result, b)), b));
            }
        } else {
            // 当ab都不是系统最小值，通过除法方法计算其结果
            return divNum(a, b);
        }
    }

    private static int divNum(int a, int b) {
        // 将a，b都转换成正数进行计算
        int x = a < 0 ? negNum(a) : a;
        int y = b < 0 ? negNum(b) : b;

        // 记录结果
        int result = 0;
        /**
         * 循环尝试
         * 最大差距位数：去掉符号位剩余31位表示数值
         *  a的二进制0100……
         *  b的二进制0……1
         *  a的最高位是第30位，b只有第0位是1,其余位是0。此时ab距离最大，达到30。
         *  所以每次从30开始移动
         */
        for (int i = 30; i >= 0; i = sub(i, 1)) {
            // 当移动至刚好大于y时，进行计算，记录对应位置为1，即结果对应位置为1
            if ((x >> i) >= y) {
                result |= (1 << i);
                x = (sub(x, y << i));
            }
        }
        // 将符号加到结果，相同符号位则返回正，不同符号位则返回负
        return a < 0 ^ b < 0 ? negNum(result) : result;
    }

    /**
     * 通过位运算取num的相反数
     * num 的相反数 = ~num + 1
     * @param num
     * @return
     */
    public static int negNum(int num) {
        return add(~num, 1);
    }

    /**
     * 取模（取余）
     * 适用范围 a % b,b=2^n。b要是2的n次幂。才可以使用。
     * 原理：a % b时，即计算a小于n*b的部分还有多少。例如45 % 32，n即为1,计算n小于1 * 32 还有多少，还有13。
     *  如何确定还剩余多少，可以将两个数的二进制列出
     *  45：101101
     *  32：100000
     *  在二进制中，b的整数倍在a中，就是第5位及更高位所表示的数。而其余位数则表示小于b的值，也就是a对b余数。那么问题就变成如何返回b的余数的二进制。
     *  b - 1 的二进制表示为 11111，使用a & (b - 1) 就可以保留下，小于b的二进制位，而大于b的部分则被&0运算成0。返回的结果就是a % b的结果
     * @param a
     * @param b
     * @return
     */
    public static int mod(int a, int b) {
        return a & (mul(b, 1));
    }

    public static void main(String[] args) {

        /**
         * 验证加法实现是否正确
         * 循环测试
         * 随机生成两个数进行加和
         */
        int testTimes = 100000;
        int a = 0;
        int b = 0;
        int max = 10;
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        ArrayList<Integer> baseResult = new ArrayList<>(100000);
        ArrayList<Integer> bitResult = new ArrayList<>(100000);
        ArrayList<Integer> temp = null;
        System.out.println("测试开始");
        System.out.println("生成测试数据");
        for (int i = 0; i < testTimes; i++) {
            temp = new ArrayList<>();
            a = (int) (Math.random() * (max + 1));
            b = (int) (Math.random() * (max + 1));
            temp.add(a);
            temp.add(b);
            arrayLists.add(temp);
        }
        System.out.println("测试数据生成完成");
        System.out.println("位运算加法测试开始");
        long l = System.currentTimeMillis();
        for (ArrayList<Integer> t : arrayLists) {
            bitResult.add(add(t.get(0), t.get(1)));
        }
        long l1 = System.currentTimeMillis();
        System.out.println("位运算加法结束，耗时\t" + (l1 - l));

        System.out.println("基础加法测试开始");
        l = System.currentTimeMillis();
        for (ArrayList<Integer> t : arrayLists) {
            baseResult.add(t.get(0) + t.get(1));
        }
        l1 = System.currentTimeMillis();
        System.out.println("基础加法结束，耗时\t" + (l1 - l));
        for (int i = 0; i < testTimes; i++) {
            if (baseResult.get(i).intValue() != bitResult.get(i).intValue()) {
                System.out.println("测试失败");
                System.out.println("操作数\t" + arrayLists.get(i).toString());
                System.out.println("位运算加法结果\t" + bitResult.get(i).toString());
                System.out.println("基础加法结果\t" + baseResult.get(i).toString());
            }
        }
        System.out.println("测试结束");
        /**
         * 输出结果：测试成功，实现正确
         * 测试开始
         * 生成测试数据
         * 测试数据生成完成
         * 位运算加法测试开始
         * 位运算加法结束，耗时	12
         * 基础加法测试开始
         * 基础加法结束，耗时	9
         * 测试结束
         *
         * Process finished with exit code 0
         */
        /**
         * 问题：为什么位运算不如加减乘除快？
         * 因为在Java中加减乘除是虚拟机底层实现，包含汇编的语言进行底层的实现。而使用位运算手写实现的加减乘除逻辑，要经过翻译之后到达虚拟机执行，在翻译过程中比较耗时，
         * 而不如直接使用虚拟机实现的加减乘除效率高。并不代表位运算效率不如加减乘除。实际上单独使用位运算对数值进行操作效率要高与加减乘除。
         */


        System.out.println();
        System.out.println("测试减法开始");
        for (int i = 0; i < testTimes; i++) {
            a = (int) (Math.random() * (max + 1));
            b = (int) (Math.random() * (max + 1));
            if (sub(a, b) != (a - b)) {
                System.out.println("操作数a:\t" + a + "\t操作数b:\t" + b);
                System.out.println("位运算减法结果:\t" + sub(a, b));
                System.out.println("基础减法结果:\t" + (a - b));
                System.out.println();
            }
        }
        System.out.println("测试减法结束");
        /**
         * 输出结果：测试成功，实现正确
         *
         * 测试减法开始
         * 测试减法结束
         *
         * Process finished with exit code 0
         */

        int bitResultValue = 0;
        int baseResultValue = 0;

        System.out.println();
        System.out.println("测试乘法开始");
        for (int i = 0; i < testTimes; i++) {
            a = (int) (Math.random() * (max + 1));
            b = (int) (Math.random() * (max + 1));
            bitResultValue = mul(a, b);
            baseResultValue = (a * b);
            if (bitResultValue != baseResultValue) {
                System.out.println("操作数a:\t" + a + "\t操作数b:\t" + b);
                System.out.println("位运算乘法结果:\t" + bitResultValue);
                System.out.println("基础乘法结果:\t" + baseResultValue);
                System.out.println();
            }
        }
        System.out.println("测试乘法结束");
        /**
         * 输出结果：测试成功，实现正确
         *
         * 测试乘法开始
         * 测试乘法结束
         *
         * Process finished with exit code 0
         */

        System.out.println();
        System.out.println("测试除法开始");
        for (int i = 0; i < testTimes; i++) {
            a = (int) (Math.random() * (max + 1));
            b = (int) (Math.random() * (max + 1));
            if (b == 0) {
                continue;
            }
            bitResultValue = div(a, b);
            baseResultValue = (a / b);
            if (bitResultValue != baseResultValue) {
                System.out.println("操作数a:\t" + a + "\t操作数b:\t" + b);
                System.out.println("位运算除法结果:\t" + bitResultValue);
                System.out.println("基础除法结果:\t" + baseResultValue);
                System.out.println();
            }
        }
        System.out.println("测试除法结束");

        /**
         * 输出结果：测试成功，实现正确
         *
         * 测试除法开始
         * 测试除法结束
         *
         * Process finished with exit code 0
         */
    }

}
