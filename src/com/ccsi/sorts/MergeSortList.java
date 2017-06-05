package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/13.
 */
public class MergeSortList {
    private ListNode head;
    private ListNode tail;

    public MergeSortList() {
        this.head = null;
        this.tail = null;
    }

    public void add(int val){
        if(head==null){
            head=new ListNode(val);
            tail=head;
        }else{
            tail.next=new ListNode(val);
            tail=tail.next;
        }
    }

    public void printNode(){
        System.out.println("Print LinkedList from head to tail:");
        if(head==null) System.out.println("The List is empty.");
        else{
            ListNode p=head;
            while(p!=null){
                p.displayNode();
                p=p.next;
            }
        }
    }

    public ListNode getHead(){
        return head;
    }

    public ListNode mergeSortList(ListNode originalHead){
        if(originalHead==null||originalHead.next==null)return originalHead;
        ListNode a=originalHead;
        ListNode b=originalHead.next;   //由于后面截断a用的是next，须从next开始，否则前后两半分布不均匀
        while(b!=null&&b.next!=null){
            originalHead=originalHead.next;
            b=b.next.next;
        }
        b=originalHead.next;
        originalHead.next=null;

        return merge(mergeSortList(a),mergeSortList(b));
    }

    private ListNode merge(ListNode a,ListNode b){
        if(a==null&&b==null)return null;
        if(a==null&&b!=null)return b;
        if(b==null&&a!=null)return a;
        ListNode dummyHead=new ListNode(Integer.MAX_VALUE);
        ListNode c=dummyHead;
        while(a!=null&&b!=null){
            if(a.val<b.val){
                c.next=a;
                a=a.next;
            }else{
                c.next=b;
                b=b.next;
            }
            c=c.next;
        }
        c.next=(a==null)?b:a;
        head=dummyHead.next;
        tail=head;
        while(tail!=null)tail=tail.next;

        return dummyHead.next;
    }
}
