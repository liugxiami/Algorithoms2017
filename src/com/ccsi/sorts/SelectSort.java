package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/12.
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array={2,3,6,1,9,12};
        selectSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void selectSort(int[] a){
        if(a==null||a.length==0)return;
        int len=a.length;
        for(int i=0;i<len-1;i++){
            for (int j = i; j <len ; j++) {
                if(a[i]>a[j])swap(a,i,j);
            }
        }
    }
    public static void swap(int[] array,int p,int q){
        int temp=array[p];
        array[p]=array[q];
        array[q]=temp;
    }
}
