package com.ccsi.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by gxliu on 2017/5/29.
 */
public class LC217ContainDuplicate {
    public static void main(String[] args) {
        int[] array={1,2,4,5,7,3,9,20};
        System.out.println(containDuplicate(array));
    }

    public static boolean containDuplicate(int[] array){
        if(array==null||array.length<=1)return false;
        HashSet set=new HashSet();
        for (int i = 0; i < array.length; i++) {
            if(!set.add(array[i]))return true;
        }
        return false;
    }

    public static boolean containDuplicate1(int[] array){
        if(array==null||array.length<=1)return false;
        Arrays.sort(array);
        for (int i = 1; i < array.length; i++) {
            if(array[i]==array[i-1])return true;
        }
        return false;
    }
}
