package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/25.
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }
    private static final boolean RED=true;
    private static final boolean BLACK=false;

    private Node root;

    public RedBlackTree() {
        root=null;
    }
    //判断一个node是否是red
    private boolean isRed(Node x){
        if(x==null)return BLACK;   //所有的null节点都是black的。必须有这一个
        return x.color==RED;
    }
    //节点的size
    public int size(Node x){
        if(x==null)return 0;
        return x.size;
    }
    //树的size
    public int size(){
        return size(root);
    }
    //判断这颗树是否为空
    private boolean isEmpty(){
        return root==null;
    }
    //获取相应key对应的value
    public Value get(Key key){
        if(key==null)throw new IllegalArgumentException("Argument to get() is null.");
        return get(key,root);
    }
    //注意，这是BST，用循环就能找到value
    public Value get(Key key,Node x){
        Node curr=x;
        while(curr!=null){
            int cmp=key.compareTo(curr.key);
            if(cmp==0)return curr.value;
            else if(cmp<0)curr=curr.left;
            else curr=curr.right;
        }
        return null;
    }
    //利用get()函数来写containsKey
    public boolean containsKey(Key key){
        return get(key)!=null;
    }
    //关键
    public void put(Key key,Value value){
        if(key==null) throw new IllegalArgumentException("Argument to put() is null");
        if(value==null) {
            remove(key,root); //这个函数写的不准确。
            return;
        }

        root=put(root,key,value);
        root.color=BLACK;
    }
    private Node put(Node curr,Key key,Value value){
        if(curr==null)return curr=new Node(key,value,RED,1);

        int cmp=key.compareTo(curr.key);
        if(cmp==0)curr.value=value;
        else if(cmp<0)curr.left=put(curr.left,key,value);
        else curr.right=put(curr.right,key,value);
        //因为这是颗左偏的BST
        //只要出现右子树是red，直接左转
        if(isRed(curr.right)&&!isRed(curr.left))curr=rotateLeft(curr);
        //不能出现两个连续的红节点，左左，那么右转。（不会出现右右情况）
        if(isRed(curr.left)&&isRed(curr.left.left))curr=rotateRight(curr);
        //转完后，如果出现两子树都红了，反一下颜色。
        if(isRed(curr.left)&&isRed(curr.right)) flipColor(curr);

        curr.size=size(curr.left)+size(curr.right)+1;

        return curr;
    }

    private Node rotateLeft(Node h){
        Node x=h.right;
        h.right=x.left;
        x.left=h;

        x.color=h.color;
        h.color=RED;
        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;
        return x;

    }

    private Node rotateRight(Node h){
        Node x=h.left;
        h.left=x.right;
        x.right=h;

        x.color=h.color;
        h.color=RED;

        x.size=h.size;
        h.size=size(h.left)+size(h.right)+1;
        return x;
    }
    private void flipColor(Node h){
        h.color=!h.color;
        h.left.color=!h.left.color;
        h.right.color=!h.right.color;
    }
    //该remove不对，如果节点不是叶子节点，直接赋值null是不行的。
    private void remove(Key key,Node x){
        Node curr=x;
        while(curr!=null){
            int cmp=key.compareTo(curr.key);
            if(cmp==0)curr=null;
            else if(cmp<0)curr=curr.left;
            else curr=curr.right;
        }
    }
}
