package com.wxdgut.chap9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-27 21:21:32
 * <p>
 * 107. 二叉树的层序遍历 II  难度：中等
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * <p>
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。
 * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 返回其自底向上的层序遍历为：[[15,7],[9,20],[3]]
 */

public class BinaryTreeLevelOrderTraversalII {

//    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
//            res.add(0, level);  //修改1
//        }
//        return res;
//    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //方法2：DFS--递归 0ms
        //也可以不改，对二叉树的层序遍历的结果进行 Collections.reverse(res);但占用内存多
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    public void dfs(TreeNode root, List<List<Integer>> res, Integer level) {
        if (root == null) return;
        if (level == res.size()) res.add(0, new ArrayList<>()); //修改1
        res.get(res.size() - level - 1).add(root.val);  //修改2
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
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
