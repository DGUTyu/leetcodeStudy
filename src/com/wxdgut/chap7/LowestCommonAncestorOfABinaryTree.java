package com.wxdgut.chap7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-04 21:27:01
 * <p>
 * 236. 二叉树的最近公共祖先 难度：中等
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1  输出：3
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4  输出：5
 * 输入：root = [1,2], p = 1, q = 2                          输出：1
 * <p>
 * 提示：
 * 树中节点数目在范围 [2, 10^5] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */

public class LowestCommonAncestorOfABinaryTree {
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        //递归写法1
//        //时间复杂度 O(N) 空间复杂度 O(N)
//        //递归终止条件
//        if (root == null) return root;
//        if (root.val == p.val || root.val == q.val) return root;
//        TreeNode left = lowestCommonAncestor(root.left, p, q);
//        TreeNode right = lowestCommonAncestor(root.right, p, q);
//        if (left == null) return right;//不可写成 if (left != null) return left;
//        if (right == null) return left;//不可写成 if (right != null) return right;
//        return root;
//    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归写法2
        //递归边界
        if (root == null || root == p || root == q) return root;
        //分解为求左子树的子问题和右子树的子问题,注意是后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //左右子树都有则返回root
        if (left != null && right != null) return root;
        //如果左右子树中有一个子问题没得到结果，则返回得到结果的子问题.
        return left != null ? left : right;
    }


//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        //递归写法3
//        TreeNode[] res = new TreeNode[1];
//        dfs(root, p, q, res);
//        return res[0];
//    }

    //递归写法3
    private int dfs(TreeNode node, TreeNode p, TreeNode q, TreeNode[] res) {
        if (node == null) return 0;
        int count = 0;
        if (node == p || node == q) count++;
        int left = dfs(node.left, p, q, res);
        int right = dfs(node.right, p, q, res);
        count += left + right;
        if (count == 2 && res[0] == null) res[0] = node;
        return count;
    }

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
