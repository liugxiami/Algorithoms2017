package com.ccsi.sorts;

import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by gxliu on 2017/5/16.
 */
public class StringSort {
    public static void main(String[] args) {
        String[] strings={"ABC","AB","AC","BCD","AAA","BACDE","CD"};
        stringBucketSort(strings);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
    public static void stringSort(String[] strings){
        if(strings==null||strings.length<=1)return;
        sort(strings, 0, strings.length-1);
    }
    //1.quickSort
    public static void sort(String[] strings,int start,int end){
        if(start>=end)return;
        int index=partition(strings,start,end, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1=(String)o1;
                String str2=(String)o2;
                int p=0,q=0;
                while(p<str1.length()&&q<str2.length()){
                    if(str1.charAt(p)>str2.charAt(q))return 1;   //前面大，返回1
                    else if(str1.charAt(p)<str2.charAt(q))return -1;  //前面小，返回-1
                    else{
                        p++;
                        q++;
                    }
                }
                if(str1.length()==str2.length())return 0;
                return (p==str1.length())?-1:1; //谁先结束谁小，返回-1
            }
        });
        sort(strings,start,index-1);
        sort(strings,index+1,end);
    }
    private static int partition(String[] strings,int start,int end,Comparator cmp){
        if(start>=end)return start;
        int ran=new Random().nextInt(end-start)+start;
        swap(strings,start,ran);
        String flag=strings[start];
        int idx=start+1;
        for (int i = start+1; i <= end; i++) {
            if(cmp.compare(strings[i],flag)<0)swap(strings,i,idx++);
        }
        swap(strings,start,idx-1);
        return idx-1;
    }
    private static void swap(String[] str,int p,int q){
        if(p!=q){
            String temp=str[p];
            str[p]=str[q];
            str[q]=temp;
        }
    }
    //2.bucketSort
    public static void stringBucketSort(String[] strings){
        if(strings==null||strings.length<=1)return;
        bucketSort(strings,0,strings.length-1,0);
    }

    private static void bucketSort(String[] strings,int start,int end,int idx){
        if(start>=end)return;
        List<String>[] bucket=new ArrayList[27];     //声明list数组
        for (int i = 0; i < 27; i++) {               //对list初始化
            bucket[i]=new ArrayList<String>();
        }

        boolean needContinue=false;                  //加快运算
        for (int i = start; i <=end; i++) {          //入桶
            if(strings[i].length()<=idx){            //有两种情况，一是当前排序的idx超过了数组长度
                bucket[0].add(strings[i]);           //直接进0桶，也就是最前面的桶
            }else{
                bucket[strings[i].charAt(idx)-'A'+1].add(strings[i]);  //否则正常入桶
                needContinue=true;
            }

        }
        if(!needContinue)return;

        int index=0;
        for (int i = 0; i < 27; i++) {                 //出桶，全部迭代一遍，也平常的桶排序一样
            int size=bucket[i].size();
            int count=0;
            while(count<size){
                strings[start+index++]=bucket[i].get(count++);  //需要注意与元素位置，此步容易出错
            }
            if(start+index>end)break;
        }

        for (int i = 0; i < 27; i++) {
            int size=bucket[i].size();
            if(size>0){
                bucketSort(strings,start,start+size-1,idx+1);  //微排
                start+=size;                             //这步很巧妙
                bucket[i].clear();                       //每排好一个桶里的元素，及时清空，否则容易出错。
            }
        }
    }
}
