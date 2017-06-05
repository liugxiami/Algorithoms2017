package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/12.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array={2,3,6,1,9,12};
        insertSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void insertSort(int[] arr){
        if(arr==null||arr.length==0)return;
        int len=arr.length;
        for (int i = 1; i < len-1; i++) {
            for (int j = i; j >0; j--) {
                if(arr[j]<arr[j-1])swap(arr,j,j-1);
            }
        }
    }
    public static void swap(int[] a,int p,int q){
        int temp=a[p];
        a[p]=a[q];
        a[q]=temp;
    }
}
