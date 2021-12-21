package com.wxdgut.chap8;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-18 09:16:46
 * <p>
 * 229. 求众数 II 难度：中等
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * <p>
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 示例：
 * 输入：[3,2,3]              输出：[3]
 * 输入：[1,1,1,3,3,2,2,2]    输出：[1,2]
 * 输入：[1,1,1,3,3,3,2,2,2]  输出：[]
 * <p>
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * -109 <= nums[i] <= 109
 * <p>
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 */

public class MajorityElementII {

//    public List<Integer> majorityElement(int[] nums) {
//        //方法1：摩尔投票-官方版 时间复杂度：O(n) 2ms
//        int a = 0;
//        int b = 0;
//        int countA = 0;
//        int countB = 0;
//        for (int n : nums) {
//            if (countA > 0 && n == a) { //如果该元素为第一个元素，则计数加1
//                countA++;
//            } else if (countB > 0 && n == b) { //如果该元素为第二个元素，则计数加1
//                countB++;
//            } else if (countA == 0) { // 选择第一个元素
//                a = n;
//                countA++;
//            } else if (countB == 0) { // 选择第二个元素
//                b = n;
//                countB++;
//            } else { //如果三个元素均不相同，则相互抵消1次
//                countA--;
//                countB--;
//            }
//        }
//        int cnt1 = 0;
//        int cnt2 = 0;
//        for (int num : nums) {
//            if (countA > 0 && num == a) cnt1++;
//            if (countB > 0 && num == b) cnt2++;
//        }
//        // 检测元素出现的次数是否满足要求
//        List<Integer> ans = new ArrayList<>();
//        if (countA > 0 && cnt1 > nums.length / 3) ans.add(a);
//        if (countB > 0 && cnt2 > nums.length / 3) ans.add(b);
//        return ans;
//    }

//    public List<Integer> majorityElement(int[] nums) {
//        /**
//         * 方法1：摩尔投票法改版1 时间复杂度：O(n) 2ms
//         * 对于本题，我们需要统计出现次数超过 n/3 的数。
//         * 我们可以不失一般性的将其拓展为「统计出现次数超过 n/k 的数」。
//         * 可以证明，出现次数超过 n/k 的数最多只有 k - 1 个。
//         * 否则必然违背「数总共只有 n 个」或者「当前统计的是出现次数超过 n/k 的数」的前提条件。
//         *
//         */
//        int a = 0, b = 0;
//        int countA = 0, countB = 0;
//        for (int n : nums) {
//            if (countA != 0 && a == n) countA++;
//            else if (countB != 0 && b == n) countB++;
//            else if (countA == 0 && ++countA >= 0) a = n;
//            else if (countB == 0 && ++countB >= 0) b = n;
//            else {
//                countA--; countB--;
//            }
//        }
//        countA = 0; countB = 0;
//        for (int n : nums) {
//            if (a == n) countA++;
//            else if (b == n) countB++;
//        }
//        List<Integer> res = new ArrayList<>();
//        if (countA > nums.length / 3) res.add(a);
//        if (countB > nums.length / 3) res.add(b);
//        return res;
//    }

    public List<Integer> majorityElement(int[] nums) {
        //方法1：摩尔投票法改版2 时间复杂度：O(n) 1ms
        int a = nums[0], countA = 0;
        int b = nums[0], countB = 0;
        for (int n : nums) {
            if (a == n) {
                countA++;
                continue;
            }
            if (b == n) {
                countB++;
                continue;
            }
            if (countA == 0) {
                a = n; //换候选人
                countA++;
                continue;
            }
            if (countB == 0) {
                b = n; //换候选人
                countB++;
                continue;
            }
            countA--;
            countB--;
        }
        //到此已经选出候选人 a b ,此时的a、b的票数是比淘汰的候选人多出来的票数
        countA = 0;
        countB = 0;
        for (int it : nums) {
            if (it == a) countA++;
            else if (it == b) countB++;
        }
        List<Integer> list = new ArrayList<>();
        if (countA > nums.length / 3) list.add(a);
        if (countB > nums.length / 3) list.add(b);
        return list;
    }

//    public List<Integer> majorityElement(int[] nums) {
//        //方法2：哈希表 11ms
//        Map<Integer, Integer> map = new HashMap<>();
//        List<Integer> res = new ArrayList<>();
////        for (int n : nums) {
////            if (!map.containsKey(n)) map.put(n, 1);
////            else map.put(n, map.get(n) + 1);
////        }
//        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);
//        for (int res : map.keySet()) {
//            if (map.get(res) > nums.length / 3) res.add(res);
//        }
//        return res;
//    }
}
