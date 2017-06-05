package com.ccsi.tests;

import java.util.Random;

/**
 * Created by gxliu on 2017/5/16.
 */
public class BoysAndGirlsQuickSort {
    public static void main(String[] args) {
        int[] boys={3,5,7,6,9,1,2,8,4};
        int[] girls={9,4,5,3,2,1,6,7,8};
        boyGirlSort(boys,girls);
        for (int i = 0; i < boys.length; i++) {
            System.out.println(boys[i]);
        }
    }
    private static void boyGirlSort(int[] boys,int[] girls){
        if(boys==null||girls==null||boys.length!=girls.length)return;

        sort(boys,girls,0,boys.length-1);
    }
    private static void sort(int[] boys,int[] girls,int start,int end){
        if(start>=end)return;
        int index=partition(boys,girls,start,end);
        sort(boys,girls,start,index-1);
        sort(boys,girls,index+1,end);
    }

    //注意：既然是一一配对的，那么pivot在两数组中的位置必然是一样的。
    private static int partition(int[] boys,int[] girls,int start,int end){
        if(start>=end)return start;
        int ran=new Random().nextInt(end-start)+start;
        swap(boys,start,ran);
        int flag=boys[start];
        int idx=start;
        int gidx=start;
        for (int i = start; i <=end ; i++) {
            if(compare(girls[i],flag)<=0){
                swap(girls,i,idx++);
                if(compare(girls[idx-1],flag)==0){   //注意这步，需要记录等于flag的index，准备后面的交换
                    gidx=idx-1;                      //与常规的quickSort不同的地方在此
                }
            }
        }
        swap(girls,gidx,idx-1);

        flag=girls[idx-1];
        idx=start+1;
        for (int i = start+1; i <=end ; i++) {
            if(compare(boys[i],flag)<0)swap(boys,i,idx++);
        }
        swap(boys,start,idx-1);
        return idx-1;
    }
    private static int compare(int a,int b){
        if(a>b)return 1;
        else if(a<b)return -1;
        else return 0;
    }
    private static void swap(int[] arr,int p,int q){
        if(p!=q){
            int temp=arr[p];
            arr[p]=arr[q];
            arr[q]=temp;
        }
    }
}
