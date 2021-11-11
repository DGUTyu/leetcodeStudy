package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-11 20:24:40
 *
 * 70. 爬楼梯 难度：简单
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 */

public class ClimbingStairs {
    /**
     * 假如你现在是在第n个台阶，
     * 那你只能的n-1个台阶上通过1步爬上来的或者是在n-2个台阶上通过2步爬上来的。
     * 归纳便有：f(n)=f(n-1)+f(n-2) ，f为爬到第x个台阶的函数。
     * 观察可知，这类似斐波那契数列，可用递归，但如果不加缓存的话，时间复杂度为指数级别。
     * 这里无需记录每个n对应的值，我们只需不断的迭代计算，拿到某n项对应的数值即可。
     */

    public int climbStairs(int n){
        //方法1：不设置缓存，直接求结果
//        int first=1,second=2,third=0;
//        if(n<3 && n>0) return n;
//        for (int i = 3; i <= n ; i++) {
//            third=first+second;
//            first=second;
//            second=third;
//        }
//        return third;

        //方法2：利用数组(长度为n+1)缓存每项的结果，返回第n项数值
        //给定 n 是一个正整数，不做n的合法性判断
//        if(n<3 && n>0) return n;
//        int[] array=new int[n+1];
//        array[1]=1;
//        array[2]=2;
//        for (int i = 3; i <= n; i++) {
//            array[i]=array[i-1]+array[i-2];
//            //此种写法切记不要加上 array[i-2]=array[i-1];array[i-1]=array[i];
//        }
//        return array[n];

        //方法3：方法1的简洁版
        //给定 n 是一个正整数，不做n的判断
        //当前的全部走法用 a 表示，默认为1 （n=1)
        // b 用来记录a之前对应的全部走法，默认为1 （n=1）
        int a=1,b=1;
        //当 n 大于1时执行循环，循环次数为在n=1的基础上，计算第n项需要迭代的次数
        while(--n >= 1){
            a += b; //每次迭代的计算方法：当前全部走法 a=a+b
            b = a-b;//更新下一次计算前吧对应的全部走法
        }
        return a;
    }
}
