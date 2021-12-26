package com.wxdgut.chap9;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-26 21:33:47
 * <p>
 * 102. 二叉树的 层序 遍历 难度：中等
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * <p>
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。（即逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 返回其层序遍历结果：[[3],[9,20],[15,7]]
 */

public class BinaryTreeLevelOrderTraversal {

//    public List<List<Integer>> levelOrder(TreeNode root) {
//        //方法1：BFS 时间复杂度为 O(n) 1ms
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root); //也可以用queue.offer()
//        while (!queue.isEmpty()) {
//            List<Integer> level = new ArrayList<>();
//            int currentLevelSize = queue.size();
//            while (currentLevelSize-- > 0) {
//                TreeNode node = queue.remove(); //也可以用queue.poll()
//                level.add(node.val);
//                if (node.left != null) queue.add(node.left);
//                if (node.right != null) queue.add(node.right);
//            }
//            res.add(level);
//        }
//        return res;
//    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        //方法2：DFS--递归 0ms
        List<List<Integer>> res = new ArrayList<>();
        dfsB(root, res, 0);
        return res;
    }

    public void dfsA(TreeNode root, List<List<Integer>> res, Integer level) {
        if (root == null) return;
        level++;
        //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
        if (res.size() < level) res.add(new ArrayList<>());
        res.get(level - 1).add(root.val);
        dfsA(root.left, res, level);
        dfsA(root.right, res, level);
    }

    public void dfsB(TreeNode root, List<List<Integer>> res, Integer level) {
        if (root == null) return;
        //if (!(res.size() > level)) res.add(new ArrayList<>());
        //if (level >= res.size()) res.add(new ArrayList<>());
        if (level == res.size()) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        dfsB(root.left, res, level + 1);
        dfsB(root.right, res, level + 1);
    }

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
