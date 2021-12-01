package com.wxdgut.chap7;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-01 09:08:23
 * <p>
 * 111. 二叉树的最小深度 难度：简单
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 示例 ：
 * 输入：root = [3,9,20,null,null,15,7]         输出：2
 * 输入：root = [2,null,3,null,4,null,5,null,6] 输出：5
 */

public class MinimumDepthOfBinaryTree {

//    public int minDepth(TreeNode root) {
//        //方法1：递归 6ms
//        //递归终止条件
//        if (root == null) return 0;
//        int left = minDepth(root.left);
//        int right = minDepth(root.right);
//        if (right != 0 && left != 0) return Math.min(left, right) + 1;
//        return right + left + 1;
//        // 以上两句也可以合并为一句
//        // return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
//    }

    public int minDepth(TreeNode root) {
        //方法2：BFS 1ms
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1; //注意初始值为1
        //或者 int count = 0; 左右节点都为空时 return ++count;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) return count;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            count++;
        }
        return count;
    }


//    public int minDepth(TreeNode root) {
//        //方法3：DFS 22ms
//        if (root == null) return 0;
//        Deque<TreeNode> stack = new LinkedList<>();
//        Deque<Integer> level = new LinkedList<>();
//        stack.push(root);
//        level.push(1);
//        int min = Integer.MAX_VALUE;
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            int curLevel = level.pop();
//            if (node.left == null && node.right == null && min > curLevel) {
//                min = curLevel; //更新min为所有子节点中最小的层数
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//                level.push(curLevel + 1);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//                level.push(curLevel + 1);
//            }
//        }
//        return min;
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
