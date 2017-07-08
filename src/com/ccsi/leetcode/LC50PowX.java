package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/7/7.
 */
public class LC50PowX {
    public static void main(String[] args) {
        System.out.println(myPow(2,-3));
    }
    public static double myPow(double x,int n){
        if(x==0||x==1)return x;
        if(n==0)return 1;
        if(n==1)return x;
        if(x<0){
            if(n>0){
                if(n%2==1)return helper(-x,n)*(-1);
                else return helper(-x,n);
            }else{
                if((-n)%2==1)return 1/helper(-x,-n)*(-1);
                else return 1/helper(-x,-n);
            }
        }else{
            if(n>0){
                return helper(x,n);
            }else{
                return 1/helper(x,-n);
            }
        }
    }
    private static double helper1(double x,int n){
        int temp=n;
        double result=1.0;
        while(temp-->0){
            result*=x;
        }
        return result;
    }
    private static double helper(double x,int n){
        if(n==1)return x;
        int mid=n/2;
        double temp=helper(x,mid);
        if(n%2==0)return temp*temp;
        else return temp*temp*x;
    }
}
