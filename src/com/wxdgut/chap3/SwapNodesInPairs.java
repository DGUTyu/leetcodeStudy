package com.wxdgut.chap3;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-13 15:10:25
 *
 * 24. 两两交换链表中的节点 难度：中等
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 *
 * （可先做 206. 反转链表 难度：简单）
 * （https://leetcode-cn.com/problems/reverse-linked-list/submissions/）
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4] 输出：[2,1,4,3]
 * 示例 2：
 * 输入：head = [] 输出：[]
 * 示例 3：
 * 输入：head = [1] 输出：[1]
 */

public class SwapNodesInPairs {
    /**
     * 方法1：迭代
     * 创建哑结点 dummyHead(不移动)，令 dummyHead.next = head。
     * 令 temp 表示当前到达的节点（移动），初始时 temp = dummyHead。
     * 每次需要交换 temp 后面的两个节点node1 和 node2。
     * temp.next = node2; node1.next = node2.next; node2.next = node1;
     * 两两交换链表中的节点之后，temp移动，令 temp=node1
     * 最后新的链表的头节点是 dummyHead.next
     *
     * 方法2：递归
     * 用 head 表示原始链表的头节点，
     * 用 newHead 表示新的链表的头节点/原始链表的第二个节点,
     * 则原始链表中的其余节点的头节点是 newHead.next。
     * 令 head.next = swapPairs(newHead.next)，
     * 表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。
     * 然后令 newHead.next = head,即完成了所有节点的交换。
     * 最后返回新的链表的头节点 newHead
     */
    public ListNode swapPairs(ListNode head){
        //方法1：迭代 时间复杂度：O(n)，空间复杂度：O(1)
        // n 是链表的节点数量，需要对每个节点进行更新指针的操作。
//        ListNode dummyHead=new ListNode(0);
//        dummyHead.next=head;
//        ListNode temp=dummyHead;
//        while (temp.next!=null && temp.next.next!=null){
//            ListNode node1=temp.next;
//            ListNode node2=temp.next.next;
//            temp.next=node2;
//            node1.next=node2.next;
//            node2.next=node1;
//            temp=node1;
//        }
//        return dummyHead.next;

        //方法2：递归  n 是链表的节点数量
        // 时间复杂度：O(n)  需要对每个节点进行更新指针的操作
        // 空间复杂度：O(n)O(n)  主要取决于递归调用的栈空间。

        //递归终止条件
        if(head==null || head.next==null) return head;
        ListNode newHead=head.next;
        head.next=swapPairs(newHead.next);
        //head.next=swapPairs(head.next.next);//也可以这样写
        newHead.next=head;
        return newHead;
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
