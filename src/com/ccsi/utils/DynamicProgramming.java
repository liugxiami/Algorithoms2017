package com.ccsi.utils;

/**
 * Created by gxliu on 2017/6/18.
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        int[] weights={2,3,4,5,6};
        int[] volume={1,4,3,6,8};
        System.out.println(dynamicProgramming(10,weights,volume));
    }

    public static int dynamicProgramming(int total,int[] weights,int[] volume){
        int len=weights.length;
        //缓存
        int[][] cache=new int[len][total+1];
        //初始化（也就是第一行数据）
        for (int i = 0; i <= total; i++) {
            if(i-volume[0]>=0)cache[0][i]=weights[0];
        }
        //实现递推方程式
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= total; j++) {
                if(j-volume[i]>=0){
                    cache[i][j]=Math.max(cache[i-1][j],cache[i-1][j-volume[i]]+weights[i]);
                }else{
                    cache[i][j]=cache[i-1][j];
                }
            }
        }
        return cache[len-1][total];
    }
}
