package com.ccsi.trees;

import com.ccsi.sorts.ListNode;
import com.sun.org.apache.regexp.internal.RE;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by gxliu on 2017/5/18.
 */
public class TreeGears {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8,9};
        TreeNode root=buildBST(array);
        printTree(root);
        System.out.println();
        printTreeByLayer(root);
        System.out.println(countCBT(root));
    }
    public static void printTree(TreeNode root){
        if(root==null)return;
        printTree(root.left);
        System.out.print(root.val+" ");
        printTree(root.right);
    }
    public static void printTreeByLayer(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> outerQueue=new LinkedList<>();
        outerQueue.offer(root);
        while(!outerQueue.isEmpty()){
            Queue<TreeNode> innerQueue=new LinkedList<>();
            while(!outerQueue.isEmpty()){
                TreeNode curr=outerQueue.poll();
                System.out.print(curr.val+" ");
                if(curr.left!=null)innerQueue.offer(curr.left);
                if(curr.right!=null)innerQueue.offer(curr.right);
            }
            System.out.println();
            outerQueue=innerQueue;
        }
    }
    //Count nodes
    public static int countNodes(TreeNode root){
        if(root==null)return 0;
        return countNodes(root.left)+countNodes(root.right)+1;
    }
    //count full-binary-tree nodes
    public static int countFBT(TreeNode root){
        if(root==null)return 0;
        TreeNode curr=root;
        int count=0;
        while(curr!=null){
            count++;
            curr=curr.left;
        }

        return (1<<count)-1;
    }
    //count complete-binary-tree nodes
    public static int countCBT(TreeNode root){
        if(root==null)return 0;

        TreeNode leftTree=root.left;
        int leftTreeHeight=0;
        while(leftTree!=null){
            leftTreeHeight++;
            leftTree=leftTree.left;
        }

        TreeNode rightTree=root.right;
        int rightTreeHeight=0;
        while(rightTree!=null){
            rightTreeHeight++;
            rightTree=rightTree.right;
        }

        if(leftTreeHeight==rightTreeHeight){
            return (1<<leftTreeHeight+1)-1;
        }else{
            return countCBT(root.left)+countCBT(root.right)+1;
        }
    }
    //recursion find all paths
    public static void backTracking(List<List<Integer>> bag, Stack<Integer> path,TreeNode root){
        path.push(root.val);
        if(root.left==null&&root.right==null){
            bag.add(new ArrayList<>(path));
        }else{
            if(root.left!=null)backTracking(bag,path,root.left);
            if(root.right!=null)backTracking(bag,path,root.right);
        }
        path.pop();
    }
    //BFS find all paths
    public static List<List<TreeNode>> findAllPaths(TreeNode root){
        if(root==null)return null;
        List<List<TreeNode>> result=new ArrayList<>();
        List<TreeNode> init=new ArrayList<>();

        Queue<List<TreeNode>> pathQueue=new LinkedList<>();
        Queue<TreeNode> nodeQueue=new LinkedList<>();

        init.add(root);
        pathQueue.offer(init);
        nodeQueue.offer(root);

        while(!nodeQueue.isEmpty()){
            List<TreeNode> currPath=pathQueue.poll();
            TreeNode currNode=nodeQueue.poll();

            if(currNode.left==null&&currNode.right==null){
                result.add(currPath);
            }else{
                if(currNode.left!=null){
                    List<TreeNode> newPath=new LinkedList<>(currPath);
                    newPath.add(currNode.left);
                    pathQueue.offer(newPath);
                    nodeQueue.offer(currNode.left);
                }
                if(currNode.right!=null){
                    List<TreeNode> newPath=new LinkedList<>(currPath);
                    newPath.add(currNode.right);
                    pathQueue.offer(newPath);
                    nodeQueue.offer(currNode.right);
                }
            }
        }
        return result;
    }
    //BFS find all paths(modified, save one Queue)
    public static List<List<TreeNode>> findAllPaths1(TreeNode root){
        if(root==null)return null;
        List<List<TreeNode>> result=new ArrayList<>();
        List<TreeNode> init=new ArrayList<>();

        Queue<List<TreeNode>> pathQueue=new LinkedList<>();

        init.add(root);
        pathQueue.offer(init);

        while(!pathQueue.isEmpty()){
            List<TreeNode> currPath=pathQueue.poll();
            TreeNode currNode=currPath.get(currPath.size()-1);

            if(currNode.left==null&&currNode.right==null){
                result.add(currPath);
            }else{
                if(currNode.left!=null){
                    List<TreeNode> newPath=new ArrayList<>(currPath);
                    newPath.add(currNode.left);
                    pathQueue.offer(newPath);
                }
                if(currNode.right!=null){
                    List<TreeNode> newPath=new ArrayList<>(currPath);
                    newPath.add(currNode.right);
                    pathQueue.offer(newPath);
                }
            }
        }
        return result;
    }

    public static TreeNode buildBST(int[] array){
        if(array==null||array.length==0)return null;
        return buildBST(array,0,array.length-1);
    }
    private static TreeNode buildBST(int[] array,int li,int hi){
        if(li>hi)return null;
        int mid=(hi-li)/2+li;
        TreeNode curr=new TreeNode(array[mid]);
        curr.left=buildBST(array,li,mid-1);
        curr.right=buildBST(array,mid+1,hi);
        return curr;
    }

    public static boolean searchBST(TreeNode root,int target){
        if(root==null) System.out.println("Tree Empty.");
        TreeNode curr=root;
        while(curr!=null){
            if(curr.val==target)return true;
            else if(curr.val>target)curr=curr.left;
            else curr=curr.right;
        }
        return false;
    }
    public static boolean searchBST1(TreeNode curr,int target){
        if(curr==null)return false;
        if(curr.val==target)return true;
        else if(target<curr.val)return searchBST(curr.left,target);
        else return searchBST(curr.right,target);
    }

    public static void preorder_serialize(List<Integer> result,TreeNode root){
        if(root==null)return;
        result.add(root.val);
        preorder_serialize(result,root.left);
        preorder_serialize(result,root.right);
    }
    public static void inorder_serialize(List<Integer> result,TreeNode root){
        if(root==null)return;
        inorder_serialize(result,root.left);
        result.add(root.val);
        inorder_serialize(result,root.right);
    }
    public static void postorder_serialize(List<Integer> result,TreeNode root){
        if(root==null)return;
        postorder_serialize(result,root.left);
        postorder_serialize(result,root.right);
        result.add(root.val);
    }

    public static boolean isBST(TreeNode root){
        if(root==null)return true;
        return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    private static boolean isBST(TreeNode curr,int low,int high){
        if(curr==null)return true;

        if(curr.val<=low||curr.val>=high)return false;
        return isBST(curr.left,low,curr.val)&&isBST(curr.right,curr.val,high);
    }

    public static void BFS(TreeNode root){
        if(root==null)return;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode temp=queue.poll();
            System.out.println(temp.val);
            if(temp.left!=null)queue.offer(temp.left);
            if(temp.right!=null)queue.offer(temp.right);
        }
    }

    public static void DFS(TreeNode curr){
        if(curr==null)return;
        System.out.println(curr.val);
        DFS(curr.left);
        //System.out.println(curr.val);
        DFS(curr.right);
        //System.out.println(curr.val);
    }
    //pre-order DFS with loop
    public static void DFSLoop(TreeNode root){
        if(root==null)return;
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp=stack.pop();
            System.out.println(temp.val);
            if(temp.right!=null)stack.push(temp.right);
            if(temp.left!=null)stack.push(temp.left);
        }
    }

    public static int height(TreeNode root){
        if(root==null)return -1;
        return Math.max(height(root.left),height(root.right))+1;
    }

    public static int findMax(TreeNode root){
        if(root==null) return Integer.MIN_VALUE;  //注意，这里的null值当量是Integer.MIN_VALUE;
        return Math.max(Math.max(findMax(root.left),findMax(root.right)),root.val);
    }

    public static int treeSum(TreeNode root){
        if(root==null)return 0;
        return treeSum(root.left)+treeSum(root.right)+root.val;
    }

    public static boolean isSameTree(TreeNode leftTree,TreeNode rightTree){
        if(leftTree==null&&rightTree==null)return true;
        if(leftTree==null||rightTree==null)return false;
        if(leftTree.val!=rightTree.val)return false;
        return isSameTree(leftTree.left,rightTree.left)&&isSameTree(leftTree.right,rightTree.right);
    }

    public static boolean isSameTreeBFS(TreeNode leftTree,TreeNode rightTree){
        Queue<TreeNode> leftQueue=new LinkedList<>();
        Queue<TreeNode> rightQueue=new LinkedList<>();

        leftQueue.offer(leftTree);
        rightQueue.offer(rightTree);

        while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()){
            TreeNode leftTemp=leftQueue.poll();
            TreeNode rightTemp=rightQueue.poll();

            if(leftTemp==null&&rightTemp==null)return true;
            if(leftTemp==null||rightTemp==null)return false;
            if(leftTemp.val!=rightTemp.val)return false;
            else{
                leftQueue.offer(leftTemp.left);
                rightQueue.offer(rightTemp.left);
                leftQueue.offer(leftTemp.right);
                rightQueue.offer(rightTemp.right);
            }
        }
        return false;
    }

    public static boolean isSymmetryDFS(TreeNode root){
        if(root==null)return true;
        TreeNode leftTree=root.left;
        TreeNode rightTree=root.right;
        return isSymmetryDFS(leftTree,rightTree);
    }
    private static boolean isSymmetryDFS(TreeNode leftTree,TreeNode rightTree){
        if(leftTree==null&&rightTree==null)return true;
        if(leftTree==null||rightTree==null)return false;
        if(leftTree.val!=rightTree.val)return false;
        else{
            return isSymmetryDFS(leftTree.left,rightTree.right)&&isSymmetryDFS(leftTree.right,rightTree.left);
        }
    }

    public static boolean isSymmetryBFS(TreeNode root){
        if(root==null)return true;
        Queue<TreeNode> leftQueue=new LinkedList<>();
        Queue<TreeNode> rightQueue=new LinkedList<>();

        leftQueue.offer(root.left);
        rightQueue.offer(root.right);

        while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()){
            TreeNode leftTemp=leftQueue.poll();
            TreeNode rightTemp=rightQueue.poll();

            if(leftTemp==null&&rightTemp==null)return true;
            if(leftTemp==null||rightTemp==null)return false;
            if(leftTemp.val!=rightTemp.val)return false;
            else{
                leftQueue.offer(leftTemp.left);
                rightQueue.offer(rightTemp.right);
                leftQueue.offer(leftTemp.right);
                rightQueue.offer(rightTemp.left);
            }
        }
        return false;
    }
}
