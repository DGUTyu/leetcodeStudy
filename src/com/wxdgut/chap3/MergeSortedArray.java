package com.wxdgut.chap3;

import java.util.Arrays;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-16 20:22:19
 * <p>
 * 88. 合并两个有序数组 难度：简单
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * <p>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，
 * 另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
 * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，
 * 后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 */

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //方法1：直接合并 + Arrays.sort排序
//        for (int i = 0; i < n; i++) {
//            nums1[i+m]=nums2[i];
//        }
//        Arrays.sort(nums1);

        //方法2:逆向双指针
//        int i = m + n - 1;
//        m--;
//        n--;
//        while (m >= 0 && n >= 0) {
//            //两个值大于或等于时，先取nums1较长的数值来释放nums1空间
//            nums1[i--] = (nums1[m] >= nums2[n]) ? nums1[m--] : nums2[n--];
//        }
//        while (n >= 0) {
//            nums1[i--] = nums2[n--];
//        }

        //方法3：方法2的简洁版
        int i = m + n;
        while (m > 0 && n > 0) {
            nums1[--i] = (nums2[n - 1] > nums1[m - 1]) ? nums2[--n] : nums1[--m];
        }
        while (n > 0) {
            nums1[--i] = nums2[--n];
        }
    }
}
