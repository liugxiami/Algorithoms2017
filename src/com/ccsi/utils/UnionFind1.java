package com.ccsi.utils;

import java.util.*;

/**
 * Created by gxliu on 2017/5/30.
 */
public class UnionFind1 {
    private class UFElement{
        String name;
        String parent;
        int rank;

        public UFElement(String name, String parent) {
            this.name = name;
            this.parent = parent;
            this.rank = 0;
        }
    }

    private Map<String,UFElement> map;
    private int count;

    public UnionFind1() {
        this.map = new HashMap<>();
        this.count = 0;
    }

    public void makeSets(String[][] stars){
        if(stars==null||stars.length==0||stars[0].length==0)return;
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[0].length; j++) {
                String curr=stars[i][j];
                if(!map.containsKey(curr)){
                    map.put(curr,new UFElement(curr,curr));
                    count++;
                }
            }
        }
    }

    public void union(String star1,String star2){
        String parent1=find(star1);
        String parent2=find(star2);

        if(!parent1.equals(parent2)){
            UFElement UParent1=map.get(parent1);
            UFElement UParent2=map.get(parent2);

            if(UParent1.rank<UParent2.rank){   //按执归并
                UParent1.parent=parent2;
            }else if(UParent1.rank>UParent2.rank){
                UParent2.parent=parent1;
            }else{
                UParent1.parent=parent2;
                UParent2.rank++;
            }
            count--;
        }
    }

    public String find(String star){
        UFElement curr=map.get(star);
        UFElement ele=curr;
        while(true){
            String parent=curr.parent;
            if(parent.equals(curr.name))return parent;
            ele.parent=parent;   //路径压缩
            curr=map.get(parent);
        }
    }

    public int getCount(){
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
        makeSets(stars);
        for (int i = 0; i < stars.length; i++) {
            union(stars[i][0],stars[i][1]);
        }
    }
}
