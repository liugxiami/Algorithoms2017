package com.ccsi.utils;

import java.util.*;

/**
 * Created by gxliu on 2017/5/31.
 */
public class UnionFind2 {
    private class UFElement{
        String name;
        String parent;
        int rank;
        int count;

        public UFElement(String name, String parent) {
            this.name = name;
            this.parent = parent;
            this.rank = 0;
            this.count = 1;
        }
    }
    private Map<String,UFElement> ufelements;
    private int groups;
    private int max;

    public UnionFind2() {
        this.ufelements = new HashMap<>();
        this.groups = 0;
        this.max=Integer.MIN_VALUE;
    }

    public void makeSets(String[][] stars){
        if(stars==null||stars.length==0||stars[0].length==0)return;
        for (int i = 0; i < stars.length; i++) {
            for (int j = 0; j < stars[0].length; j++) {
                String star=stars[i][j];
                if(!ufelements.containsKey(star)){
                    ufelements.put(star,new UFElement(star,star));
                    groups++;
                }
            }
        }
    }

    public void union(String star1,String star2){
        String parent1=find(star1);
        String parent2=find(star2);

        if(!parent1.equals(parent2)){
            UFElement Uparent1=ufelements.get(parent1);
            UFElement Uparent2=ufelements.get(parent2);

            if(Uparent1.rank<Uparent2.rank){
                Uparent1.parent=parent2;
                Uparent2.count+=Uparent1.count;
            }else if(Uparent1.rank>Uparent2.rank){
                Uparent2.parent=parent1;
                Uparent1.count+=Uparent2.count;
            }else{
                Uparent1.parent=parent2;
                Uparent2.count+=Uparent1.count;
                Uparent2.rank++;
            }
            groups--;
            max=Math.max(max,Math.max(Uparent1.count,Uparent2.count));
        }
    }

    public String find(String star){
        UFElement curr=ufelements.get(star);
        UFElement ele=curr;
        while(true){
            String parent=curr.parent;
            if(parent.equals(curr.name)){
                ele.parent=parent;
                return parent;
            }
            curr=ufelements.get(parent);
        }
    }

    public int getGroups(){
        return this.groups;
    }

    public int getMaxGroup(){
        return this.max;
    }

    public Map<String,List<String>> getSets(){
        Map<String,List<String>> result=new HashMap<>();
        for(Map.Entry<String,UFElement> entry:ufelements.entrySet()){
            String curr=entry.getKey();
            String parent=find(curr);
            if(!result.containsKey(parent)){
                result.put(parent,new ArrayList<>());
            }
            result.get(parent).add(curr);
        }
        return result;
    }

    public void action(String[][] stars){
        if(stars==null||stars.length==0||stars[0].length==0)return;
        makeSets(stars);

        for (int i = 0; i < stars.length; i++) {
            String star1=stars[i][0];
            String star2=stars[i][1];

            union(star1,star2);
        }
    }
}
