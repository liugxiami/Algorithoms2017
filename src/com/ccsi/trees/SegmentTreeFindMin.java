package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/23.
 */
public class SegmentTreeFindMin {
    private class Node{
        int min;
        int li;
        int hi;
        Node left;
        Node right;

        public Node(int min, int li, int hi) {
            this.min = min;
            this.li = li;
            this.hi = hi;
        }

        public Node(int li, int hi) {
            this.min=Integer.MAX_VALUE;
            this.li = li;
            this.hi = hi;
        }
    }
    private Node root;
    private int[] numbers;
    public SegmentTreeFindMin(int[] array) {
        if(array==null||array.length==0)return;
        numbers=array;
        this.root = buildTree(array,0,array.length-1);
    }
    private Node buildTree(int[] array,int li,int hi){
        if(li==hi)return new Node(array[li],li,hi);
        int mi=li+(hi-li)/2;
        Node root=new Node(li,hi);
        root.left=buildTree(array,li,mi);
        root.right=buildTree(array,mi+1,hi);
        root.min=Math.min(root.left.min,root.right.min);
        return root;
    }

    public void update(int i,int val){
        if(i<0||i>=numbers.length)return;
        numbers[i]=val;
        update(root, i, val);
    }
    private void update(Node curr,int i,int val){
        if(curr.li ==i&&curr.hi ==i){
            curr.min=val;
        }else{
            int mi=(curr.li +curr.hi)/2;
            if(i<=mi){
                update(curr.left,i,val);
            }else update(curr.right,i,val);
            curr.min=Math.min(curr.left.min,curr.right.min);
        }
    }

    public int query(int i,int j){
        if(i<0||j<0||j>=numbers.length||i>=numbers.length)
            throw new IllegalStateException("Out of range!");
        return query(root,i,j);
    }
    private int query(Node curr,int i,int j){
        if(curr.li ==i&&curr.hi ==j)return curr.min;
        int mi=(curr.li +curr.hi)/2;
        if(i<mi&&j<=mi){
            return query(curr.left,i,j);
        }else if(i>mi&&j>mi){
            return query(curr.right,i,j);
        }else{
            return Math.min(query(curr.left,i,mi),query(curr.right,mi+1,j));
        }
    }
}
