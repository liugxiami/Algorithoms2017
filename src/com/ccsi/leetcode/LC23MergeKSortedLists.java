package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/27.
 */
public class LC23MergeKSortedLists {
    public static void main(String[] args) {

    }
    private static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode mergeLists(ListNode[] lists){
        if(lists==null||lists.length==0)return null;
        return mergeLists(lists,0,lists.length-1);
    }
    private static ListNode mergeLists(ListNode[] lists,int start,int end){
        if(start==end)return lists[start];

        int mi=start+(end-start)/2;
        ListNode list1=mergeLists(lists,start,mi);
        ListNode list2=mergeLists(lists,mi+1,end);

        ListNode list=new ListNode(0);
        ListNode dummyHead=list;
        while(list1!=null&&list2!=null){
            if(list1.val<list2.val){
                list=list1;
                list1=list1.next;
            }else{
                list=list2;
                list2=list2.next;
            }
            list=list.next;
        }

        if(list1==null){
            list.next=list2;
        }else{
            list.next=list1;
        }
        return dummyHead.next;
    }
}
