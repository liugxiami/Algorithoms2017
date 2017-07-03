package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/7/2.
 */
public class LC347TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums={1,1,1,1,2,2,2,3,3,4,4,4,4,4};
        List<Integer> res=topKFrequent(nums,2);
        res.forEach(x-> System.out.println(x));
    }
    public static List<Integer> topKFrequent(int[] nums,int k){
        List<Integer> result=new ArrayList<>();
        if(nums==null||nums.length==0||k<=0)return result;

        Map<Integer,Integer> map=new HashMap<>();
        for(Integer num:nums){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }

        PriorityQueue<Map.Entry> pq=new PriorityQueue<>(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                int num1=(int)(o1.getValue());
                int num2=(int)(o2.getValue());
                return num1-num2;
            }
        });

        for(Map.Entry entry:map.entrySet()){
            pq.offer(entry);
            if(pq.size()>k){
                pq.poll();
            }
        }
        while(!pq.isEmpty()){
            result.add(0,(int)pq.poll().getKey());
        }
        return result;
    }
}
