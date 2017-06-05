package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/12.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array={2,3,6,1,9,12};
        shellSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    //insertSort的改进型
    public static void shellSort(int[] arr){
        if(arr==null||arr.length==0)return;
        int len=arr.length;
        int gap=len/2;
        while(gap>0){
            for (int i = gap; i < len; i++) {
                for (int j = i; j >0 ; j-=gap) {
                    if(j-gap>=0&&arr[j]<arr[j-gap])swap(arr,j,j-gap);
                    else break;
                }
            }
            gap/=2;
        }
    }
    public static void swap(int[] a,int p,int q){
        int temp=a[p];
        a[p]=a[q];
        a[q]=temp;
    }
}
