package com.wxdgut.chap10;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-02-06 09:33:12
 * <p>
 * 860. 柠檬水找零  难度：简单
 * 链接：https://leetcode-cn.com/problems/lemonade-change
 * <p>
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
 * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * <p>
 * 示例：
 * 输入：bills = [5,5,5,10,20]  输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 * <p>
 * 输入：bills = [5,5,10,10,20]  输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 * <p>
 * 输入：bills = [5,5,10]  输出：true
 * 输入：bills = [10,10]   输出：false
 * <p>
 * 提示：
 * 1 <= bills.length <= 10^5
 * bills[i] 不是 5 就是 10 或是 20
 */

public class LemonadeChange {
//    public boolean lemonadeChange(int[] bills) {
//        /**
//         * 方法1：贪心法 时间复杂度：O(N) 2ms
//         * 当客户给我们 20 美元时，我们有两种选择：
//         * 给三个 5 美元或给一个 5 美元和一个 10 美元。
//         * 显然，后面的方案优先级更高一点。
//         * 以此类推，我们只需要记录 5 美元和 10 美元的数量即可。
//         * if (customer pays with $5) five++;
//         * if (customer pays with $10) ten++, five--;
//         * if (customer pays with $20) ten--, five-- or five -= 3;
//         * 检查five是否为正，否则返回假。
//         */
//        int five = 0, ten = 0;
//        for (int i : bills) {
//            if (i == 5) five++;
//            else if (i == 10) {
//                ten++;
//                five--;
//            } else if (ten > 0) {
//                ten--;
//                five--;
//            } else five -= 3;
//            if (five < 0) return false;
//        }
//        return true;
//    }

    public boolean lemonadeChange(int[] bills) {
        //方法1：优化版贪心法 时间复杂度：O(N) 1ms
        int five = 0, ten = 0;
        for (int i : bills) {
            if (i == 5) five++;
            else if (i == 10) {
                if (five == 0) return false;
                ten++;
                five--;
            } else if (five > 0 && ten > 0) {
                ten--;
                five--;
            } else if (five >= 3) {
                five -= 3;
            } else return false; //else 不可少
        }
        return true;
    }

//    public boolean lemonadeChange(int[] bills) {
//        //方法2：HashMap 时间复杂度：O(N) 11ms
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("5", 0);
//        map.put("10", 0);
//        for (int i = 0; i < bills.length; i++) {
//            if (bills[i] == 5) {
//                map.put("5", map.get("5") + 1);
//            } else if (bills[i] == 10) {
//                if (map.get("5") == 0) return false;
//                map.put("10", map.get("10") + 1);
//                map.put("5", map.get("5") - 1);
//            } else if (map.get("5") > 0 && map.get("10") > 0) {
//                map.put("5", map.get("5") - 1);
//                map.put("10", map.get("10") - 1);
//            } else if (map.get("5") >= 3) {
//                map.put("5", map.get("5") - 3);
//            } else return false;
//        }
//        return true;
//    }

//    public boolean lemonadeChange(int[] bills) {
//        //方法3：数组 可记录 时间复杂度：O(N) 2ms
//        int money[] = new int[3];
//        for (int i : bills) {
//            if (i == 5) {
//                money[0]++;
//            } else if (i == 10) {
//                if (money[0] == 0) return false;
//                money[1]++;
//                money[0]--;
//            } else if (money[0] > 0 && money[1] > 0) {
//                money[2]++;
//                money[1]--;
//                money[0]--;
//            } else if (money[0] >= 3) {
//                money[2]++;
//                money[0] -= 3;
//            } else return false;
//        }
//        //for (int n : money) System.out.print(n + "/");
//        return true;
//    }
}
