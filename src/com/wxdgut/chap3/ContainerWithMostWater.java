package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-10 21:56:42
 *
 * 11. 盛最多水的容器 难度：中等
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 *
 */

public class ContainerWithMostWater {
    /**
     * 双指针法
     * 首先使用左右下标 i j 标志边界，
     * 由于此时宽度是最大的，要使得两者之间的面积变大，
     * 只能移动较短的那个柱子才有可能实现，
     * 记录此时较短的柱子，并计算此时的面积放入考虑范围。
     * 以此类推，每次移动最短的那个柱子，并记录移动前的面积。
     * 在这些面积中选最大的即为结果，为此，
     * 我们可以设置一个max变量，在每次计算好面积时就与它作比较并更新max变量
     *
     * （双指针法的正确性可以参考 https://leetcode-cn.com/problems/container-with-most-water/solution/on-shuang-zhi-zhen-jie-fa-li-jie-zheng-que-xing-tu/）
     *
     * 使用左右下标 i j 向中间收敛，即左右夹逼
     *
     */

    public int maxArea(int[] height) {
        int max=0;
        for (int i = 0,j = height.length-1; i < j; ) {
            //记录两边柱子的最小值，然后移动较短的柱子的下标
            int minHeight=height[i] < height[j] ? height[i++]:height[j--];
            //计算移动前（故j-i后要加上1）可能的最大面积并及时更新最大面积
            max=Math.max(max,(j-i+1)*minHeight);
        }
        return max;
    }
}
