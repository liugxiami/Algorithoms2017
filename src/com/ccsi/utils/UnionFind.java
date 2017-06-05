package com.ccsi.utils;

import java.util.*;

/**
 * Created by gxliu on 2017/5/29.
 */
public class UnionFind {
    private class UFElement{
        String name;
        String parent;

        public UFElement(String name, String parent) {
            this.name = name;
            this.parent = parent;
        }
        public boolean equals(UFElement other){
            return this.name.equals(other.name);
        }
    }

    private Map<String,UFElement> map;

    public UnionFind() {
        this.map = new HashMap<>();
    }

    private void makeSet(String[][] stars){
        if(stars==null||stars.length==0||stars[0].length==0)return;

        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[0].length; j++) {
                String star=stars[i][j];
                if(!map.containsKey(star)){
                    map.put(star,new UFElement(star,star));
                }
            }
        }
    }

    private void union(String star1,String star2){
        String parent1=find(star1);
        String parent2=find(star2);
        if(!parent1.equals(parent2)){
            map.get(parent1).parent=parent2;
        }
    }

    private String find(String star){
        UFElement curr=map.get(star);
        while(true){
            String parentName=curr.parent;
            if(parentName.equals(curr.name))return parentName;
            curr=map.get(parentName);
        }
    }

    public int getCount(){
        int count=0;
        for(Map.Entry<String,UFElement> entry:map.entrySet()){
            if(entry.getKey().equals(entry.getValue().parent))count++;
        }
        return count;
    }

    public Map<String,List<String>> getSets(){
        Map<String,List<String>> result=new HashMap<>();
        for(Map.Entry<String,UFElement> entry:map.entrySet()){
            String parent=find(entry.getKey());
            if(!result.containsKey(parent)){
                result.put(parent,new ArrayList<>());
            }
            result.get(parent).add(entry.getKey());
        }
        return result;
    }

    public void action(String[][] stars){
        if(stars==null||stars.length==0||stars[0].length==0)return;
        makeSet(stars);
        for (int i = 0; i < stars.length; i++) {
            union(stars[i][0],stars[i][1]);
        }
    }
}
