package com.wxdgut.chap4;

import java.util.LinkedList;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-20 20:21:55
 * <p>
 * 239. 滑动窗口最大值 难度：困难
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置 [1  3  -1] -3  5  3  6  7       最大值 3
 *              1 [3  -1  -3] 5  3  6  7       最大值 3
 * 以此类推。
 */

public class SlidingWindowMaximum {
    //方法1:使用LinkedList 实现 单调递减栈 时间复杂度：O(n)
    // 此方法中queue.peekFirst()相当于queue.peek()，可选写其中一种
    // queue.pollFirst()相当于queue.poll()

    // 解释过程中队列中都是具体的值，方便理解，具体见代码。
    // 初始状态：L=R=0,队列:{}
    // i=0,nums[0]=1。队列为空,直接加入。队列：{1}
    // i=1,nums[1]=3。队尾值为1，3>1，弹出队尾值，加入3。队列：{3}
    // i=2,nums[2]=-1。队尾值为3，-1<3，直接加入。队列：{3,-1}。此时窗口已经形成，L=0,R=2，result=[3]
    // i=3,nums[3]=-3。队尾值为-1，-3<-1，直接加入。队列：{3,-1,-3}。队首3对应的下标为1，L=1,R=3，有效。result=[3,3]
    // i=4,nums[4]=5。队尾值为-3，5>-3，依次弹出后加入。队列：{5}。此时L=2,R=4，有效。result=[3,3,5]
    // i=5,nums[5]=3。队尾值为5，3<5，直接加入。队列：{5,3}。此时L=3,R=5，有效。result=[3,3,5,5]
    // i=6,nums[6]=6。队尾值为3，6>3，依次弹出后加入。队列：{6}。此时L=4,R=6，有效。result=[3,3,5,5,6]
    // i=7,nums[7]=7。队尾值为6，7>6，弹出队尾值后加入。队列：{7}。此时L=5,R=7，有效。result=[3,3,5,5,6,7]
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // Deque<Integer> queue = new LinkedList();  //也可以这样写但不建议
        int[] res = new int[nums.length - (k - 1)]; // 结果数组
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 防止窗口爆炸，窗口左边界 L=R-(k-1) 即L=i+1-k
            if (queue.peekFirst() < i - (k - 1)) {
                queue.pollFirst();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            if ((i + 1 - k) >= 0) {
                res[i + 1 - k] = nums[queue.peekFirst()];
            }
        }
        return res;
    }


    //方法2：动态规划，找左右两边最大值
    //参考：https://leetcode.com/problems/sliding-window-maximum/discuss/65881/O(n)-solution-in-Java-with-two-simple-pass-in-the-array/67883
    //比如题目中的例子：
    //left_max数组（right_max数组）存储每个窗口中从左到右（从右到左）的累计最大值
    //先将以k个为一组分开，即
    //1, 3, -1, | -3, 5, 3, | 6, 7
    //再每一组中求出 left_max
    //1, 3, 3, | -3, 5, 5, | 6, 7
    //再求出 right_max
    //3, 3, -1, | 5, 5, 3, | 7, 7
    //那么最大值等于 max(right_max[i], left_max[i + k - 1])
    //3, 3, 5, 5, 6, 7

//    public static int[] maxSlidingWindow(int[] nums, int k) {
//        int[] left = new int[nums.length]; //存储每个窗口中从左到右的累计最大值
//        int[] right = new int[nums.length]; //存储每个窗口中从右到左的累计最大值
//        left[0] = nums[0]; //初始化
//        right[nums.length - 1] = nums[nums.length - 1]; //初始化
//        for (int i = 1; i < nums.length; i++) {
//            left[i] = (i % k == 0) ? nums[i] : Math.max(left[i - 1], nums[i]);
//            int j = nums.length - 1 - i;
//            right[j] = (j % k == 0) ? nums[j] : Math.max(right[j + 1], nums[j]);
//        }
//        int[] res = new int[nums.length - (k - 1)]; //存储每次窗口的最大值
//        for (int i = 0, j = 0; i < res.length; i++) {
//            res[j++] = Math.max(right[i], left[i + k - 1]);
//        }
//        return res;
//    }

    //方法3：执行用时为 2 ms 的范例,
    //执行用时：2 ms, 在所有 Java 提交中击败了100.00%的用户
    //内存消耗：59.5 MB, 在所有 Java 提交中击败了5.02%的用户
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int n = nums.length, max = Integer.MIN_VALUE, index = -1;
//        int[] res = new int[n - (k - 1)];
//        for (int left = 0, right = k - 1; right < n; ++left, ++right) {
//            if (left <= index) {
//                if (nums[right] >= max) {
//                    max = nums[right];
//                    index = right;
//                }
//            } else if (nums[right] >= max - 1) {
//                max = nums[right];
//                index = right;
//            } else if (nums[left] >= max - 1) {
//                max = nums[left];
//                index = left;
//            } else {
//                max = Integer.MIN_VALUE;
//                for (int i = left; i <= right; ++i) {
//                    if (nums[i] >= max) {
//                        max = nums[i];
//                        index = i;
//                    }
//                }
//            }
//            res[left] = max;
//        }
//        return res;
//    }
}
