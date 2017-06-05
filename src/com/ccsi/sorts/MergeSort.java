package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/12.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array={2,3,5,1,12,4};
        mergeSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void mergeSort(int[] arr){
        if(arr==null||arr.length==0)return;
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr,int start,int end){
        if(start>=end)return;
        int mid=start+(end-start)/2;
        sort(arr,start,mid);
        sort(arr,mid+1,end);
        merge(arr,start,end);
    }

    private static void merge(int[] arr,int start,int end){
        if(start>=end)return;
        int[] aux=arr.clone();

        int mid=start+(end-start)/2;
        int p=start,q=mid+1;

        for (int i = start; i <=end ; i++) {
            if(p>mid){
                arr[i]=aux[q++];
            }else if(q>end){
                arr[i]=aux[p++];
            }else if(aux[p]<aux[q]){
                arr[i]=aux[p++];
            }else{
                arr[i]=aux[q++];
            }
        }
    }
}
