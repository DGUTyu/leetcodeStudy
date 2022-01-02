package com.wxdgut.chap9;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2022-01-02 14:27:28
 * <p>
 * 515. 在每个树行中找最大值  难度：中等
 * 链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row
 * <p>
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * 示例：
 * 输入: root = [1,3,2,5,3,null,9] 输出: [1,3,9]
 * 输入: root = [1,2,3]            输出: [1,3]
 * 输入: root = [1,null,2]         输出: [1,2]
 * 输入: root = [1]                输出: [1]
 * 输入: root = []                 输出: []
 * <p>
 * 提示：
 * 二叉树的节点个数的范围是 [0,10^4]
 * -2^31 <= Node.val <= (2^31) - 1
 */

public class FindLargestValueInEachTreeRow {
//    public List<Integer> largestValues(TreeNode root) {
//        //方法1：BFS 2ms
//        List<Integer> list = new ArrayList<>();
//        if (root == null) return list;
//        int max = Integer.MIN_VALUE;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            while (size-- > 0) {
//                TreeNode currNode = queue.remove();
//                max = Math.max(max, currNode.val);
//                if (currNode.left != null) queue.add(currNode.left);
//                if (currNode.right != null) queue.add(currNode.right);
//            }
//            list.add(max);
//            max = Integer.MIN_VALUE;
//        }
//        return list;
//    }

    public List<Integer> largestValues(TreeNode root) {
        //方法1：BFS 改版 2ms
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode currNode = queue.remove();
                max = Math.max(max, currNode.val);
                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
            list.add(max);
        }
        return list;
    }

//    public List<Integer> largestValues(TreeNode root) {
//        //方法2:DFS 1ms
//        List<Integer> list = new ArrayList<>();
//        dfs(root, list, 0);
//        return list;
//    }
//
//    private void dfs(TreeNode root, List<Integer> list, int level) {
//        if (root == null) return;
//        if (level == list.size()) list.add(root.val);
//        // list.set() 用指定的元素替换此列表中指定位置的元素
//        list.set(level, Math.max(list.get(level), root.val));
//        dfs(root.left, list, level + 1);
//        dfs(root.right, list, level + 1);
//    }

    //Definition for a binary tree node
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
