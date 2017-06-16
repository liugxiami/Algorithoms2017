package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/15.
 */
public class LC55JumpGame {
    public boolean canJump(int[] array){
        if(array==null||array.length==0)return true;
        if(array.length==1&&array[0]==0)return false;

        int len=array.length;
        int max=0;
        for (int i = 0; i < len; i++) {
            int curr=i+array[i];
            if(max<i)return false;
            if(max==0&&array[i]==0)return false;

            max=Math.max(max,curr);
            if(max>=len)return true;
        }
        return false;
    }
    public boolean canJumpTim(int[] array){
        if(array==null||array.length==0)return true;
        if(array.length==1&&array[0]==0)return false;

        int len=array.length;
        int max=array[0];
        for (int i = 1; i < max; i++) {
            max=Math.max(max,array[i]+i);
            if(max>=len-1)return true;
        }
        return false;
    }

    public static void main(String[] args) {
        LC55JumpGame game=new LC55JumpGame();
        int[] array={2,3,1,1,4};
        int[] array1={3,2,1,0,4};
        System.out.println(game.canJumpTim(array));
    }
}
