package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/5/28.
 */
public class LC380RandomimzeSet {
    List<Integer> list;
    Map<Integer,Integer> map;  //key=num,value=position in list;

    public LC380RandomimzeSet() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public boolean insert(int val){
        if(!map.containsKey(val)){
            map.put(val,list.size());
            list.add(val);
            return true;
        }
        return false;
    }

    public boolean remove(int val){
        if(map.containsKey(val)){
            int position=map.get(val);
            map.remove(val);
            list.set(position,list.get(list.size()-1));
            list.remove(list.size()-1);
            return true;
        }
        return false;
    }
    public int getRandom(){
        int ran=(int)(Math.random()*list.size());
        return list.get(ran);
    }

    public int size(){
        return list.size();
    }
}
