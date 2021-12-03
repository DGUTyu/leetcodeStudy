package com.wxdgut.chap7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-03 09:55:46
 * <p>
 * [极客时间-每日一课]如何优雅地计算斐波那契数列？
 * 视频链接：https://time.geekbang.org/dailylesson/detail/100028406
 * 可参考文章链接：https://www.cnblogs.com/benzhai/p/12410707.html
 * 可参考矩阵快速幂求斐波那契数列（总结）链接：
 * https://blog.csdn.net/weixin_34221276/article/details/86129443?spm=1001.2014.3001.5502
 */

public class FibonacciSequence {

    private Map<Integer, Integer> map;

    public FibonacciSequence() {
        map = new HashMap<>();
    }

    public int FibnacciA(int n) {
        /**
         * Lev1. 递归
         * 时间复杂度：指数级
         * n = 0...19
         * 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,
         * n = 1...20
         * 1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,
         * 程序运行时间： 3629400ns  程序运行时间：4ms
         */
        if (n < 2) return n;
        return FibnacciA(n - 1) + FibnacciA(n - 2);
    }

    public int FibnacciB(int n) {
        /**
         * Lev2. 带备忘录的递归 使用HashMap
         * 时间复杂度O(n), 空间复杂度O(n)
         * 求前20项
         * 程序运行时间： 925700ns  程序运行时间：1ms
         */
        if (map.containsKey(n)) return map.get(n);
        if (n < 2) return n;
        int res = FibnacciB(n - 1) + FibnacciB(n - 2);
        map.put(n, res);
        return res;
    }

    public int FibnacciC(int n) {
        /**
         * Lev3. DP (动态规划)
         * 时间复杂度O(n), 空间复杂度O(n)
         * 当发现存在大量重复子问题的时候，通常我们会想到DP.
         * DP是一种自下向上的解决问题的思路，先解出f(2), 那f(3)就得解，
         * 接着f(4)也就得解，直到f(n)，而递归是自上而下。
         * 状态转移方程 DP[n] = DP[n-1] + DP[n-2]
         *
         * 求前20项
         * 程序运行时间： 626500ns  程序运行时间：0ms
         */
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) { //注意等号
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int FibnacciD(int n) {
        /**
         * Lev3. DP (动态规划) 把DP数组去掉
         * 时间复杂度O(n), 空间复杂度O(1)
         * 求前20项
         * 程序运行时间： 644800ns 程序运行时间：0ms
         *
         * 扩展：
         * 数列初始值后移1位
         * ① n的范围从0开始，原前10项：0,1,1,2,3,5,8,13,21,34,
         * 后移1位后：1,1,2,3,5,8,13,21,34,55,
         * 代码应该修改为：int a = 1, b = 1; while (--n >= 1)
         *
         * 后移2位后：1,2,3,5,8,13,21,34,55,89,
         * 代码应该修改为：int a = 1, b = 1; while (--n >= 0)
         *
         * ② n的范围从1开始，原前10项：1,1,2,3,5,8,13,21,34,55,
         * 后移1位后：1,2,3,5,8,13,21,34,55,89,
         * 代码应该修改为：int a = 1, b = 1; while (--n >= 1)
         *
         * 后移2位后：2,3,5,8,13,21,34,55,89,144,
         * 代码应该修改为：int a = 1, b = 1; while (--n >= 0)
         *
         * 小结：
         * 原始斐波那契数列用int a = 0, b = 1; while (--n >= 0)
         * 爬楼梯则使用int a = 1, b = 1; while (--n >= 1)
         *
         * 下面是分析：
         * n = 0...19
         * 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,
         * n = 1...20
         * 1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,
         * 函数FibnacciA ~ FibnacciD的输入输出都是一样的。
         * 故在求斐波那契数列的前n项值，先确定n的范围是从0还是1开始
         * 原始斐波那契数列用int a = 0, b = 1; while (--n >= 0)，
         * 这样会得到以下结果：
         * n的范围从0开始的前n个数值为：0,1,1,2,3,5,8,13,21,34,
         * n的范围从1开始的前n个数值为：1,1,2,3,5,8,13,21,34,55,
         * 如果想去掉斐波那契数列的第一个数，
         * 则FibnacciD中使用int a = 1, b = 1; while (--n >= 1)
         *
         * 应用例子：在爬楼梯中，int a = 0, b = 1; while (--n >= 0)
         * 由于n从1开始，故序列为1,1,2,3,5,8,13,21,34,55,
         * 但是根据题目，符合题意的是1,2,3,5,8,13,21,34,55, 第一个1我们是不需要的，
         * 故使用 int a = 1, b = 1; while (--n >= 1)
         */
        int a = 0, b = 1;
        while (--n >= 0) {
            a += b;
            b = a - b;
        }
        return a;
    }

    public int FibnacciE(int n) {
        /**
         * Lev4. 通项公式
         * 代码中使用的 pow 函数的时空复杂度与 CPU 支持的指令集相关，这里不深入分析。
         * 求前20项
         * 程序运行时间： 705000ns 到 1494400ns  程序运行时间：0-1ms
         */
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibn / sqrt5);
    }

