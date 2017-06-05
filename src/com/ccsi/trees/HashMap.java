package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/26.
 */
public class HashMap {
    private class Entry{
        int key;
        Object value;

        public Entry(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    Entry[] entries;
    int capacity;
    int count;

    public HashMap() {
        this.capacity = 37;
        this.entries = new Entry[capacity];
        this.count = 0;
    }

    public void put(int key,Object value){
        int index=hashCode(key);

        if(count*2>=capacity){
            capacity*=2;
            resize();
        }

        while(true){
            index%=capacity;
            if(entries[index]==null){
                entries[index]=new Entry(key,value);
                count++;
                return;
            }else{
                Entry temp=entries[index];
                if(temp.key==key){
                    temp.value=value;
                    return;
                }
                else index++;
            }
        }
    }
    public void remove(int key){
        if(!containsKey(key))return;
        int index=indexOf(key);
        entries[index]=null;
        count--;
    }
    public boolean containsKey(int key){
        return indexOf(key)!=-1;
    }
    private int indexOf(int key){
        int index=hashCode(key);

        int start=index;
        while(true){

            if(entries[index]!=null&&entries[index].key==key)return index;
            else index++;

            index%=capacity; //当index==capacity时的情况要注意，该语句如果放在65行就用出错，没有index=0的情况了。
            if(start==index)return -1;
        }
    }
    public int size(){
        return this.count;
    }

    private int hashCode(int key){
        int index=(key*37+key%100/10)%capacity;
        return index;
    }

    private void resize(){
        Entry[] oldEntries=entries.clone();

        entries=new Entry[capacity];
        count=0;    //resize时要将count归零
        for(Entry e:oldEntries){
            if(e!=null)put(e.key,e.value); //在put每个entry时，count都会自增。
        }
    }

    public boolean isEmpty(){
        return this.count==0;
    }
}
