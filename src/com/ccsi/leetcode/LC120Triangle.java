package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/25.
 */
public class LC120Triangle {
    public static void main(String[] args) {
        List<List<Integer>> triangle=new ArrayList<>();
        List<Integer> curr=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            triangle.add(new ArrayList<>());
        }
        curr.add(2);
        triangle.get(0).addAll(curr);
        curr.clear();
        curr.add(3);
        curr.add(4);
        triangle.get(1).addAll(curr);
        curr.clear();
        curr.add(6);
        curr.add(5);
        curr.add(7);
        triangle.get(2).addAll(curr);
        curr.clear();
        curr.add(4);
        curr.add(1);
        curr.add(8);
        curr.add(3);
        triangle.get(3).addAll(curr);
        System.out.println(minTriangle1(triangle));
    }

    public static int minTriangle(List<List<Integer>> triangle){
        if(triangle==null||triangle.size()==0)return 0;
        int len=triangle.size();
        if(len==1)return triangle.get(0).get(0);

        int[][] cache=new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <=i; j++) {
                if(i==0&&j==0)cache[i][j]=triangle.get(0).get(0);
                else if(j==0)cache[i][j]=cache[i-1][j]+triangle.get(i).get(0);
                else if(j==i)cache[i][j]=cache[i-1][j-1]+triangle.get(i).get(j);
                else cache[i][j]=Math.min(cache[i-1][j-1],cache[i-1][j])+triangle.get(i).get(j);
            }
        }

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min=Math.min(cache[len-1][i],min);
        }
        return min;
    }
    //内存优化
    public static int minTriangle1(List<List<Integer>> triangle){
        if(triangle==null||triangle.size()==0)return 0;
        int len=triangle.size();

        if(len==1)return triangle.get(0).get(0);
        int[] pre=new int[len];

        pre[0]=triangle.get(0).get(0);

        for (int i = 1; i < len; i++) {
            int[] curr=new int[len];
            for (int j = 0; j <= i; j++) {
                if(j==0)curr[j]=pre[j]+triangle.get(i).get(0);
                else if(i==j)curr[j]=pre[j-1]+triangle.get(i).get(j);
                else curr[j]=Math.min(pre[j-1],pre[j])+triangle.get(i).get(j);
            }
            pre=curr;
        }

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min=Math.min(min,pre[i]);
        }
        return min;
    }
}
