package com.ccsi.sorts;

/**
 * Created by gxliu on 2017/5/17.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr={2,3,1,1,5,6,3,4,2};
        heapSort(arr);
        for (int i:arr) {
            System.out.println(i);
        }
    }
    public static void heapSort(int[] arr){
        if(arr==null||arr.length<=1)return;
        int len=arr.length;

        int mid=(len-1)/2;
        for (int i = mid; i >=0 ; i--) {          //建堆，从中间开始往前，因为中间之后必定没有后代
            max_heapify(arr,i,len-1);
        }

        for (int i = len-1; i >0 ; i--) {
            swap(arr,i,0);                     //将最大值放到最后，此时堆被破坏了，需要重新建堆
            max_heapify(arr,0,i-1);    //循环往返排好序
        }
    }
    private static void max_heapify(int[] arr,int start,int end){
        int dad=start;
        int son=2*start+1;

        while(son<=end){
            if(son+1<=end&&arr[son+1]>arr[son])son++;   //如果两孩子都在，比较两孩子大小
            if(arr[dad]>arr[son])return;                //父亲大则不变
            else{                                       //儿子大则交换，并且更新其后代
                swap(arr,dad,son);
                dad=son;
                son=2*dad+1;
            }
        }
    }

    private static void swap(int[] arr,int p,int q){
        if(p!=q){
            int temp=arr[p];
            arr[p]=arr[q];
            arr[q]=temp;
        }
    }
}
