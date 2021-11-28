package com.wxdgut.chap5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-12 09:38:38
 *
 * 1. 两数之和 难度：简单
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。
 * 但是，数组中同一个元素在答案里不能重复出现。你可以按任意顺序返回答案。
 *
 */

public class TwoSum {
    /**
     * 方法1：双重for循环 暴力枚举
     * 方法2：使用哈希表，将寻找 target - x 从 O(N)降低到 O(1)
     */

    public int[] twoSum(int[] nums,int target){
        //方法1：双重for循环 暴力枚举 时间复杂度O(N^2)
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if(nums[i]+nums[j]==target){
//                    return new int[]{i,j};
//                }
//            }
//        }
//        return new int[0]; //防止编译器报错，返回一个垃圾值

        //方法2：使用哈希表，时间复杂度O(N)
        Map<Integer,Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //元素值为key,元素坐标为value，故这里要使用containsKey而不是containsValue
            if(map.containsKey(target-nums[i])){
                return new int[]{ map.get(target-nums[i]),i };
            }
            map.put(nums[i],i);
        }
        return new int[0];//防止编译器报错，返回一个垃圾值
    }

}
