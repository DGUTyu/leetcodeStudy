package com.wxdgut.chap3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-13 21:12:00
 *
 * 141. 环形链表 难度：简单
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 *
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 示例：
 * 输入：head = [3,2,0,-4], pos = 1  输出：true
 * 输入：head = [1], pos = -1        输出：false
 *
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */

public class LinkedListCycle {
    /**
     * 方法1：HashSet  N 是链表中的节点数
     * 时间复杂度：O(N) 空间复杂度：O(N)
     * 使用哈希表来存储所有已经访问过的节点。每次我们到达一个节点，
     * 如果该节点已经存在于哈希表中，则说明该链表是环形链表，
     * 否则就将该节点加入哈希表中。重复这一过程，直到遍历完整个链表。
     *
     * 方法2：快慢指针
     * 时间复杂度：O(N) 空间复杂度：O(1)
     * 慢指针每次只移动一步，而快指针每次移动两步。
     * 初始时，慢指针在位置 head，而快指针在位置 head.next。
     * 在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。
     */

    public boolean hasCycle(ListNode head){
        // 方法1：HashSet
//        if(head==null || head.next==null) return false;
//        Set<ListNode> seen =new HashSet<>();
//        while(head!=null){
//            //有重复则会添加失败返回false
//            if( !seen.add(head)) return true;
//            head=head.next;
//        }
//        return false;

        // 方法2：快慢指针
        if(head==null || head.next==null) return false;
        //初始化快慢指针起点
        ListNode slow=head;
        ListNode fast=head.next;
        while (slow!=fast){
            //fast最快会指向null,先进行判断
            if(fast==null || fast.next==null) return false;
            //快慢指针开始移动
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
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
