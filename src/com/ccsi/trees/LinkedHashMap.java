package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/26.
 */
public class LinkedHashMap {
    private class Entry{
        int key;
        Object value;
        Entry next;
        Entry pre;

        public Entry(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;
    int count;
    Entry[] entries;

    public LinkedHashMap() {
        this.capacity=37;
        this.count=0;
        this.entries=new Entry[capacity];
    }

    public void put(int key,Object value){
        int index=hashCode(key);

        if(count*2>=capacity){
            capacity*=2;
            restore();
        }
        if(entries[index]==null){
            entries[index]=new Entry(key,value);
            count++;
            return;
        }else{
            Entry temp=entries[index];
            while(temp.next!=null){
                if(temp.key==key){
                    temp.value=value;
                    return;
                }
                else {
                    temp=temp.next;
                }
            }
            temp.next=new Entry(key,value);
            temp.next.pre=temp;
            count++;
        }
    }
    public void remove(int key){
        if(!containsKey(key))return;
        int index=hashCode(key);
        Entry curr=positionOf(key);

        if(curr.pre==null){
            entries[index]=curr.next;
        }else{
            curr.pre.next=curr.next;
        }

        if(curr.next!=null){
            curr.next.pre=curr.pre;
        }

        count--;
    }
    public boolean containsKey(int key){
        return positionOf(key)!=null;
    }
    public Entry positionOf(int key){
        int position=hashCode(key);
        if(entries[position]==null)return null;

        Entry temp=entries[position];
        while(temp!=null){
            if(temp.key==key)return temp;
            else{
                temp=temp.next;
            }
        }
        return temp;
    }
    public int size(){
        return this.count;
    }
    public boolean isEmpty(){
        return this.count==0;
    }
    private int hashCode(int key){
        int index=(key*37+key%100/10)%capacity;
        return index;
    }
    private void restore(){
        Entry[] oldEntries=entries.clone();

        entries=new Entry[capacity];
        count=0;

        for(Entry e:entries){
            if(e!=null){
                while(e!=null){
                    put(e.key,e.value);
                    e=e.next;
                }
            }
        }
    }
}
