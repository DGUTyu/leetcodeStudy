package com.wxdgut.chap3;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-16 11:05:54
 *
 * 21. 合并两个有序链表 难度：简单
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4] 输出：[1,1,2,3,4,4]
 *
 * 提示：l1 和 l2 均按 非递减顺序 排列
 */

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        //方法1：递归
        // 时间复杂度：O(n + m) 空间复杂度：O(n + m)
//        if(l1==null) return l2;
//        if(l2==null) return l1;
//        ListNode min = l1.val < l2.val ? l1 : l2;
//        min.next=mergeTwoLists(min.next,min == l1 ? l2 : l1);
//        return min;

        //方法2：迭代
        // 时间复杂度：O(n + m) 空间复杂度：O(1)
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode preHead=new ListNode();
        ListNode min=preHead;
        while (l1!=null && l2!=null){
            if(l1.val<l2.val){
                min.next=l1;
                l1=l1.next;
            }else{
                min.next=l2;
                l2=l2.next;
            }
            min=min.next;
        }
        //接上没有遍历完的链表
        min.next = l1==null ? l2 : l1;
        return preHead.next;
    }

    //Definition for singly-linked list.
    public class ListNode{
        int val;
        ListNode next;

        ListNode(){}

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
