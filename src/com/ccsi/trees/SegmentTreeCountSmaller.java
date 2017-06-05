package com.ccsi.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gxliu on 2017/5/24.
 */
public class SegmentTreeCountSmaller {
    private class Node{
        int count;
        int start;
        int end;
        Node left;
        Node right;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //这个build没有初始化特征值，因为特征值需要在每次insert的时候会发生变化
    public Node build(int start,int end){
        if(start>end)return null;
        if(start==end)return new Node(start,end);
        int mi=start+(end-start)/2;
        Node root=new Node(start,end);
        root.left=build(start,mi);
        root.right=build(mi+1,end);
        return root;
    }
    //此处的insert用的是loop，而非通常的递归
    public void insert(int val,Node root){
        Node curr=root;

        while(curr!=null){
            int mi=(curr.start+curr.end)/2;    //mi在此处计算，不是在外面
            curr.count++;
            if(val<=mi)curr=curr.left;
            else curr=curr.right;
        }
    }
    //与通常的segmentTree的query相似
    public int query(int start,int end,Node root){
        if(end<root.start)return 0;
        if(start==root.start&&end==root.end)return root.count;
        int mi=(root.start+root.end)/2;
        if(end<=mi)return query(start,end,root.left);
        else if(start>mi)return query(start,end,root.right);
        return query(start,mi,root.left)+query(mi+1,end,root.right);
    }
    //主要函数，执行建树，循环插入数值，每插入一次都要查询一次，获取[最小值, 插入值-1]的区间的特征值
    public List<Integer> countSmaller(int[] nums){
        List<Integer> result=new ArrayList<>();
        if(nums==null||nums.length==0)return result;
        int len=nums.length;
        int[] aux=new int[len];

        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            max=Math.max(max,nums[i]);
            min=Math.min(min,nums[i]);
        }

        Node root=build(min,max);

        for (int i = len-1; i >=0; i--) {
            insert(nums[i],root);
            aux[i]=query(min,nums[i]-1,root);
        }

        for (int i = 0; i < len; i++) {
            result.add(aux[i]);
        }
        return result;
    }
}
