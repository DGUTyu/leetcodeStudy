package com.wxdgut.chap7;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-30 11:05:18
 * <p>
 * 104. 二叉树的最大深度 难度：简单
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，返回它的最大深度 3 。
 */

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        // 递归：推荐 0ms
        // 如果该节点不存在，则简单地返回 0，否则，返回 1+其子树的较长距离。
//        //写法1
//        //确定终止条件
//        if (root == null) return 0;
//        //处理当前逻辑,下沉
//        int left = maxDepth(root.left);
//        int right = maxDepth(root.right);
//        int middle = Math.max(left, right) + 1;
//        return middle;

//        //写法2
//        if (root == null) return 0;
//        int maxL = maxDepth(root.left) + 1;
//        int maxR = maxDepth(root.right) + 1;
//        return maxL > maxR ? maxL : maxR;

        //写法3
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

//        //写法4
//        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

//    public int maxDepth(TreeNode root) {
//        //方法2：BFS 1ms
//        //我们使用队列来进行广度优先搜索，队列具有先进先出的特性。
//        //在这里使用栈是错误的选择，栈应用于深度优先搜索。
//        if (root == null) return 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);//也可以用queue.offer()
//        int count = 0;
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                TreeNode cur = queue.remove();//也可以用queue.poll()
//                if (cur.left != null) queue.add(cur.left);
//                if (cur.right != null) queue.add(cur.right);
//            }
//            count++;
//        }
//        return count;
//    }

//    public int maxDepth(TreeNode root) {
//        //方法3：DFS 2ms
//        //我们可以使用两个栈，一个记录节点的stack栈，一个记录节点所在层数的level栈
//        //stack中每个节点在level中都会有一个对应的值，并且他们是同时出栈，同时入栈
//        if (root == null) return 0;
//        Deque<TreeNode> stack = new LinkedList<>();
//        Deque<Integer> level = new LinkedList<>();
//        stack.push(root);
//        level.push(1);
//        int max = 0;
//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            int curLevel = level.pop();
//            max = curLevel > max ? curLevel : max;
//            if (node.left != null) {
//                stack.push(node.left);
//                level.push(curLevel + 1);
//            }
//            if (node.right != null) {
//                stack.push(node.right);
//                level.push(curLevel + 1);
//            }
//        }
//        return max;
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