    public int FibnacciF(int n) {
        /**
         * Lev4. 通项公式 自定义O(log(N))的幂运算优化系统幂函数
         * 求前20项
         * 程序运行时间： 707800ns 到 1621900ns  程序运行时间：0-1ms
         */
        double sqrt5 = Math.sqrt(5);
        double fibn = myPow((1 + sqrt5) / 2, n) - myPow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibn / sqrt5);
    }

    //Lev4
    public double myPow(double x, double n) {
        double res = 1;
        double value = x;
        while (0 != n) {
            if (n % 2 == 1) {
                res *= value;
                n -= 1;
            }
            value = value * value;
            n /= 2;
        }
        return res;
    }

    public int FibnacciG(int n) {
        /**
         * Lev5. 借助线性代数的矩阵运算
         * 时间复杂度：同快速幂，O(logn) 空间复杂度：O(1)O(1)。
         *
         * 求前20项
         * 程序运行时间： 970700ns 程序运行时间：1ms
         *
         * 扩展：
         * ①return res[0][1];
         * n的范围从0开始：
         * 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,
         * n的范围从1开始：
         * 1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,
         *
         * ②return res[0][0];
         * n的范围从0开始：
         * 1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,
         * n的范围从1开始：
         * 1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,
         *
         * 小结：原始斐波那契数列用return res[0][1]; 爬楼梯则用return res[0][0]
         *
         * 下面是分析：
         * 应用例子：在爬楼梯中，题目说明n从1开始，故使用return res[0][0];
         */
        int[][] q = {{1, 1}, {1, 0}}; //底数
        int[][] res = pow(q, n);
        return res[0][1]; //return res[0][0];
    }

    //Lev5
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}}; //单位矩阵
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1; //右移一位 相当于 n/=2
            a = multiply(a, a);
        }
        return ret;
    }

    //Lev5
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        FibonacciSequence fb = new FibonacciSequence();
        fb.map.clear();
        int n = 20; //n 为正整数
        long startTime = System.nanoTime();   //获取开始时间（纳秒）
        long startTimeH = System.currentTimeMillis(); //获取开始时间（毫秒）
//        System.out.println("n的范围从0开始：");
//        for (int i = 0; i < n; i++) {
//            System.out.print(fb.FibnacciG(i) + ",");
//        }
        System.out.println("\n n的范围从1开始：");
        for (int i = 1; i <= n; i++) {
            System.out.print(fb.FibnacciG(i) + ",");
        }
        System.out.println("\n n = " + n);
        long endTime = System.nanoTime(); //获取结束时间（纳秒）
        long endTimeH = System.currentTimeMillis(); //获取结束时间（毫秒）
        System.out.println("程序运行时间： " + (endTime - startTime) + "ns");
        System.out.println("程序运行时间：" + (endTimeH - startTimeH) + "ms");
    }
}
