package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/23.
 */
public class SegmentTreeRangeSum {
    private class Node{
        int sum;
        int li;
        int hi;
        Node left;
        Node right;

        public Node(int sum, int li, int hi) {
            this.sum = sum;
            this.li = li;
            this.hi = hi;
        }
        public Node(int li,int hi){
            this.sum=0;
            this.li=li;
            this.hi=hi;
        }
    }

    private Node root;
    private int[] numbers;

    public SegmentTreeRangeSum(int[] array) {
        if(array==null||array.length==0)root=null;
        numbers=array;
        this.root = buildTree(array,0,array.length-1);
    }
    private Node buildTree(int[] array,int li,int hi){
        if(li==hi)return new Node(array[li],li,hi);

        int mi=li+(hi-li)/2;
        Node root=new Node(li,hi);
        root.left=buildTree(array,li,mi);
        root.right=buildTree(array,mi+1,hi);
        root.sum=root.left.sum+root.right.sum;
        return root;
    }

    public void update(int i,int val){
        if(i<0||i>=numbers.length)throw new IllegalStateException("Out of range.");
        int delta=val-numbers[i];
        numbers[i]=val;
        update(root,i,delta);
    }
    private void update(Node curr,int i,int delta){
        if(curr.li==curr.hi){
            curr.sum+=delta;
            return; //需要return 没有这个return会出错。
        }

        int mi=(curr.li+curr.hi)/2;
        if(i<=mi){
            update(curr.left,i,delta);
        }else update(curr.right,i,delta);

        curr.sum+=delta;
    }

    public int query(int li,int hi){
        if(li<0||li<0||li>numbers.length-1||hi>numbers.length-1)throw new IllegalStateException("Out of range.");
        return query(root,li,hi);
    }
    private int query(Node curr,int li,int hi){
        if(curr.li==li&&curr.hi==hi)return curr.sum;

        int mi=(curr.li+curr.hi)/2;
        if(li<=mi&&hi<=mi)return query(curr.left,li,hi);
        else if(li>mi&&hi>mi)return query(curr.right,li,hi);
        else{
            return query(curr.left,li,mi)+query(curr.right,mi+1,hi);
        }
    }
}
