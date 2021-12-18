package com.wxdgut.chap8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-18 09:16:31
 * <p>
 * 169. 多数元素 难度：简单
 * 链接：https://leetcode-cn.com/problems/majority-element
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例：
 * 输入：[3,2,3]         输出：3
 * 输入：[2,2,1,1,1,2,2] 输出：2
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */

public class MajorityElement {

    public int majorityElement(int[] nums) {
        //方法1：摩尔投票法 时间复杂度 O(n) 1ms
        int count = 0, res = 0;
        for (int i : nums) {
            if (count == 0) res = i;
            if (res == i) count++;
            else count--;
        }
        return res;
    }

//    public int majorityElement(int[] nums) {
//        /**
//         * 方法2：排序 时间复杂度为 O(nlogn) 2ms
//         * 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，
//         * 那么下标为 ⌊2/n⌋ 的元素（下标从 0 开始）一定是众数。
//         */
//        Arrays.sort(nums);
//        return nums[nums.length / 2];
//    }

//    public int majorityElement(int[] nums) {
//        //方法3：哈希表 时间复杂度为 O(n) 17ms
//        Map<Integer, Integer> map = new HashMap<>();
//        int res = 0;
//        for (int n : nums) {
//            if (!map.containsKey(n)) map.put(n, 1);
//            else map.put(n, map.get(n) + 1);
//            //map.put(n, map.getOrDefault(n, 0) + 1); //以上两句也可以写为
//            if (map.get(n) > nums.length / 2) {
//                res = n;
//                break;
//            }
//        }
//        return res;
//    }

//    public int majorityElement(int[] nums) {
//        /**
//         * 方法4：分治 时间复杂度为 O(nlogn) 1ms
//         * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，
//         * 那么 a 必定是至少一部分的众数，这样就可以使用分治法解决这个问题。
//         * 将数组分成左右两部分，分别求出左半部分的众数 a1 以及右半部分的众数 a2，
//         * 随后在 a1 和 a2 中选出正确的众数。
//         *
//         * 使用经典的分治算法递归求解，直到所有的子问题都是长度为 1 的数组。
//         * 长度为 1 的子数组中唯一的数显然是众数，直接返回即可。
//         * 如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。
//         * 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。
//         * 否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
//         */
//        return majorityElementRec(nums, 0, nums.length - 1);
//    }
//
//    private int majorityElementRec(int[] nums, int start, int end) {
//        if (start == end) return nums[start];
//        // 在该切片的左半部和右半部递归
//        int mid = (end - start) / 2 + start;
//        int left = majorityElementRec(nums, start, mid);
//        int right = majorityElementRec(nums, mid + 1, end);
//        // 如果两边多数元素一致，则返回该元素。
//        if (left == right) return left;
//        //否则，计算每个元素并返回对应的数量。
//        int leftCount = countInRange(nums, left, start, end);
//        int rightCount = countInRange(nums, right, start, end);
//        return leftCount > rightCount ? left : right;
//    }
//
//    private int countInRange(int[] nums, int n, int start, int end) {
//        int count = 0;
//        for (int i = start; i <= end; i++) {
//            if (nums[i] == n) count++;
//        }
//        return count;
//    }
}
