package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-15 22:07:28
 *
 * 189. 轮转数组 难度：中等
 * 链接：https://leetcode-cn.com/problems/rotate-array
 *
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 进阶：你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */

public class RotateArray {
    public void rotate(int[] nums,int k){
        //方法1：使用额外的数组
        //时间复杂度： O(n) 空间复杂度： O(n)
//        int n=nums.length;
//        int step=k % n;
//        int[] newArr=new int[n];
//        for (int i = 0; i < n; i++) {
//            newArr[(i+k) % n ]=nums[i];
//        }
//        //arraycopy(Object src,int srcPos,Object dest, int destPos,int length);
//        System.arraycopy(newArr,0,nums,0,n);

        //方法2:方法1的改版
        //时间复杂度： O(n) 空间复杂度：O(k % nums.length)即 O(n)
//        int n=nums.length;
//        if(n<=1) return;
//        int step=k % n;
//        //记录nums中后step位的值 如 1234567 step=3 ,此时temp[5,6,7]
//        int[] temp=new int[step];
//        for (int i = 0; i < step; i++) {
//            temp[i]=nums[n-step+i];
//        }
//        // 先从后面开始赋值 如 123 4567 k=3 ,现在变为 123 1234
//        for (int i = n-1-step; i >=0 ; i--) {
//            nums[i+step]=nums[i];
//        }
//        for (int i = 0; i < step; i++) {
//            nums[i]=temp[i];
//        }

        //方法3：翻转数组（分段多次翻转数组）
        //时间复杂度：O(n) 空间复杂度：O(1)
        //例：以 k=3 为例，结果可分为以下几步：
        //原始数组 1 2 3 4 5 6 7 翻转所有元素 7 6 5 4 3 2 1
        //翻转 [0, (k % n)-1]     区间的元素 5 6 7 4 3 2 1
        //翻转 [k % n, n-1]       区间的元素 5 6 7 1 2 3 4
        k %= nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    //对数组下标为start到end的元素值进行翻转
    public void reverse(int[] nums,int start,int end){
        while (start<end){
            nums[start] ^= nums[end];
            nums[end] ^=nums[start];
            nums[start] ^=nums[end];
            start++; end--;
        }
    }
}
