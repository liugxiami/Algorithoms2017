package com.ccsi.leetcode;

import com.ccsi.Main;

/**
 * Created by gxliu on 2017/6/15.
 */
public class LC134GasStation {
    public int canCompleteCircuit(int[] gas,int[] cost){
        if(gas==null||gas.length==0)return -1;
        int len=gas.length;

        if(len==1)return gas[0]>cost[0]?0:-1;

        int[] delta=new int[len];
        int total=0;
        for (int i = 0; i < len; i++) {
            delta[i]=gas[i]-cost[i];
            total+=delta[i];
        }

        if(total<0)return -1;

        total=0;
        int start=len;
        for (int i = 0; i < len; i++) {
            total+=delta[i];
            if(total<0){
                total=0;
                start=len;
            }else{
                start= Math.min(start,i);
            }
        }
        return start;
    }

    public static void main(String[] args) {
        int[] gas={3,2,1,4,5};
        int[] cost={2,3,2,3,5};
        LC134GasStation gs=new LC134GasStation();
        System.out.println(gs.canCompleteCircuit(gas,cost));
    }
}
