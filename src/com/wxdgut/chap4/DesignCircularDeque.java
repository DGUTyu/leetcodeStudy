package com.wxdgut.chap4;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-21 16:51:01
 * <p>
 * 641. 设计循环双端队列 难度：中等
 * 链接：https://leetcode-cn.com/problems/design-circular-deque
 * <p>
 * 设计实现双端队列。你的实现需要支持以下操作：
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。isFull()：检查双端队列是否满了。
 */

public class DesignCircularDeque {
    // 使用静态数组
    // 判别队列为空的条件是：first == last
    // 判别队列为满的条件是：(last + 1) % capacity == first
    // 因为有循环的出现，要特别注意处理数组下标可能越界的情况。
    // 注意：先赋值还是先移动指针
    // 为了避免「队列为空」和「队列为满」的判别条件冲突，我们有意浪费了一个位置
    // 浪费一个位置是指：循环数组中任何时刻一定至少有一个位置不存放有效元素
    private int capacity;
    private int first;
    private int last;
    private int arr[];

    public DesignCircularDeque(int k) {
        capacity = k + 1;
        first = 0;
        last = 0;
        arr = new int[capacity];
    }

    public boolean insertFront(int value) {
        if (isFull()) return false;
        first = (first - 1 + capacity) % capacity;
        arr[first] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) return false;
        arr[last] = value;
        last = (last + 1) % capacity;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) return false;
        first = (first + 1) % capacity;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) return false;
        last = (last - 1 + capacity) % capacity;
        return true;
    }

    public int getFront() {
        if (isEmpty()) return -1;
        return arr[first];
    }

    public int getRear() {
        if (isEmpty()) return -1;
        return arr[(last - 1 + capacity) % capacity];
    }

    public boolean isEmpty() {
        return first == last;
    }

    public boolean isFull() {
        return (last + 1) % capacity == first;
    }
}
