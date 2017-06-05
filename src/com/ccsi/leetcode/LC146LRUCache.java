package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/5/27.
 */
public class LC146LRUCache {
    private class Entry{
        int key;
        int value;
        Entry pre,next;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
            pre=null;
            next=null;
        }
    }

    HashMap<Integer,Entry> map;
    Entry root;
    Entry tail;
    int capacity;
    int count;

    public LC146LRUCache(int capacity) {
        this.map=new HashMap<>();
        this.capacity = capacity;
        this.root=null;
        this.tail=null;
        this.count=0;
    }
    public int get(int key){
        if(map.containsKey(key)){
            Entry curr=map.get(key);
            if(curr.pre==null){
                root=root.next;

            }else{
                curr.pre.next=curr.next;
            }

            if(curr.next!=null){
                curr.next.pre=curr.pre;
            }

            tail.next=curr;
            curr.pre=tail;
            curr.next=null;
            tail=curr;

            return curr.value;
        }else return -1;
    }

    public void put(int key,int value){
        if(map.containsKey(key)){
            Entry curr=map.get(key);
            if(curr.pre==null){
                root=root.next;
                root.pre=null;
            }else{
                curr.pre.next=curr.next;
            }

            if(curr.next!=null){
                curr.next.pre=curr.pre;
            }

            tail.next=curr;
            curr.pre=tail;
            curr.next=null;
            tail=curr;
        }else{
            if(root==null){
                Entry curr=new Entry(key,value);
                root=curr;
                tail=root;
                map.put(key,curr);
                count++;
            }else {
                if(count==capacity){
                    root=root.next;
                    root.pre=null;
                    count--;
                }

                Entry curr=new Entry(key,value);
                tail.next=curr;
                curr.pre=tail;
                curr.next=null;
                tail=curr;

                map.put(key,curr);
                count++;
            }
        }
    }
    public void print(){
        while(root!=null){
            System.out.println(root.value);
            root=root.next;
        }
    }
}
