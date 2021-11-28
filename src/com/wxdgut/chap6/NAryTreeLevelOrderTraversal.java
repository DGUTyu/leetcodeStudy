package com.wxdgut.chap6;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-27 11:23:23
 * <p>
 * 429. N 叉树的层序遍历 难度：中等
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
 * <p>
 * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,3,2,4,null,5,6] 输出：[[1],[3,2,4],[5,6]]
 */

public class NAryTreeLevelOrderTraversal {
//    public List<List<Integer>> levelOrder(Node root) {
//        /**
//         * 方法1：利用队列实现bfs广度优先搜索
//         * 我们使用队列来进行广度优先搜索，队列具有先进先出的特性。
//         * 在这里使用栈是错误的选择，栈应用于深度优先搜索。
//         */
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            List<Integer> level = new ArrayList<>();
//            int size = queue.size();
//            //注意：不可写成  for (int i = 0; i < queue.size(); i++)
//            for (int i = 0; i < size; i++) {
//                Node node = queue.remove();
//                //或用queue.poll(); return the head of this queue
//                level.add(node.val);
//                queue.addAll(node.children);
//                //如果不想保留root，也可以直接用root 代替 node
//            }
//            res.add(level);
//        }
//        return res;
//    }

//    public List<List<Integer>> levelOrder(Node root) {
//        //方法2：简化的广度优先搜索 与方法1时间差别不大
//        List<List<Integer>> res = new ArrayList<>();
//        if (root == null) return res;
//        List<Node> previousLayer = Arrays.asList(root);
//        while (!previousLayer.isEmpty()) {
//            List<Node> currentLayer = new ArrayList<>();
//            List<Integer> previousVals = new ArrayList<>();
//            for (int i = 0; i < previousLayer.size(); i++) { //2ms
//                previousVals.add(previousLayer.get(i).val);
//                currentLayer.addAll(previousLayer.get(i).children);
//            }
//            //或 for (Node node : previousLayer) { //3ms
//            //    previousVals.add(node.val);
//            //    currentLayer.addAll(node.children);
//            //}
//            res.add(previousVals);
//            previousLayer = currentLayer;
//        }
//        return res;
//    }

    public List<List<Integer>> levelOrder(Node root) {
        /**
         * 方法3：递归
         * 通常我们不能使用递归进行广度优先搜索.这是因为广度优先搜索基于队列。
         * 而递归运行时使用堆栈，适合深度优先搜索。
         * 但是在本题中，我们可以以不同的顺序添加到最终列表中，
         * 只要我们知道节点在哪一层并确保在那一层的列表顺序正确就可以了。
         */
        List<List<Integer>> res = new ArrayList<>();
        traverseNode(root, res, 0);
        return res;
    }

    private void traverseNode(Node root, List<List<Integer>> res, int level) {
        if (root == null) return;
        if (res.size() <= level) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            traverseNode(root.children.get(i), res, level + 1);
        }
//        for (Node item : root.children) {
//            traverseNode(item, res, level + 1);
//        }
    }

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
