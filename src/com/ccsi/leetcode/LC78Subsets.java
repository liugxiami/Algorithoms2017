package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/7/9.
 */
public class LC78Subsets {
    public static void main(String[] args) {
        int[] nums={1,2,3,4};
        List<List<Integer>> result=subsets1(nums);
        for (int i = 0; i < result.size(); i++) {
            result.get(i).forEach(x-> System.out.print(x+" "));
            System.out.println();
        }
    }
    //method 1: backtracking
    public static List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        if(nums==null||nums.length==0)return res;
        Stack<Integer> path=new Stack<>();
        subsets(nums,0,nums.length,path,res);
        return res;
    }
    private static void subsets(int[] nums,int index,int max,Stack<Integer> path,List<List<Integer>> res){
        if(index>=max){
            res.add(new ArrayList<>(path));
            return;
        }

        subsets(nums,index+1,max,path,res);

        path.push(nums[index]);
        subsets(nums,index+1,max,path,res);
        path.pop();
    }
    //method 2:bit-maniputation
    public static List<List<Integer>> subsets1(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        if(nums==null||nums.length==0)return result;

        int length=nums.length;
        for (int i = 0; i < Math.pow(2, length); i++) {
            int temp=i;
            List<Integer> curr=new ArrayList<>();
            int index=0;
            while(temp>0){
                if((temp&1)==1){
                    curr.add(nums[index]);
                }
                index++;
                temp>>=1;
            }
            result.add(curr);
        }
        return result;
    }
}
