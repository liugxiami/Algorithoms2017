package com.ccsi.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by gxliu on 2017/5/18.
 */
public class SerializeAndDeserialzieTree {
    public static void main(String[] args) {
        TreeNode root=BuildTree.buildTopToButtom();
        /*int[][] matrix=new int[2][];
        matrix=serializeTree(root);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        TreeNode newTree=deSerialize(matrix);
        System.out.println(isSameTreeBFS(root,newTree));*/
        int[] result=serializedTreeBFS(root);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        TreeNode newTree=deSerializeBFS(result);
        System.out.println(isSameTreeBFS(root,newTree));
    }
    public static int[][] serializeTree(TreeNode root){
        if(root==null)return null;
        int[][] result=new int[2][];
        List<Integer> pre=new ArrayList<>();
        preOrder(pre,root);
        List<Integer> in=new ArrayList<>();
        inOrder(in,root);
        result[0]=listToArray(pre);
        result[1]=listToArray(in);
        return result;
    }
    private static void preOrder(List<Integer> result,TreeNode root){
        if(root==null)return;
        result.add(root.val);
        preOrder(result,root.left);
        preOrder(result,root.right);
    }
    private static void inOrder(List<Integer> result,TreeNode root){
        if(root==null)return;
        inOrder(result,root.left);
        result.add(root.val);
        inOrder(result,root.right);
    }
    private static int[] listToArray(List<Integer> list){
        if(list==null||list.size()==0)return null;
        int[] arr=new int[list.size()];
        int idx=0;
        for(Integer i:list){
            arr[idx++]=i;
        }
        return arr;
    }
    //base on DFS
    public static TreeNode deSerialize(int[][] matrix){
        if(matrix==null||matrix[0].length==0)return null;
        int[] pre=matrix[0];
        int[] in=matrix[1];

        TreeNode root=innerDeSerialize(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    private static TreeNode innerDeSerialize(int[] pre,int preStart,int preEnd,int[] in,int inStart,int inEnd){
        if(preStart>preEnd)return null;
        int flag=pre[preStart];

        TreeNode root=new TreeNode(flag);
        int i=inStart;
        for (; i <= inEnd; i++) {
            if(in[i]==flag)break;
        }
        root.left=innerDeSerialize(pre,preStart+1,i-inStart+preStart ,in,inStart,i-1);
        root.right=innerDeSerialize(pre,i-inStart+preStart+1 , preEnd,in,i+1,inEnd);

        return root;
    }
    public static int[] serializedTreeBFS(TreeNode root){
        if(root==null)return null;
        List<Integer> result=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        result.add(root.val);

        while(!queue.isEmpty()){
            TreeNode temp=queue.poll();
            if(temp.left==null){
                result.add(Integer.MIN_VALUE);
            }else{
                result.add(temp.left.val);
                queue.offer(temp.left);
            }

            if(temp.right==null){
                result.add(Integer.MIN_VALUE);
            }else{
                result.add(temp.right.val);
                queue.offer(temp.right);
            }
        }
        return listToArray(result);
    }
    public static TreeNode deSerializeBFS(int[] array){
        if(array==null||array.length==0)return null;
        TreeNode root=new TreeNode(array[0]);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        int idx=1;
        while(!queue.isEmpty()&&idx<array.length){
            TreeNode temp=queue.poll();
            if(array[idx]==Integer.MIN_VALUE){
                temp.left=null;
            }else{
                temp.left=new TreeNode(array[idx]);
                queue.offer(temp.left);
            }

            idx++;
            if(array[idx]==Integer.MIN_VALUE){
                temp.right=null;
            }else{
                temp.right=new TreeNode(array[idx]);
                queue.offer(temp.right);
            }
            idx++;
        }
        return root;
    }
    //Base on DFS
    public static boolean isSameTree(TreeNode leftTree,TreeNode rightTree){
        if(leftTree==null&&rightTree==null)return true;
        if(leftTree==null||rightTree==null)return false;
        if(leftTree.val!=rightTree.val)return false;
        else{
            return isSameTree(leftTree.left,rightTree.left)&&isSameTree(leftTree.right,rightTree.right);
        }
    }
    //Base on BFS
    public static boolean isSameTreeBFS(TreeNode leftTree,TreeNode rightTree){
        Queue<TreeNode> leftQueue=new LinkedList<>();
        Queue<TreeNode> rightQueue=new LinkedList<>();

        leftQueue.offer(leftTree);
        rightQueue.offer(rightTree);
        while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()){
            TreeNode tempLeft=leftQueue.poll();
            TreeNode tempRight=rightQueue.poll();

            if(tempLeft==null&&tempRight==null)return true;
            if(tempLeft==null||tempRight==null)return false;
            if(tempLeft.val!=tempRight.val)return false;
            else{
                leftQueue.offer(tempLeft.left);
                rightQueue.offer(tempRight.left);
                leftQueue.offer(tempLeft.right);
                rightQueue.offer(tempRight.right);
            }
        }
        return true;
    }
}
