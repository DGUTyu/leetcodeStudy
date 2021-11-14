package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-14 09:45:38
 *
 * 26. 删除有序数组中的重复项 难度：简单
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用O(1)额外空间的条件下完成。
 *
 * 提示：nums 已按升序排列
 *
 * 示例 1：
 * 输入：nums = [1,1,2] 输出：2, nums = [1,2]
 *
 */

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums){
        //常规for循环
//        int j=0;
//        for (int i = 0; i < nums.length; i++) {
//            if(j==0 || nums[i]>nums[j-1]){ //注意是j-1
//                nums[j++]=nums[i];
//            }
//        }
//        return j;

        //增强for循环1
//        int j=0;
//        for(int n : nums){
//            if(j==0 || n>nums[j-1]){
//                nums[j++]=n;
//            }
//        }
//        return j;

        //增强for循环2
//        int j=nums.length > 0 ? 1 : 0;
//        for(int n : nums){
//            if(n>nums[j-1]) nums[j++]=n;
//        }
//        return j;

        //官方双指针
//        int n=nums.length;
//        if(n==0) return 0;
//        int slow=1,fast=1;
//        while (fast<n){
//            if(nums[fast] != nums[fast-1]){
//                nums[slow++]=nums[fast];
//            }
//            fast++;
//        }
//        return slow;

        //改版-官方双指针
        int slow=nums.length > 0 ? 1 : 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if(nums[fast] != nums[fast-1]) nums[slow++]=nums[fast];
        }
        return slow; //注意不要写成 show+1
    }

}
