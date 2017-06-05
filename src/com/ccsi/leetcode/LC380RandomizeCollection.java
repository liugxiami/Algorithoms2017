package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/5/28.
 */
public class LC380RandomizeCollection {
    List<Integer> list;
    Map<Integer,Set<Integer>> map;

    public LC380RandomizeCollection() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public boolean insert(int val){
        boolean existed=map.containsKey(val);
        if(!existed){
            map.put(val,new HashSet<>());
        }
        map.get(val).add(list.size());

        list.add(val);
        return !existed;
    }

    public boolean remove(int val){
        if(!map.containsKey(val))return false;

        int firstPos=map.get(val).iterator().next();

        map.get(val).remove(firstPos);
        if(map.get(val).isEmpty())map.remove(val);

        int lastNum=list.get(list.size()-1);

        list.set(firstPos,lastNum);

        if(firstPos!=list.size()-1){
            map.get(lastNum).remove(list.size()-1);
            map.get(lastNum).add(firstPos);
        }

        list.remove(list.size()-1);
        return true;
    }

    public int getRandom(){
        if(list.size()==0)return 0;
        int ran=(int)(Math.random()*list.size());
        return list.get(ran);
    }

    public int size(){
        return list.size();
    }

    public void print(){
        list.forEach(x-> System.out.print(x+" "));
    }
}
