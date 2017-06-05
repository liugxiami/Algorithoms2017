package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/15.
 */
public class BulkSort {
    public static void main(String[] args) {
        int[] arr={2,3,1,1,5,6,3,4,2};
        bulkSort(arr);
        for (int i:arr
             ) {
            System.out.println(i);
        }
    }
    public static void bulkSort(int[] arr){
        if(arr==null||arr.length<=1)return;
        int len=arr.length;
        int range=10;

        int[] bulk=new int[range];
        for (int i = 0; i < len; i++) {
            bulk[arr[i]]++;
        }

        int idx=0;
        for (int i = 0; i < range; i++) {
            int count=bulk[i];
            while(count-->0){
                arr[idx++]=i;
            }
        }
    }
}
