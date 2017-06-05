package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/2.
 */
public class LC128LongestConsecutiveSequence {
    private class UFElement{
        int key;
        int parent;
        int rank;
        int count;

        public UFElement(int key, int parent) {
            this.key = key;
            this.parent = parent;
            this.rank = 0;
            this.count = 1;
        }
    }

    private Map<Integer,UFElement> ufelements;
    private int numOfGroups;
    private int max=Integer.MIN_VALUE;

    public LC128LongestConsecutiveSequence() {
        this.ufelements = new HashMap<>();
        this.numOfGroups = 0;
    }

    public void makeSet(int[] array){
        if(array==null||array.length==0)return;
        for (int i = 0; i < array.length; i++) {
            ufelements.put(array[i],new UFElement(array[i],array[i]));
            this.numOfGroups++;
        }
    }

    public void union(int num1,int num2){
        int parent1=find(num1);
        int parent2=find(num2);

        if(parent1!=parent2){
            UFElement uParent1=ufelements.get(parent1);
            UFElement uParent2=ufelements.get(parent2);

            if(uParent1.rank<uParent2.rank){
                uParent1.parent=parent2;
                uParent2.count+=uParent1.count;
            }else if(uParent1.rank>uParent2.rank){
                uParent2.parent=parent1;
                uParent1.count+=uParent2.count;
            }else{
                uParent1.parent=parent2;
                uParent2.count+=uParent1.count;
                uParent2.rank++;
            }
            numOfGroups--;
            max=Math.max(max,Math.max(uParent1.count,uParent2.count));
        }
    }

    public int find(int num){
        UFElement curr=ufelements.get(num);
        UFElement temp=curr;
        while(true){
            int parent=curr.parent;
            if(parent==curr.key){
                temp.parent=parent;
                return parent;
            }

            curr=ufelements.get(parent);
        }
    }

    public int longestSeq(int[] array){
        if(array==null||array.length==0)return 0;
        makeSet(array);

        for (int i = 0; i < array.length; i++) {
            int temp=array[i];
            if(ufelements.containsKey(temp+1)){
                union(temp,temp+1);
            }

            if(ufelements.containsKey(temp-1)){
                union(temp,temp-1);
            }
        }
        return max;
    }
}
