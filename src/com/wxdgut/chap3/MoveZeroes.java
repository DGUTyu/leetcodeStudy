package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-09 17:48:44
 *
 * 283. 移动零 难度：简单
 * 链接：https://leetcode-cn.com/problems/move-zeroes/
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 说明:
 * 1.必须在原数组上操作，不能拷贝额外的数组。
 * 2.尽量减少操作次数。
 */

public class MoveZeroes {
    /**
     * 使用双下标，i用于遍历数组，j用于记录非零元素在数组中的应有位置
     * 使用一个循环：
     * 1.对数组进行遍历，非零元素的值赋给j所指定的位置元素，将非零元素值复制到前面
     * 2.复制值后如果i所在位置与j不在同一个位置，就置i所在元素值为0
     * 3.移动 j
     *
     * 也可以用两个循环，一个用于将非零元素移动到前面，一个置j之后的下标元素值为0
     */

    public void moveZeroes(int[] nums){
        if(nums.length==0) return;
        for(int i=0,j=0;i<nums.length;i++){
            if (nums[i]!=0){
                nums[j]=nums[i];
                //复制值后如果i所在位置与j不在同一个位置，就置i所在元素值为0
                if(i!=j) nums[i]=0;
                j++;
            }
        }
    }
}
