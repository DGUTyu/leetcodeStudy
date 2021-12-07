package com.wxdgut.chap7;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-07 08:44:28
 * <p>
 * 889. 根据前序和后序遍历构造二叉树 难度：中等
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * <p>
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 * pre 和 post 遍历中的值是不同的正整数。
 * <p>
 * 测试用例：
 * 二叉树：[1,2,3,4,5,6,7,null,null,8,9,null,10]
 * 前序：[1,2,4,5,8,9,3,6,10,7]
 * 中序：[4,2,8,5,9,1,6,10,3,7]
 * 后序：[4,8,9,5,2,10,6,7,3,1]
 * <p>
 * 示例：
 * 输入：pre = [1,2,4,5,3,6,7],        post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 * 输入：pre = [1,2,4,5,8,9,3,6,10,7], post = [4,8,9,5,2,10,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7,null,null,8,9,10]
 * <p>
 * 提示：每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 */

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    private int preIndex = 0;
    private int postIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        /**
         * 方法1：递归 0ms  时间复杂度 O(N)
         * 说明：仅提供前序遍历和后序遍历无法确定一棵二叉树，可能有多种结果
         * 思路：
         * 当我们遇到一个节点值pre[i]等于当前post[j]时，
         * 这意味着我们已经完成了pre[i]子树的构建。
         * 因此，我们不应该继续向该子树添加子节点。
         * 相反，我们应该弹出该子树并继续到可以添加子节点的路径。
         *
         * 创建一个节点树节点（pre[preIndex]）作为根节点。
         * 因为根节点将按后序遍历进行最后迭代，
         * 如果root.val==post[posIndex]，这意味着我们已经建造了整棵树，
         * 如果我们还没有完成整棵树的建造，我们就递归左子树和右子树。
         * 最后，我们将得到posIndex，root.val==post[postIndex]。
         * 我们移动posIndex并返回根节点。
         *
         * 当我们遍历post并访问post[i]时，
         * 这意味着post[i]树中的所有子节点都已被访问。
         * 因此，我们可以使用pre来构造树，
         * 而使用post来确定我们访问的节点是否仍然有未访问的子节点。
         */
        TreeNode root = new TreeNode(preorder[preIndex++]); //注意移动preIndex下标
        if (root.val != postorder[postIndex])
            root.left = constructFromPrePost(preorder, postorder);
        if (root.val != postorder[postIndex])
            root.right = constructFromPrePost(preorder, postorder);
        postIndex++;
        return root;
    }


//    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
//        //方法2：迭代 使用双端队列 1ms
//        Deque<TreeNode> deque = new LinkedList<>();
//        deque.offer(new TreeNode(preorder[0])); //也可以用deque.add()
//        for (int i = 1, j = 0; i < preorder.length; i++) {
//            TreeNode node = new TreeNode(preorder[i]);
//            while (deque.getLast().val == postorder[j]) {
//                deque.pollLast();
//                j++;
//            }
//            if (deque.getLast().left == null)
//                deque.getLast().left = node;
//            else deque.getLast().right = node;
//            deque.offer(node); //也可以用deque.add()
//        }
//        return deque.getFirst();
//    }

//    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
//        //方法2：迭代 使用栈 2ms
//        Deque<TreeNode> stack = new LinkedList<>();
//        TreeNode root = new TreeNode(preorder[0]);
//        stack.push(root);
//        for (int i = 1, j = 0; i < preorder.length; i++) {
//            TreeNode node = new TreeNode(preorder[i]);
//            while (stack.peek().val == postorder[j]) {
//                stack.pop();
//                j++;
//            }
//            if (stack.peek().left == null)
//                stack.peek().left = node;
//            else stack.peek().right = node;
//            stack.push(node);
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
