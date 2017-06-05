package com.ccsi.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gxliu on 2017/5/18.
 */
public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root=BuildTree.buildTopToButtom();
        layerTraversal(root);
    }
    public static void inorderDFS(TreeNode curr){
        if(curr==null)return;
        System.out.println(curr.val);
        inorderDFS(curr.left);
        inorderDFS(curr.right);
    }
    public static void preorderDFS(TreeNode curr){
        if(curr==null)return;
        preorderDFS(curr.left);
        System.out.println(curr.val);
        preorderDFS(curr.right);
    }
    public static void postorderDFS(TreeNode curr){
        if(curr==null)return;
        postorderDFS(curr.left);
        postorderDFS(curr.right);
        System.out.println(curr.val);
    }
    public static void BFS(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode curr=queue.poll();
            System.out.println(curr.val);
            if(curr.left!=null)queue.offer(curr.left);
            if(curr.right!=null)queue.offer(curr.right);
        }
    }
    public static void layerTraversal(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> outQueue=new LinkedList<>();
        outQueue.offer(root);
        while(!outQueue.isEmpty()){
            Queue<TreeNode> innerQueue=new LinkedList<>();
            while(!outQueue.isEmpty()){
                TreeNode curr=outQueue.poll();
                System.out.print(curr.val+" ");
                if(curr.left!=null)innerQueue.offer(curr.left);
                if(curr.right!=null)innerQueue.offer(curr.right);
            }
            outQueue=innerQueue;
            System.out.println();
        }
    }
}
