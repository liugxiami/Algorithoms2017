package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/13.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public void displayNode(){
        System.out.println("["+this.val+"]");
    }
}
