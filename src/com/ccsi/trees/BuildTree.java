package com.ccsi.trees;


/**
 * Created by gxliu on 2017/5/18.
 */
public class BuildTree {
    public static TreeNode buildTopToButtom(){
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        //root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right.left=new TreeNode(6);
        //root.right.right=new TreeNode(7);
        return root;
    }
    public static TreeNode buildBottomUp(){
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        TreeNode node2=new TreeNode(2,node4,node5);
        TreeNode node3=new TreeNode(3,node6,node7);
        TreeNode root=new TreeNode(1,node2,node3);
        return root;
    }
}
