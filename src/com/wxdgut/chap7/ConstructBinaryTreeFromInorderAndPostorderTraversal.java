package com.wxdgut.chap7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-06 09:21:15
 * <p>
 * 106. 从中序与后序遍历序列构造二叉树 难度：中等
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * <p>
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:你可以假设树中没有重复的元素。
 * <p>
 * 测试用例：
 * 二叉树：[1,2,3,4,5,6,7,null,null,8,9,null,10]
 * 前序：[1,2,4,5,8,9,3,6,10,7]
 * 中序：[4,2,8,5,9,1,6,10,3,7]
 * 后序：[4,8,9,5,2,10,6,7,3,1]
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private int rootIndex = 0;
    private Map<Integer, Integer> map = null;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //方法1：递归 1ms
        rootIndex = postorder.length - 1;
        map = new HashMap<>();
        for (int rootIndex = 0; rootIndex < inorder.length; rootIndex++) map.put(inorder[rootIndex], rootIndex);
        return helperA(0, inorder.length - 1, inorder, postorder);
    }

    private TreeNode helperA(int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[rootIndex--]);
        int inIndex = map.get(root.val);
        //以下两句不可调换顺序 root.right 必须在 root.left 之前
        root.right = helperA(inIndex + 1, inEnd, inorder, postorder);
        root.left = helperA(inStart, inIndex - 1, inorder, postorder);
        return root;
    }

//    //官方递归 1ms
//    int rootIndex;
//    int[] postorder;
//    int[] inorder;
//    Map<Integer, Integer> map = new HashMap<>();
//
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        this.postorder = postorder;
//        this.inorder = inorder;
//        rootIndex = postorder.length - 1;
//        int idx = 0;
//        for (Integer val : inorder) map.put(val, idx++);
//        return helperB(0, inorder.length - 1);
//    }
//
//    public TreeNode helperB(int inStart, int inEnd) {
//        if (inStart > inEnd) return null;
//        TreeNode root = new TreeNode(postorder[rootIndex--]);
//        int inIndex = map.get(root.val);
//        root.right = helperB(inIndex + 1, inEnd);
//        root.left = helperB(inStart, inIndex - 1);
//        return root;
//    }

//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        //方法2：迭代
//        if (inorder.length == 0 || postorder.length == 0) return null;
//        int inIndex = inorder.length - 1;
//        int postIndex = postorder.length - 1;
//        Deque<TreeNode> stack = new LinkedList<>();
//        TreeNode prev = null;
//        TreeNode root = new TreeNode(postorder[postIndex--]);
//        stack.push(root);
//        while (postIndex >= 0) {
//            while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]) {
//                prev = stack.pop();
//                inIndex--;
//            }
//            TreeNode newNode = new TreeNode(postorder[postIndex]);
//            if (prev != null) {
//                prev.left = newNode;
//            } else if (!stack.isEmpty()) {
//                TreeNode currTop = stack.peek();
//                currTop.right = newNode;
//            }
//            stack.push(newNode);
//            prev = null;
//            postIndex--;
//        }
//        return root;
//    }

//    //方法3：dfs 0ms
//    private int rootIndex, inIndex;
//
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        rootIndex = inIndex = inorder.length - 1;
//        return dfsBuildTree(null, inorder, postorder);
//    }
//
//    private TreeNode dfsBuildTree(TreeNode parent, int[] inorder, int[] postorder) {
//        if (rootIndex < 0) return null;
//        if (parent != null && inorder[inIndex] == parent.val) {
//            inIndex--;
//            return null;
//        }
//        TreeNode node = new TreeNode(postorder[rootIndex--]);
//        node.right = dfsBuildTree(node, inorder, postorder);
//        node.left = dfsBuildTree(parent, inorder, postorder);
//        return node;
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
