package com.ccsi.sorts;

import java.util.Random;

/**
 * Created by gxliu on 2017/5/13.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array={2,3,5,1,12,4};
        quickSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
    public static void quickSort(int[] arr){
        if(arr==null||arr.length==0)return;
        sort(arr,0,arr.length-1);
    }
    private static void sort(int[] arr,int start,int end){
        if(start>=end)return;
        int idx=partition1(arr,start,end);
        sort(arr,start,idx-1);
        sort(arr,idx+1,end);
    }
    //method 1
    private static int partition(int[] arr,int start,int end){
        if(start>=end)return start;
        //int ran=(int)((Math.random())*(end-start)+start);
        int ran=new Random().nextInt(end-start)+start;
        int pivot=arr[ran];
        int idx=start+1;
        swap(arr,start,ran);
        for (int i = start+1; i <=end ; i++) {
            if(arr[i]<pivot)swap(arr,i,idx++);
        }
        swap(arr,start,idx-1);
        return idx-1;
    }
    //快慢指针
    private static int partition1(int[] arr,int start,int end){
        if(start>=end)return start;
        int ran=new Random().nextInt(end-start)+start;
        int pivot=arr[ran];
        swap(arr,start,ran);

        int p=start,q=end+1;
        while(p<=q){
            while(++p<=end&&arr[p]<pivot);
            while(--q>=start&&arr[q]>pivot);
            if(p<q)swap(arr,p,q);
            else break;
        }
        swap(arr,start,q);
        return q;
    }
    private static void swap(int[] arr,int p,int q){
        int temp=arr[p];
        arr[p]=arr[q];
        arr[q]=temp;
    }
}
