package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/30.
 */
public class LC94BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);
        List<Integer> res=inorderTraversal1(root);
        res.forEach(x-> System.out.println(x));
    }
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    private static List<Integer> result=new ArrayList<>();
    //1.regular inorder,but not good.
    public static List<Integer> inorderTraversal(TreeNode root){
        inorder(root);
        return result;
    }

    private static void inorder(TreeNode root){
        if(root==null)return;
        inorder(root.left);
        result.add(root.val);
        inorder(root.right);
    }
    //2.利用一个stack和while循环来做inorder。
    public static List<Integer> inorderTraversal1(TreeNode root){
        if(root==null)return result;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode curr=root;

        while(curr!=null||!stack.isEmpty()){ //关键步骤
            while (curr!=null){
                stack.push(curr);  //先将左子树上的元素入栈，其实并没有将null入栈。
                curr=curr.left;
            }

            curr=stack.pop();  //到头之后pop栈里最后面进去的元素。
            result.add(curr.val);
            curr=curr.right;
        }
        return result;
    }
}
