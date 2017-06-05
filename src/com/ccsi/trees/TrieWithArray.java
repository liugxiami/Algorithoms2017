package com.ccsi.trees;

/**
 * Created by gxliu on 2017/5/24.
 */
public class TrieWithArray {
    private class Node {
        Node[] dic;
        String word;

        public Node() {
            this.dic = new Node[26];
        }
    }

    private Node root;

    public TrieWithArray() {
        this.root = new Node();
    }

    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        int len = word.length();
        word = word.toLowerCase();
        Node curr = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (curr.dic[c - 'a'] == null) {
                curr.dic[c - 'a'] = new Node();
            }
            curr = curr.dic[c - 'a'];
        }
        curr.word=word;
    }

    public boolean query(String word) {
        if (word == null || word.length() == 0) return false;
        int len = word.length();
        word = word.toLowerCase();
        Node curr = root;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (curr.dic[c - 'a'] == null) return false;
            curr = curr.dic[c - 'a'];
        }
        return curr.word != null;
    }

    public boolean startWith(String word) {
        if (word == null || word.length() == 0) return false;
        int len=word.length();
        word=word.toLowerCase();
        Node curr=root;
        for (int i = 0; i < len; i++) {
            char c=word.charAt(i);
            if(curr.dic[c-'a']==null)return false;
            curr=curr.dic[c-'a'];
        }
        return true;
    }
}