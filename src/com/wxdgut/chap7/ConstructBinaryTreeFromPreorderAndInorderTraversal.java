package com.wxdgut.chap7;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-05 09:51:52
 * <p>
 * 105. 从前序与中序遍历序列构造二叉树 难度：中等
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * <p>
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * <p>
 * 示例 :
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        //方法1：递归 时间复杂度 O(N^2)  4ms
//        return helperA(0, 0, inorder.length - 1, preorder, inorder);
//    }
//
//    private TreeNode helperA(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
//        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
//        TreeNode root = new TreeNode(preorder[preStart]);
//        int inIndex = 0;//Index of current root in inorder
//        while (inorder[inIndex] != root.val) inIndex++;
//        //递归时遍历root的左右子树即可
//        root.left = helperA(preStart + 1,
//                inStart, inIndex - 1, preorder, inorder);
//        //root的左子树区间长度为 inIndex-inStart，记为len ，故右子树的下标从preSrart+1+len
//        root.right = helperA(preStart + 1 + inIndex - inStart,
//                inIndex + 1, inEnd, preorder, inorder);
//        return root;
//    }

//    private Map<Integer, Integer> map;
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        //递归版本2 时间复杂度 O(N)  1ms
//        //将中序遍历的值和索引存在一个哈希表中，可以O(1)找到根结点在中序遍历数组中的索引。
//        map = new HashMap<>();
//        for (int i = 0; i < inorder.length; i++) {
//            map.put(inorder[i], i); //存的是下标
//        }
//        return helperB(0, 0, inorder.length - 1, preorder, inorder);
//    }
//
//    private TreeNode helperB(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
//        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
//        TreeNode root = new TreeNode(preorder[preStart]);
//        int inIndex = map.get(root.val);
//        //递归时遍历root的左右子树即可
//        root.left = helperB(preStart + 1,
//                inStart, inIndex - 1, preorder, inorder);
//        //root的左子树区间长度为 inIndex-inStart，记为len ，故右子树的下标从preSrart+1+len
//        root.right = helperB(preStart + 1 + inIndex - inStart,
//                inIndex + 1, inEnd, preorder, inorder);
//        return root;
//    }

//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        //递归版本3  2ms
//        //将中序遍历的值和索引存在一个哈希表中
//        //将前序遍历的索引存在一个队列中
//        Queue<Integer> pre = new LinkedList<>();
//        for (int i : preorder) pre.add(i); //也可以用 pre.offer(i);
//        Map<Integer, Integer> in = new HashMap<>();
//        for (int i = 0; i < inorder.length; i++) in.put(inorder[i], i);
//        return helperC(0, inorder.length - 1, pre, in);
//    }
//
//    TreeNode helperC(int inStart, int inEnd, Queue<Integer> pre, Map<Integer, Integer> in) {
//        if (inStart > inEnd) return null;
//        int rootVal = pre.remove(); //也可以用 pre.poll();
//        int inIndex = in.get(rootVal);
//        TreeNode root = new TreeNode(rootVal);
//        root.left = helperC(inStart, inIndex - 1, pre, in);
//        root.right = helperC(inIndex + 1, inEnd, pre, in);
//        return root;
//    }

    private int rootIndex = 0;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //递归版本4  1ms
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return helperD(0, inorder.length - 1, preorder, inorder);
    }

    TreeNode helperD(int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[rootIndex++]);
        int inIndex = map.get(root.val);
        root.left = helperD(inStart, inIndex - 1, preorder, inorder);
        root.right = helperD(inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        //方法2：迭代 1ms
//        //时间复杂度 O(N) 空间复杂度 O(N)
//        if (preorder == null || preorder.length == 0) return null;
//        TreeNode root = new TreeNode(preorder[0]);
//        Deque<TreeNode> stack = new LinkedList<>();
//        stack.push(root);
//        int inIndex = 0;
//        for (int i = 1; i < preorder.length; i++) {
//            int preVal = preorder[i];
//            TreeNode node = stack.peek();
//            if (node.val != inorder[inIndex]) {
//                node.left = new TreeNode(preVal);
//                stack.push(node.left);
//            } else {
//                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]) {
//                    node = stack.pop();
//                    inIndex++;
//                }
//                node.right = new TreeNode(preVal);
//                stack.push(node.right);
//            }
//        }
//        return root;
//    }

    //Definition for a binary tree node
    public static class TreeNode {
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
