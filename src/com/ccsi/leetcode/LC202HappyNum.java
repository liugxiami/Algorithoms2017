package com.ccsi.leetcode;

import java.util.HashSet;

/**
 * Created by gxliu on 2017/5/29.
 */
public class LC202HappyNum {
    public static void main(String[] args) {
        int num=19;
        System.out.println(happyNum(num));
    }

    public static boolean happyNum(int num){
        if(num<=0)return false;
        if(num==1)return true;
        HashSet<Integer> set=new HashSet<>();

        while(true){
            System.out.print(num+" ");
            if(num==1)return true;
            if(!set.add(num))return false;

            int next=0;
            while(num>0){
                int ones=num%10;
                next+=ones*ones;
                num/=10;
            }

            num=next;
        }
    }
}
