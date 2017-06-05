package com.ccsi.leetcode;

import java.util.Random;

/**
 * Created by gxliu on 2017/5/16.
 */
public class LC75SortColors {
    public static void main(String[] args) {
        int[] colors={1,2,0,2,1,1,2,2,0,0,2,1,0,2};
        sortColors2(colors);
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
        }
    }
    //1.bulkSort
    public static void sortColors(int[] arr){
        if(arr==null||arr.length<=1)return;
        int len=arr.length;

        int[] bulks=new int[3];
        for (int i = 0; i < len; i++) {
            bulks[arr[i]]++;
        }

        int idx=0;
        for (int i = 0; i < 3; i++) {
            int count=bulks[i];
            while(count-->0){
                arr[idx++]=i;
            }
        }
    }
    //2.quickSort
    public static void sortColors1(int[] arr){
        if(arr==null||arr.length<=1)return;
        sort(arr,0,arr.length-1);
    }
    private static void sort(int[] arr,int start,int end){
        if(start>=end)return;
        int index=partition(arr,start,end);
        sort(arr,start,index-1);
        sort(arr,index+1,end);
    }
    private static int partition(int[] arr,int start,int end){
        if(start>=end)return start;
        int ran=new Random().nextInt(end-start)+start;
        swap(arr,start,ran);
        int flag=arr[start];
        int idx=start+1;
        for (int i = start+1; i <=end; i++) {
            if(arr[i]<=flag)swap(arr,i,idx++);
        }
        swap(arr,start,idx-1);
        return idx-1;
    }
    private static void swap(int[] arr,int p,int q){
        if(p!=q){
            int temp=arr[p];
            arr[p]=arr[q];
            arr[q]=temp;
        }
    }
    //3.loop 双指针
    public static void sortColors2(int[] arr){
        if(arr==null||arr.length<=1)return;
        int len=arr.length;
        int start=0,end=len-1;
        int idx=0;
        while(idx<=end){
            if(arr[idx]==0){
                swap(arr,idx,start);
                start++;
            }else if(arr[idx]==2){
                swap(arr,idx,end);
                end--;
            }else{
                idx++;
            }
        }
    }
}
