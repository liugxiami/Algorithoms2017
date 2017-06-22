package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/21.
 */
public class LC198HouseRobber {
    public static void main(String[] args) {
        int[] nums={2,4,1,5,3,7};
        System.out.println(rob1(nums));
    }
    public static int rob(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int len=nums.length;
        if(len==1)return nums[0];
        int[] cache=new int[len];
        cache[0]=nums[0];
        cache[1]=Math.max(nums[0],nums[1]);

        for (int i = 2; i < len; i++) {
            cache[i]=Math.max(cache[i-1],cache[i-2]+nums[i]);
        }

        return cache[len-1];
    }
    //内存优化
    public static int rob1(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int len=nums.length;
        if(len==1)return nums[0];

        int a=nums[0];
        int b=nums[0]>nums[1]?nums[0]:nums[1];

        for (int i = 2; i < len; i++) {
            int c=Math.max(nums[i]+a,b);
            a=b;
            b=c;
        }
        return b;
    }
}
