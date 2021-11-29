package com.wxdgut.chap7;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-29 14:58:05
 * <p>
 * 226. 翻转二叉树 难度：简单
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * <p>
 * 翻转一棵二叉树。
 */

public class InvertBinaryTree {
//    public TreeNode invertTree(TreeNode root) {
//        //方法1：递归
//        // 递归终止条件
//        if (root == null) return null;
//        //当前处理逻辑
//        TreeNode tmp = root.right;
//        root.right = root.left;
//        root.left = tmp;
//        //下沉到下一层
//        invertTree(root.left);
//        invertTree(root.right);
//        return root;
//    }

//    public TreeNode invertTree(TreeNode root) {
//        //方法2：DFS递归
//        // 递归终止条件
//        if (root == null) return null;
//        TreeNode left = root.left;
//        TreeNode right = root.right;
//        root.left = invertTree(right);
//        root.right = invertTree(left);
//        return root;
//    }

//    public TreeNode invertTree(TreeNode root) {
//        //方法3：栈 更健壮
//        if (root == null) return null;
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            //左右节点翻转
//            TreeNode temp = node.left;
//            node.left = node.right;
//            node.right = temp;
//            //处理左右子树
//            if (node.left != null) stack.push(node.left);
//            if (node.right != null) stack.push(node.right);
//        }
//        return root;
//    }

//    public TreeNode invertTree(TreeNode root) {
//        //方法4：BFS
//        if (root == null) return null;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root); //也可以用queue.offer()
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.remove(); //也可以用queue.poll()
//            TreeNode temp = node.left;
//            node.left = node.right;
//            node.right = temp;
//            if (node.left != null) queue.add(node.left);
//            if (node.right != null) queue.add(node.right);
//        }
//        return root;
//    }

    public TreeNode invertTree(TreeNode root) {
        //方法2：DFS递归改版
        if (root == null) return null;
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
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
