package com.wxdgut.chap7;

import java.util.*;

/**
 * @author Administrator
 * @program: leetcodeStudy
 * @date 2021-12-02 08:57:59
 * <p>
 * 297. 二叉树的序列化与反序列化 难度：困难
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，
 * 详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 示例 ：
 * 输入：root = [1,2,3,null,null,4,5] 输出：[1,2,3,null,null,4,5]
 * 输入：root = [1] 输出：[1]
 * 输入：root = [] 输出：[]
 * <p>
 * 提示：
 * 1.树中结点数在范围 [0, 104] 内
 * 2.-1000 <= Node.val <= 1000
 */

public class SerializeAndDeserializeBinaryTree {
    /************ 以下为官方题解 1 深度优先搜索 112ms ************/
//    //将树编码为单个字符串。
//    public String serialize(TreeNode root) {
//        //前序遍历递归序列化： 时间/空间复杂度均为O(n)
//        return rserialize(root, "");
//    }
//
//    public String rserialize(TreeNode root, String str) {
//        if (root == null) {
//            str += "None,";
//        } else {
//            str += str.valueOf(root.val) + ",";
//            str = rserialize(root.left, str);
//            str = rserialize(root.right, str);
//        }
//        return str;
//    }
//
//    //将编码数据解码到树中。
//    public TreeNode deserialize(String data) {
//        //原先的序列分割开来得到先序遍历的元素列表
//        String[] dataArray = data.split(",");
//        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
//        //前序递归遍历
//        return constructTree(dataList);
//    }
//
//    public TreeNode constructTree(List<String> dataList) {
//        if (dataList.get(0).equals("None")) {
//            dataList.remove(0); //如果当前的元素为 None，则当前为空树
//            return null;
//        }
//        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
//        dataList.remove(0);
//        root.left = constructTree(dataList);
//        root.right = constructTree(dataList);
//        return root;
//    }

    /**********以下为方法2  9ms **********/
    private String spliter = ",";
    private String FLAG = "N";

    //将树编码为单个字符串。
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        rserialize(root, sb);
        return sb.toString();
    }

    private void rserialize(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append(FLAG).append(spliter);
        else {
            sb.append(root.val).append(spliter);
            rserialize(root.left, sb);
            rserialize(root.right, sb);
        }
    }

    //将编码数据解码到树中。
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String nodeStr = nodes.remove();
        if (nodeStr.equals(FLAG)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(nodeStr));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }


    /**********以下为方法3  3ms **********/
//    private int OFFSET = 1000;
//    private String FLAG = "*";
//    int index;
//
//    //将树编码为单个字符串。
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        index = 0;
//        rserialize(root, sb);
//        return sb.toString();
//    }
//
//    private void rserialize(TreeNode root, StringBuilder sb) {
//        //all nodes fit into 4 bits.
//        //IFF we index at 0. So encode(val) = val + min(default -1000)
//        if (root == null) {
//            sb.append(FLAG);
//            return;
//        }
//        String s = Integer.toHexString(root.val + OFFSET); //OFFSET=1000
//        StringBuilder sbTemp = new StringBuilder();
//        for (int i = 0; i < 3 - s.length(); i++) sbTemp.append('0');
//        sb.append(sbTemp.append(s).toString());
//        rserialize(root.left, sb);
//        rserialize(root.right, sb);
//        return;
//    }
//
//    //将编码数据解码到树中。
//    public TreeNode deserialize(String data) {
//        if (data.charAt(index) == '*') {
//            index++;
//            return null;
//        }
//        //将字符串参数解析为第二个参数指定的基数中的有符号整数。
//        int num = Integer.parseInt(data.substring(index, index + 3), 16);
//        TreeNode root = new TreeNode(num - OFFSET); //OFFSET=1000
//        index += 3;
//        root.left = deserialize(data);
//        root.right = deserialize(data);
//        return root;
//    }

    /**********以下为方法4：官方题解 1 的改版  1ms **********/
//    //将树编码为单个字符串。
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        rserialize(root, sb);
//        return sb.toString();
//    }
//
//    private void rserialize(TreeNode root, StringBuilder sb) {
//        //递归终止条件
//        if (root == null) {
//            sb.append('n');
//            return;
//        }
//        //当前逻辑
//        sb.append(root.val).append(',');
//        //下沉到下一层
//        rserialize(root.left, sb);
//        rserialize(root.right, sb);
//    }
//
//    //将编码数据解码到树中。
//    private int index = 0;
//
//    public TreeNode deserialize(String data) {
//        return constructTree(data);
//    }
//
//    private TreeNode constructTree(String data) {
//        //递归终止条件
//        if (data.charAt(index) == 'n') {
//            index++;
//            return null;
//        }
//        //当前逻辑
//        boolean isNegative = false; //是否是负数
//        if (data.charAt(index) == '-') {
//            isNegative = true;
//            index++;
//        }
//        int num = 0;
//        while (index < data.length() && Character.isDigit(data.charAt(index))) {
//            num = num * 10 + data.charAt(index) - '0'; //将-与，之间的字符转化为数字
//            index++;
//        }
//        index++; //跳过 ','
//        if (isNegative) num = -num; //将数字转为负数形式
//        TreeNode root = new TreeNode(num);
//        //下沉到下一层
//        root.left = constructTree(data);
//        root.right = constructTree(data);
//        return root;
//    }


    /************* 以下为官方题解 1测试代码 *************/
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        root.left = root2;
        root.right = root3;
        root3.left = root4;
        root3.right = root5;
        SerializeAndDeserializeBinaryTree ser = new SerializeAndDeserializeBinaryTree();
        SerializeAndDeserializeBinaryTree deser = new SerializeAndDeserializeBinaryTree();
        System.out.println("**** 将树编码为单个字符串 ****");
        String s = ser.serialize(root);
        System.out.println(s);
        System.out.println("**** 将编码数据解码到树中 ****");
        TreeNode ans = deser.deserialize(s);
        List<Integer> orderRes = new ArrayList<>();
        preOrder(ans, orderRes); //前序遍历 1,2,3,4,5
        //inOrder(ans, orderRes); //中序遍历 2,1,4,3,5
        //postOrder(ans, orderRes); //后序遍历 2,4,5,3,1
        for (Integer item : orderRes)
            System.out.print(item + ",");
    }

    //前序遍历递归函数
    private static void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    //中序遍历递归函数
    private static void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    //后序遍历递归函数
    private static void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }
}