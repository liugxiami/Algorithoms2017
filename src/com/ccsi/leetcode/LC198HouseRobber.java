package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/21.
 */
public class LC198HouseRobber {
    public static void main(String[] args) {
        int[] nums={2,4,1,5,3,7};
        System.out.println(rob3(nums));
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
    //recursion
    public static int rob2(int[] nums){
        if(nums==null||nums.length==0)return 0;
        return helper(nums,nums.length-1);
    }
    private static int helper(int[] nums,int index){
        if(index==0)return nums[nums[0]];
        if(index==1)return Math.max(nums[0],nums[1]);

        return Math.max(helper(nums,index-1),nums[index]+helper(nums,index-2));
    }
    //recursion+cache
    public static int rob3(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int len=nums.length;
        int[] cache=new int[len];
        helper(nums,len-1,cache);
        return cache[len-1];
    }
    private static int helper(int[] nums,int index,int[] cache){
        if(index==0)cache[0]=nums[0];
        if(index==1)cache[1]=Math.max(nums[0],nums[1]);

        if(cache[index]!=0)return cache[index];
        cache[index]=Math.max(helper(nums,index-1,cache),helper(nums,index-2,cache)+nums[index]);
        return cache[index];
    }
}
