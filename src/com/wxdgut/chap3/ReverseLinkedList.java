package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-13 11:24:55
 *
 * 206. 反转链表 难度：简单
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 */

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head){
        //方法1：迭代反转 需要用到指向当前节点的指针/前一节点的指针
//        ListNode prev=null;
//        ListNode curr=head;
//        while (curr!=null){
//            //curr.next做出修改前，先用临时变量记住curr.next
//            ListNode next=curr.next;
//            curr.next=prev;  //当前指针指向前一个节点
//            prev=curr;       //指针后移
//            curr=next;       //指针后移
//        }
//        return prev;

        //方法2：递归
        //递归终止条件   最小子问题
        if(head==null || head.next==null) return head;
        //递
        ListNode p = reverseList(head.next);
        //归
        head.next.next=head; //反转
        head.next=null;
        return p;
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
