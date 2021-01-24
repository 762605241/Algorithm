package problems;

public class Random {
    public static void main(String[] args) {
        /**
         * 问题：给定[1~5]等概率随机函数，求等概率[1~7]随机函数
         * 通用形式： 给定[a~b]等概率随机函数，求等概率[c~d]随机函数
         *
         * 类似问题，Math.Random() 函数返回[0,1)之间一个数，且每个数的概率相同。那么[0,1) * n + m，得到的数也是等概率出现的，可以满足随机性。
         * 现在要求一个不是等概率范围内的随机数，要求做到等概率随机。
         */
        /**
         * 给定[a,b]范围内随机函数，求[c,d]范围内随机函数
         * 分析：
         *  1、两个随机函数并无关系，那么就需要制造一个中间变量，构造两者的关系。
         *  2、已知Math.random()随机函数，是取[0,1)范围内随机数，通过+,*操作可以扩大其范围，那么[c,d]可以表示成[0, d - c] + c的形式。
         *      那么问题就变成了如何求[0, d - c]范围内的随机函数。
         *  3、[0, d - c]范围内的随机函数，左边界永远是0，二进制也是从0开始的，且范围是固定的，即1位[0,1]，2位[0,3]，3位[0,7]。那么就可以求出
         *      大于d-c的最小值，即多少位二进制。
         *  4、已知有几位二进制，且二进制只有0和1，那么要构造随机函数，就变成了对这个二进制的每一位进行随机，即组合起来就构成[0, d-c]范围内的随机数了。
         *  5、首先根据给定[a,b]随机函数，构造0和1的随机函数，用来给二进制的每一位赋值。方法：将从a到b范围内取的随机数分成两组，一组即0，一组即1。
         *      对于奇数个数，若取到的随机数是中间值，那么重新取即可。
         *  6、每位二进制组合成一个随机数，randomNum = (f1() << n - 1) + (f1() << (n - 2)) + …… + (f1() << 2) + (f1() << 1) + (f1() << 0)
         *      取到一个随机数，通过<<操作，依次放置在指定位置。
         *  7、二进制对应的范围若大于[0, d - c]那么超出的部分不要，重新获取随机数直至在范围。
         *  8、[0, d - c] + c 即 [c,d] 范围随机数。
         */
        /**
         * 从1~5随机1~7随机
         * 思路：
         *  1、根据Math.Random()函数，实现1~5的随机函数f1()
         *  2、根据f1()构造函数f2()，f2实现0,1两数的随机。
         *  3、根据f2()函数，可以得到[0,2^n - 1],n为调用f2()函数次数。
         *      1、原理：根据二进制，每一位都是0和1，而f1()可以随机0和1两个数，且等概率。那么对于[0,7]对应二进制上，就是三位，
         *          在每一位上调用f1()函数，获取0或1，组合成一个三位的二进制，即[0,7]中的一个随机数，且等概率随机。
         *      2、组合n次调用f1()函数，构成一个[0,2^n - 1]的随机数。
         *          randomNum = (f1() << n - 1) + (f1() << (n - 2)) + …… + (f1() << 2) + (f1() << 1) + (f1() << 0)
         *          1、原理：二进制中从左至右，依次确认以为，第一次调用f1()函数，得到0或1，将值左移n，移动到最高位，此时最高确定。依次类推，确定二进制的n位。
         *  4、现有[0,7]范围的随机函数，那么求[0,6]的随机函数，只需要让f3()函数随机到7时，重新随机即可，即只取[0,6]的范围内的随机数。
         */
        /**
         * 总结：给定[a,b]随机函数，求[c,d]随机函数
         *  1、对于随机函数，Math.random()返回[0,1)范围内的随机数，且每一个随机数概率相等。
         *  2、求取一个范围内的随机数，可以根据Math.random()进行基础修改，扩大其范围。
         *  3、对于随机函数，在取随机数时，可以根据if来去掉某些值，并重新取值，来缩小范围，且保持剩余范围内随机数等概率。即将取出的随机数概率分摊到其余随机数上。
         *  4、步骤：
         *      1、根据给出范围内的随机函数，构造0和1的随机函数（从给定随机函数中去值，一半范围即0，一半范围即1。奇数的中值通过if去除）。
         *      2、再根据0和1的随机函数，构造[0, d-c]的随机函数，[0, d-c] + c 即是[c,d]随机函数
         *          1、构造[0, d-c]随机数，判断其需要几个二进制位满足范围，需要几位就调用几次01随机函数，并通过<<和+运算构造随机数。若二进制范围大于
         *              [0, d-c]范围，那么超出部分通过if去除，并重新取随机数。即构造完成[0, d - c]范围内随机数。
         */
        // 去[1,7]上随机数
        System.out.println(f4() + 1);
        /**
         * 问题：给定01的不等概率函数，但是固定概率，0的概率是p，1的概率是1-p。求01的等概率函数
         * 分析：
         *  1、要得到等概率随机函数，如何确定等概率。
         *  2、给定随机数有一下情况：0的概率是p，1的概率是1-p。可以发现01的概率和10的概率是相等的。
         *  3、通过两次的随机函数调用可以等到：00,01,10,11这四组情况，其中00和11概率不相等，舍弃。而10,01概率相同，即满足01的等概率随机函数。
         *
         *  通用问题：n个不等概率的随机函数，等到一个n位的等概率随机函数，即调用n次不等概率随机函数，找到所有等概率的值，对应返回即可。
         */
    }

    /**
     * 返回[1,5]的随机数，且等概率
     * @return
     */
    public static int f1() {
        return (int) (Math.random() * 5 + 1);
    }

    /**
     * 根据f1()函数，构造0,1随机函数，且等概率
     * @return
     */
    public static int f2() {
        int i = 0;
        do {
            i = f1();
        } while (i == 3);
        return i < 3 ? 0 : 1;
    }

    /**
     * 获取[0,7]范围内随机数，且等概率
     * @return
     */
    public static int f3() {
        return (f2() << 2) + (f2() << 1) + (f2() << 0);
    }

    /**
     * 获取[0,6]范围内的随机函数，且等概率
     * @return
     */
    public static int f4() {
        int i = 0;
        do {
            i = f3();
        } while (i == 7);
        return i;
    }
}
