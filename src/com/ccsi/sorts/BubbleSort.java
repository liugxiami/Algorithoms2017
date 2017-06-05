package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/12.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array={2,3,6,1,9,12};
        bubbleSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void bubbleSort(int[] arr){
        if(arr==null||arr.length==0)return;
        int len=arr.length;
        boolean hasSorted=false;    //引进这个boolean参数可以改善该算法
        for (int i = len-1; i >0 ; i--) {
            hasSorted=false;
            for (int j = 0; j < i; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    hasSorted=true;
                }
            }
            if(!hasSorted)break;
        }
    }
    public static void swap(int[] a,int p,int q){
        int temp=a[p];
        a[p]=a[q];
        a[q]=temp;
    }
}
