package com.wxdgut.chap6;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-11-23 11:14:59
 * <p>
 * 94. 二叉树的中序遍历 难度：简单
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * <p>
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * <p>
 * 示例 ：
 * 输入：root = [1,null,2,3] 输出：[1,3,2]
 * 说明：1为根节点，null表示1没有左节点，2表示1的右节点，3表示2的左节点
 * <p>
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        //方法1：递归
        //时间复杂度：O(n) 空间复杂度：O(n)
//        List<Integer> res = new ArrayList<>();
//        inOrder(root, res);
//        return res;

        //方法2：迭代
        //时间复杂度：O(n) 空间复杂度：O(n)
        //方法1的递归函数我们也可以用迭代的方式实现，
        //两种方式是等价的，区别在于递归的时候隐式地维护了一个栈，
        //而我们在迭代的时候需要显式地将这个栈模拟出来，其他都相同。
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        //Stack<TreeNode> stack = new Stack(); //0ms 36.7MB
        //当我们把Deque作为Stack使用时，注意只调用push()/pop()/peek()方法，
        //不要调用addFirst()/removeFirst()/peekFirst()方法，那样会破坏栈的本质。
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;

        //方法3：Morris 中序遍历
        //时间复杂度：O(2n)=O(n) 空间复杂度：O(1)
        // Morris 遍历算法是另一种遍历二叉树的方法，能将非递归的中序遍历空间复杂度降为 O(1)。
        // Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 x）：
        //1.如果 x 无左孩子，先将 x 的值加入答案数组，再访问 x 的右孩子，即 x = x.right。
        //2.如果 x 有左孩子，则找到 x 左子树上最右的节点
        //（即左子树中序遍历的最后一个节点，x 在中序遍历中的前驱节点），记为predecessor。
        //根据predecessor 的右孩子是否为空，进行如下操作。
        // ①如果predecessor 的右孩子为空，则将其右孩子指向x，访问x的左孩子，即 x = x.left。
        // ②如果predecessor 的右孩子不为空，则此时其右孩子指向x，
        //  说明已经遍历完 x 的左子树，将predecessor 的右孩子置空，
        //  将 x 的值加入答案数组，然后访问 x 的右孩子，即 x = x.right。
        //3.重复上述操作，直至访问完整棵树。
//        List<Integer> res = new ArrayList<>();
//        TreeNode predecessor = null;
//        while (root != null) {
//            if (root.left != null) {
//                //predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
//                predecessor = root.left;
//                while (predecessor.right != null && predecessor.right != root) {
//                    predecessor = predecessor.right;
//                }
//                //让 predecessor 的右指针指向 root，继续遍历左子树
//                if (predecessor.right == null) {
//                    predecessor.right = root;
//                    root = root.left;
//                }
//                // 说明左子树已经访问完了，我们需要断开链接
//                else {
//                    res.add(root.val);
//                    predecessor.right = null;
//                    root = root.right;
//                }
//            }
//            // 如果没有左孩子，则直接访问右孩子
//            else {
//                res.add(root.val);
//                root = root.right;
//            }
//        }
//        return res;

    }

    //方法1的递归函数：中序遍历
    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    //扩展：前序遍历的递归函数：
    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    //扩展：后序遍历的递归函数：
    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

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
