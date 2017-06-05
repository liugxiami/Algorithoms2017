package com.ccsi.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gxliu on 2017/5/24.
 */
public class Trie {
    private class Node{
        String word;
        Map<Character,Node> dict;

        public Node() {
            this.dict = new HashMap<>();
        }
    }

    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String word){
        if(word==null||word.length()==0)return;
        Node curr=root;
        int len=word.length();
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            if(!curr.dict.containsKey(c)){
                curr.dict.put(c,new Node());
            }
            curr=curr.dict.get(c);
        }
        curr.word=word;
    }

    public boolean query(String word){
        if(word==null||word.length()==0)return false;
        Node curr=root;
        int len=word.length();
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            if(!curr.dict.containsKey(c)){
                return false;
            }else curr=curr.dict.get(c);
        }
        return curr.word!=null;
    }

    public boolean startWith(String word){
        if(word==null||word.length()==0)return false;
        int len=word.length();
        Node curr=root;
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            if(!curr.dict.containsKey(c)){
                return false;
            }else curr=curr.dict.get(c);
        }
        return true;
    }
}
