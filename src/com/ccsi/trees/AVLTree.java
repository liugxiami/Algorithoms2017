package com.ccsi.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gxliu on 2017/5/19.
 */
public class AVLTree {
    private class AVLNode{
        Comparable element;
        public AVLNode left;
        public AVLNode right;
        int height;

        public AVLNode(Comparable element, AVLNode left, AVLNode right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height=Math.max(height(this.left),height(this.right))+1;
        }
    }

    public AVLNode root;
    public AVLTree() {
        this.root=null;
    }

    public int height(AVLNode t){
        return t==null?-1:t.height;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void printTree(){
        if(isEmpty()) System.out.println("Tree is empty.");
        printTree(root);
        System.out.println();
        layerPrintTree(root);
    }
    private void printTree(AVLNode r){
        if(r==null)return;
        printTree(r.left);
        System.out.print(r.element+" ");
        printTree(r.right);
    }
    private void layerPrintTree(AVLNode t){
        Queue<AVLNode> outQueue=new LinkedList<>();
        outQueue.offer(t);
        while(!outQueue.isEmpty()){
            Queue<AVLNode> innerQueue=new LinkedList<>();
            while(!outQueue.isEmpty()){
                AVLNode temp=outQueue.poll();
                System.out.print(temp.element+" ");
                if(temp.left!=null)innerQueue.offer(temp.left);
                if(temp.right!=null)innerQueue.offer(temp.right);
            }
            System.out.println();
            outQueue=innerQueue;
        }
    }
    private AVLNode rotateWithLeft(AVLNode root){
        AVLNode newRoot=root.left;
        root.left=newRoot.right;
        newRoot.right=root;
        root.height=Math.max(height(root.left),height(root.right))+1;
        newRoot.height=Math.max(height(newRoot.left),height(newRoot.right))+1;
        return newRoot;
    }
    private AVLNode rotateWithRight(AVLNode root){
        AVLNode newRoot=root.right;
        root.right=newRoot.left;
        newRoot.left=root;
        root.height=Math.max(height(root.left),height(root.right))+1;
        newRoot.height=Math.max(height(newRoot.left),height(newRoot.right))+1;
        return newRoot;
    }
    private AVLNode doubleRotateWithLeft(AVLNode root){
        root.left=rotateWithRight(root.left);
        return rotateWithLeft(root);
    }
    private AVLNode doubleRotateWithRight(AVLNode root){
        root.right=rotateWithLeft(root.right);
        return rotateWithRight(root);
    }
    //insert
    public void add(Comparable x){
        root=insert(root,x);
    }
    private AVLNode insert(AVLNode curr,Comparable x){
        if(curr==null){
            curr=new AVLNode(x,null,null);
        }else if(x.compareTo(curr.element)<0){
            curr.left=insert(curr.left,x);
            if(height(curr.left)-height(curr.right)==2){
                if(x.compareTo(curr.left.element)<0){
                    curr=rotateWithLeft(curr);
                }else{
                    curr=doubleRotateWithLeft(curr);
                }
            }
        }else if(x.compareTo(curr.element)>0){
            curr.right=insert(curr.right,x);
            if(height(curr.right)-height(curr.left)==2){
                if(x.compareTo(curr.right.element)>0){
                    curr=rotateWithRight(curr);
                }else{
                    curr=doubleRotateWithRight(curr);
                }
            }
        }else;
        curr.height=Math.max(height(curr.left),height(curr.right))+1;
        return curr;
    }
    public Comparable findMax(){
        return findMax(root).element;
    }
    private AVLNode findMax(AVLNode curr){
        if(curr==null)return null;
        while(curr.right!=null){
            curr=curr.right;
        }
        return curr;
    }
    public Comparable findMin(){
        return findMin(root).element;
    }
    private AVLNode findMin(AVLNode curr){
        if(curr==null)return curr;
        while(curr.left!=null){
            curr=curr.left;
        }
        return curr;
    }
}
