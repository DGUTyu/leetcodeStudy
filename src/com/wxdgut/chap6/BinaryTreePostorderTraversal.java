package com.wxdgut.chap6;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-25 10:26:35
 * <p>
 * 145. 二叉树的后序遍历 难度：简单
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * <p>
 * 给你二叉树的根节点 root ，返回它节点值的 后序  遍历。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,2,3] 输出：[3,2,1]
 * 说明：1为根节点，null表示1没有左节点，2表示1的右节点，3表示2的左节点
 * <p>
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 */

public class BinaryTreePostorderTraversal {
//    public List<Integer> postorderTraversal(TreeNode root) {
//        //后序遍历 方法1：递归
//        List<Integer> res = new ArrayList<>();
//        postOrder(root, res);
//        return res;
//    }

    //方法1的递归函数：后序遍历
    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

    //扩展：中序遍历的递归函数：
    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    //扩展：前序遍历的递归函数：
    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

//    public List<Integer> postorderTraversal(TreeNode root) {
//        //后序遍历 方法2:迭代（使用模板）
//        //思路：反转前序遍历，但不建议这样做
//        LinkedList<Integer> res = new LinkedList<>();
//        Deque<TreeNode> stack = new LinkedList<>();
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                res.addFirst(root.val);
//                root = root.right;
//            }
//            root = stack.pop();
//            root = root.left;
//        }
//        return res;
//    }

    public List<Integer> postorderTraversal(TreeNode root) {
        //后序遍历 方法2
        /**
         * Brilliant code, I think it's faster than usual iterative solution.
         * Every time it gets in while loop,
         * it can pop one out and push two in if possible.
         * The most smart trick is to add value from last,
         * which saves a Hash Set to record visited nodes.
         * 精彩的代码，我认为它比通常的迭代解决方案更快。
         * 每次进入 while 循环时，如果可能，它可以弹出一个并推入两个。
         * 最聪明的技巧是从最后添加值，这会保存一个哈希集来记录访问过的节点。
         */
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            //The code returns a list like "root-right-left"
            //so clever...addFirst() gets the root behind the left and the right
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
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
