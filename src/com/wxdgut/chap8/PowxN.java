package com.wxdgut.chap8;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-15 16:37:17
 * <p>
 * 50. Pow(x, n) 难度：中等
 * 链接：https://leetcode-cn.com/problems/powx-n
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 * <p>
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 231^-1 即n是32位有效整数
 * -104 <= x^n <= 104
 */

public class PowxN {

//    public double myPow(double x, int n) {
//        //方法一：快速幂（分治思想） + 递归 时间复杂度：O(logn)
//        long N = n;
//        return N > 0 ? quickMulA(x, n) : 1.0 / quickMulA(x, -N);
//    }
//
//    private double quickMulA(double x, long N) {
//        if (N == 0) return 1.0;
//        double subRes = quickMulA(x, N / 2);
//        return N % 2 == 0 ? subRes * subRes : subRes * subRes * x;
//    }

    public double myPow(double x, int n) {
        //方法二：快速幂（分治思想） + 迭代 时间复杂度：O(logn)
        long N = n;
        return N > 0 ? quickMulB(x, N) : 1.0 / quickMulB(x, -N);
    }

    private double quickMulB(double x, long N) {
        double res = 1.0;
        while (N > 0) {
            if (N % 2 == 1) res *= x;
            x *= x;
            N /= 2;
        }
        return res;
    }

//    public double myPow(double x, int n) {
//        if (n == 0) return 1;
//        if (n < 0) return 1 / x * myPow(1 / x, -(n + 1));
//        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
//    }
}
