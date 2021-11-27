package com.wxdgut.chap4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-27 11:22:47
 * <p>
 * 590. N 叉树的后序遍历 难度：简单
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * <p>
 * 给定一个 N 叉树，返回其节点值的 后序遍历 。
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,3,2,4,null,5,6] 输出：[5,6,3,2,4,1]
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 */

public class NAryTreePostorderTraversal {

//    public List<Integer> postorder(Node root) {
//        //方法1：迭代 前序遍历的反转
//        LinkedList<Integer> res = new LinkedList<>();
//        if (root == null) return res;
//        Deque<Node> stack = new LinkedList<>();
//        stack.addLast(root);
//        while (!stack.isEmpty()) {
//            root = stack.pollLast();
//            res.addFirst(root.val);
//            for (int i = 0; i < root.children.size(); i++) {
//                stack.addLast(root.children.get(i));
//            }
//        }
//        return res;
//    }

    //    public List<Integer> postorder(Node root) {
//        //方法2：递归 与迭代相比用时更少
//        List<Integer> res = new ArrayList<>();
//        myPostOrder(root, res);
//        return res;
//    }
//
    //后序遍历 递归函数
    private void myPostOrder(Node root, List<Integer> res) {
        if (root == null) return;
        if (!root.children.isEmpty()) {
            for (int i = 0; i < root.children.size(); i++) {
                myPostOrder(root.children.get(i), res);
            }
        }
        res.add(root.val);
    }

    //扩展：前序遍历 递归函数
    private void myPreOrder(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        if (!root.children.isEmpty()) {
            for (int i = 0; i < root.children.size(); i++) {
                myPreOrder(root.children.get(i), res);
            }
            //也可以这样写 不建议
            //for (Node item : root.children) myPreOrder(item, res);
        }
    }

    public List<Integer> postorder(Node root) {
        //方法3：dfs
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    //后序遍历 dfs
    private void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i), res);
        }
        res.add(root.val);
    }

    //扩展：前序遍历 dfs
//    public void dfs(Node root, List<Integer> res) {
//        if (root == null) return;
//        res.add(root.val);
//        for (int i = 0; i < root.children.size(); i++) {
//            dfs(root.children.get(i), res);
//        }
//        //也可以这样写 不建议
//        //for (Node item : root.children) dfs(item, res);
//    }

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
