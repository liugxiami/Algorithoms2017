package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/22.
 */
public class LC70ClimbingStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs3(9));
    }
    public static int climbStairs(int n){
        if(n<=0)return -1;
        if(n==1) return 1;
        if(n==2) return 2;

        int[] cache=new int[n+1];
        cache[1]=1;
        cache[2]=2;
        for (int i = 3; i <=n; i++) {
            cache[i]=cache[i-1]+cache[i-2];
        }
        return cache[n];
    }
    //内存优化
    public static int climbStairs1(int n){
        if(n<=0)return -1;
        if(n==1)return 1;
        if(n==2)return 2;

        int a=1;
        int b=2;
        for (int i = 3; i <=n; i++) {
            int c=a+b;
            a=b;
            b=c;
        }
        return b;
    }
    //recursion
    public static int climbStairs2(int n){
        if(n<=0)return -1;
        return helper(n,n);
    }
    private static int helper(int n,int index){
        if(index==1)return 1;
        if(index==2)return 2;
        return helper(n,index-1)+helper(n,index-2);
    }
    //recursion+buffer
    public static int climbStairs3(int n){
        if(n<=0)return -1;
        int[] buffer=new int[n+1];
        helper(n,n,buffer);
        return buffer[n];
    }
    private static int helper(int n,int index,int[] buffer){
        if(index==1)buffer[1]=1;
        if(index==2)buffer[2]=2;

        if(buffer[index]!=0)return buffer[index];
        buffer[n]=helper(n,index-1,buffer)+helper(n,index-2,buffer);
        return buffer[n];
    }
}
