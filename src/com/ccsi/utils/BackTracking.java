package com.ccsi.utils;

/**
 * Created by gxliu on 2017/6/18.
 */
public class BackTracking {
    public static void main(String[] args) {
        int[] weights={2,3,4,5,6};
        int[] valume= {1,4,3,6,8};
        System.out.println(backTracking(12,weights,valume));
    }
    private static int Max=0;
    public static int backTracking(int total,int[] weights,int[] valume){
        helper(total,weights,valume,0,0);
        return Max;
    }
    //total为剩余容积（为valume做准备），curr为当前的总重量（为weights做准备），index为当前的层数（也是下标）
    private static void helper(int total,int[] weights,int[] valume,int curr,int index){
        if(total<=0||index>=weights.length){ //到头的两种情况，一是容量不够了，一是没有元素了。
            Max=Math.max(Max,curr);   //到头了，需要做点事-----将最大值给取出来。
            return;    //这个return是必须的，容易漏掉。
        }
        //第一种情况，不放进去，那么只要index+1就可以了。
        helper(total,weights,valume,curr,index+1);
        //第二种情况，先判断背包里面的容积还够不够，够就放进去，这时容积减小，重量增加，index+。
        if(total-valume[index]>=0){
            helper(total-valume[index],weights,valume,curr+weights[index],index+1);
        }
    }
}
