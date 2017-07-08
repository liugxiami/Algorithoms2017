package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/7/7.
 */
public class LC69SqrtX {
    public static void main(String[] args) {
        System.out.println(sqrt1(15));
    }
    public static int sqrt(int x){
        int result=1;
        while(result*result<x){
            result++;
        }
        return result-1;
    }
    public static int sqrt2(int x){
        if(x<0)throw new IllegalStateException("x should be greater than 0.");
        int start=1;
        int end=x/2+1;

        while(start<=end){ //必须是<=
            int mid=start+(end-start)/2;
            if(mid*mid==x)return mid;
            else if(mid*mid>x){
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return end;
    }
    //结果不对
    public static int sqrt1(int x){
        int mid=x/2+1;
        return helper(x,1,mid);
    }

    private static int helper(int x,int start,int end){
        if(start==end)return start;

        int mid=start+(end-start)/2;
        if(mid*mid==x)return mid;
        else if(mid*mid<x){
            return helper(x,mid+1,end);
        }
        else return helper(x,start,mid-1);
    }
}
