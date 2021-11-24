package com.wxdgut.chap4;

import sun.plugin.javascript.navig.Link;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-24 13:13:13
 * <p>
 * 144. 二叉树的前序遍历 难度：简单
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * <p>
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,2,3] 输出：[1,2,3]
 * 说明：1为根节点，null表示1没有左节点，2表示1的右节点，3表示2的左节点
 * <p>
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */

public class BinaryTreePreorderTraversal {
//    public List<Integer> preorderTraversal(TreeNode root) {
//        //前序遍历 方法1：递归
//        List<Integer> res = new ArrayList<>();
//        preOrder(root, res);
//        return res;
//    }

    //方法1的递归函数：前序遍历
    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    //扩展：中序遍历的递归函数：
    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    //扩展：后序遍历的递归函数：
    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        //前序遍历 方法2：迭代（使用模板）
        List<Integer> res = new ArrayList<>();
        //后序则为：LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                res.add(root.val); //后序则为：res.addFirst(root.val);
                root = root.left; //后序则为：root = root.right;
            }
            root = stack.pop();
            root = root.right; //后序则为：root = root.left;
        }
        return res;
    }

    //Definition for a binary tree node
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
