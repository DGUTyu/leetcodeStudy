package com.wxdgut.chap3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-09 15:42:04
 *
 *146. LRU 缓存机制 难度：中等
 *链接：https://leetcode-cn.com/problems/lru-cache
 *
 *运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 *实现 LRUCache 类：
 * ① LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * ② int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * ③ void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 * 如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */

public class LRUCache {
    /**
     * 哈希表 + 双向链表
     * 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
     * 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
     * 在双向链表的实现中，使用一个伪头部head和伪尾部tail标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。
     * 访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)
     * 将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作,都可以在 O(1)时间内完成。
     */
    //缓存容量
    private int capacity;
    //伪头部、伪尾部
    private Node head,tail;
    //需要一个Map
    private Map<Integer,Node> cache =new HashMap<>();
    //需要一个Size保存map的大小
    private int size;

    //定义一个内部类：双向链表节点类
    class Node{
        //节点要保存key和value
        int key,value;
        //节点可以访问前后节点
        Node prev,next;
        public Node() { }
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size=0;
        //初始化伪头部和伪尾部
        head=new Node();
        tail=new Node();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key){
        //先根据key获取对应的node
        Node node=cache.get(key);
        //node不存在
        if (node==null) return -1;
        //node存在，移动到链表头部
        moveToHead(node);
        //返回node的值
        return node.value;
    }

    public void put(int key,int value){
        //先根据key获取对应的node
        Node node=cache.get(key);
        if (node==null) {
            //node为空则创建一个节点，添加到链表头部，添加到map,更新size
            Node newNode=new Node(key,value);
            addToHead(newNode);
            cache.put(key,newNode); //注意是newNode
            //判断需不需要移除老旧数据
            size++;
            if(size > capacity){
                //保存要移除的尾部节点
                Node tail= removeTail();
                //在map中根据尾部节点的key移除对应的node,更新size
                cache.remove(tail.key);
                size--;
            }
        }else{
            //key存在则更新其value并把它移动到链表头部
            node.value=value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node){ //添加到链表头部
        node.prev=head;
        node.next=head.next;
        head.next.prev=node;
        head.next=node;
    }

    private void removeNode(Node node){ //删除某个节点
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    private void moveToHead(Node node){ //移动到链表头部
        //先删除后添加
        removeNode(node);
        addToHead(node);
    }

    private Node removeTail(){ //删除链表尾部节点
        //删除尾部节点的同时返回该节点给他人备用
        Node node=tail.prev;
        removeNode(node);
        return node;
    }
}