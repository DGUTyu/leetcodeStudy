package com.wxdgut.chap7;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-29 15:00:27
 * <p>
 * 98. 验证二叉搜索树 难度：中等
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * <p>
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 1.节点的左子树只包含 小于 当前节点的数。
 * 2.节点的右子树只包含 大于 当前节点的数。
 * 3.所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例：
 * 输入：root = [2,1,3]               输出：true
 * 输入：root = [5,1,4,null,null,3,6] 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 */

public class ValidateBinarySearchTree {
    public long pre = Long.MIN_VALUE;//[-2147483648] 预期结果：true

    public boolean isValidBST(TreeNode root) {
        //方法1：中序遍历递归版 0 ms
        //二叉搜索树中序遍历为升序
        //需要添加成员变量：long pre=Long.MIN_VALUE;
        //时间复杂度 : O(n) 空间复杂度 : O(n)
        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        if (root == null) return true;
        boolean left = inOrder(root.left);
        if (root.val <= pre) return false;
        pre = root.val;
        boolean right = inOrder(root.right);
        return left && right;
    }

//    public boolean isValidBST(TreeNode root) {
//        //方法2：中序遍历BST 迭代版 1 ms
//        //时间复杂度 : O(n) 空间复杂度 : O(n)
//        if (root == null) return false;
//        long pre = Long.MIN_VALUE;
//        Deque<TreeNode> stack = new LinkedList<>();
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (root.val <= pre) return false;
//            pre = root.val;
//            root = root.right;
//        }
//        return true;
//    }

    //    public boolean isValidBST(TreeNode root) {
//        //方法2的改版：更具健壮性
//        //类似题目：230. 二叉搜索树中第K小的元素（K 从 1 开始计数）。
//        //链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
//        //Deque<TreeNode> stack = new LinkedList<>();
//        //while (root != null || !stack.isEmpty()) {
//        //   while (root != null) {
//        //       stack.push(root);
//        //        root = root.left;
//        //    }
//        //    root = stack.pop();
//        //    if (--k == 0) break;
//        //    root = root.right;
//        //}
//        //return root.val;
//
//        if (root == null) return false;
//        Deque<TreeNode> stack = new LinkedList<>();
//        TreeNode pre = null;
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (pre != null && root.val <= pre.val) return false;
//            pre = root;
//            root = root.right;
//        }
//        return true;
//    }

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
