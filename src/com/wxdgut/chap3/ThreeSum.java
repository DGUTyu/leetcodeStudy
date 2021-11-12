package com.wxdgut.chap3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-12 09:42:10
 *
 * 15. 三数之和 难度：中等
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]  输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * 输入：nums = []  输出：[]
 * 示例 3：
 * 输入：nums = [0]  输出：[]
 *
 */

public class ThreeSum {
    /**
     * 「不重复」的要求使得我们无法简单地使用三重循环枚举所有的三元组。
     * 这是因为在最坏的情况下，数组中的元素全部为 0,时间复杂度至少为 O(N^3)
     * 在这之后，我们还需要使用哈希表进行去重操作，得到不包含重复三元组的最终答案。
     *
     * 思路：Arrays.sort排序 + 双指针夹逼
     * 对数组进行排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，
     * 数字分别为 nums[L]和 nums[R]，计算三个数的和 sum，判断是否满足为 0
     * 当 sum == 0时，添加进结果集
     * 如果sum > 0 说明nums[i]右侧的数过大，应该左移
     * 如果sum < 0 说明nums[i]左侧的数过小，应该右移
     * 如果 nums[i]== nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
     *
     */

    public List< List<Integer> > threeSum(int[] nums){
        List< List<Integer> > result=new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //也可以写  for (int i = 0; i < nums.length ; i++) 但不建议
            if(i>0 && nums[i]==nums[i-1]) continue; //去重，开始下一次循环
            int left=i+1,right=nums.length-1; //左右指针指向 nums[i]后面的两端
            while (left < right){
                int sum=nums[i]+nums[left]+nums[right];
                if (sum==0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < right && nums[left]==nums[left+1]) left++; //去重
                    while (left < right && nums[right]==nums[right-1]) right--; //去重
                    left++; right--;
                }else if(sum<0) left++;
                else right--;
            }
        }
        return result;
    }
}
