package com.wxdgut.chap6;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-27 11:21:56
 * <p>
 * 589. N 叉树的前序遍历 难度：简单
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * <p>
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,3,2,4,null,5,6] 输出：[1,3,5,6,2,4]
 * <p>
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 */

public class NAryTreePreorderTraversal {
//    public List<Integer> preorder(Node root) {
//        //方法1：迭代
//        List<Integer> res = new ArrayList<>();
//        if (root == null) return res;
//        Deque<Node> stack = new LinkedList<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            root = stack.pop();
//            res.add(root.val);
//            for (int i = root.children.size() - 1; i >= 0; i--)
//                stack.push(root.children.get(i));
//            //也可以这样写 不建议
//            // Collections.reverse(root.children);
//            // for (Node item : root.children)  stack.push(item);
//        }
//        return res;
//    }

    //    public List<Integer> preorder(Node root) {
//        //方法2：递归 与迭代相比用时更少
//        List<Integer> res = new ArrayList<>();
//        myPreOrder(root, res);
//        return res;
//    }
//
    //前序遍历 递归函数
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

    //扩展：后序遍历 递归函数
    private void myPostOrder(Node root, List<Integer> res) {
        if (root == null) return;
        if (!root.children.isEmpty()) {
            for (int i = 0; i < root.children.size(); i++) {
                myPostOrder(root.children.get(i), res);
            }
        }
        res.add(root.val);
    }

    public List<Integer> preorder(Node root) {
        //方法3：dfs 深度优先搜索
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    //前序遍历 dfs
    public void dfs(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i), res);
        }
        //也可以这样写 不建议
        //for (Node item : root.children) dfs(item, res);
    }

    //扩展：后序遍历 dfs
//    private void dfs(Node root, List<Integer> res) {
//        if (root == null) return;
//        for (int i = 0; i < root.children.size(); i++) {
//            dfs(root.children.get(i), res);
//        }
//        res.add(root.val);
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
