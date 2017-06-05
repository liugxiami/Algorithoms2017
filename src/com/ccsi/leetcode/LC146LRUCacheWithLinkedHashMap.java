package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/5/27.
 */
public class LC146LRUCacheWithLinkedHashMap {
    LinkedHashMap<Integer,Integer> map;
    int capacity;
    int count;

    public LC146LRUCacheWithLinkedHashMap(int capacity) {
        this.map = new LinkedHashMap();
        this.capacity = capacity;
        this.count=0;
    }
    public int get(int key){
        if(map.containsKey(key)){
            int value=map.get(key);
            map.remove(key);
            map.put(key,value);
            return value;
        }else return -1;
    }
    public void put(int key,int value){
        if(map.containsKey(key)){
            map.remove(key);
            map.put(key,value);
        }else{
            if(count==capacity){
                map.remove(map.entrySet().iterator().next().getKey());
                count--;
            }
            map.put(key,value);
            count++;
        }
    }
    public void print(){
        for(Integer i:map.keySet()){
            System.out.println(map.get(i));
        }
    }
}
