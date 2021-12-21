package com.wxdgut.chap8;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-15 16:37:17
 * <p>
 * 50. Pow(res, n) 难度：中等
 * 链接：https://leetcode-cn.com/problems/powx-n
 * <p>
 * 实现 pow(res, n) ，即计算 res 的 n 次幂函数（即，res^n）。
 * <p>
 * 提示：
 * -100.0 < res < 100.0
 * -2^31 <= n <= 231^-1 即n是32位有效整数
 * -104 <= res^n <= 104
 */

public class PowxN {

//    public double myPow(double res, int n) {
//        //方法一：快速幂（分治思想） + 递归 时间复杂度：O(logn)
//        long N = n;
//        return N > 0 ? quickMulA(res, n) : 1.0 / quickMulA(res, -N);
//    }
//
//    private double quickMulA(double res, long N) {
//        if (N == 0) return 1.0;
//        double subRes = quickMulA(res, N / 2);
//        return N % 2 == 0 ? subRes * subRes : subRes * subRes * res;
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

//    public double myPow(double res, int n) {
//        if (n == 0) return 1;
//        if (n < 0) return 1 / res * myPow(1 / res, -(n + 1));
//        return (n % 2 == 0) ? myPow(res * res, n / 2) : res * myPow(res * res, n / 2);
//    }
}
