package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/17.
 */
//bucketSort的变种
public class CountingSort {
    public static void main(String[] args) {
        int[] arr={2,3,1,1,5,6,3,4,2};
        countingSort(arr);
        for (int i:arr
                ) {
            System.out.println(i);
        }
    }
    public static void countingSort(int[] arr){
        if(arr==null||arr.length<=1)return;
        int max=100;
        int[] bucket=new int[max];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        for (int i = 1; i < max; i++) {
            bucket[i]+=bucket[i-1];
        }

        int[] aux=arr.clone();
        for (int i = 0; i < aux.length; i++) {
            int val=aux[i];
            arr[--bucket[val]]=val;  //还是很难理解，注意，bucket[val]里面存储的是比val小的数的个数，那么也就是val的排名
        }
    }
}
