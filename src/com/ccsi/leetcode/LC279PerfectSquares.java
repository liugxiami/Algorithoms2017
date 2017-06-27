package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/25.
 */
public class LC279PerfectSquares {
    public static void main(String[] args) {
        System.out.println(perfectSquares(5));
    }

    //F(n)=min(F(n-1^2)+1,F(n-2^2)+1,....F(n-m^2)+1),m=Math.sqrt(n).
    public static int perfectSquares(int num){
        if(num<=0)return -1;
        if(num==1)return 1;

        int[] cache=new int[num+1];
        cache[0]=0;
        cache[1]=1;

        for (int i = 2; i <=num; i++) {
            int min=i;     //用一个参数做缓存，这里不能直接用cache[i],最大就是i。
            for (int j = 1; j*j <= i ; j++) {
                min=Math.min(cache[i-j*j]+1,min);
            }
            cache[i]=min;
        }
        return cache[num];
    }
}
