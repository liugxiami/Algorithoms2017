package com.ccsi.utils;

import java.util.*;

/**
 * Created by gxliu on 2017/5/29.
 */
public class UnionFindPre {
    public static void main(String[] args) {
        String[][] stars=new String[][]{{"李亚鹏","周迅"},{"李亚鹏","王菲"},{"谢霆锋","王菲"},
                {"谢霆锋","张柏芝"},{"李晨","范冰冰"},{"李晨","张馨予"},{"陈小春","张柏芝"},
                {"应采儿","陈小春"},{"郑伊健","梁咏琪"},{"郑伊健","蒙嘉慧"}};
        List<Set<String>> result=category(stars);
        for(Set<String> set:result){
            set.forEach(x-> System.out.print(x+" "));
            System.out.println();
        }
    }
    //此方法的复杂度是O(N^2)
    public static List<Set<String>> category(String[][] stars){
        Set<String> set=new HashSet<>();
        List<Set<String>> list=new ArrayList<>();

        if(stars==null||stars.length==0||stars[0].length==0)return list;

        for (int i = 0; i < stars.length; i++) {
            String star1=stars[i][0];
            String star2=stars[i][1];

            if(set.contains(star1)){  //包含明星1的情况
                for(Set<String> first:list){
                    if(first.contains(star1)){
                        for (Set<String> second:list){
                            if(second.contains(star2)){
                                first.addAll(second);  //有可能明星2也在
                            }else{
                                first.add(star2);      //有可能明星2不在
                            }
                        }
                    }
                }
            }else if(set.contains(star2)){  //明星1不在，但明星2在的情况
                for(Set<String> first:list){
                    if(first.contains(star2)){
                        first.add(star1);
                    }
                }
            }else{                         //明星1和2都不在的情况
                Set<String> newSet= new HashSet<>();
                newSet.add(star1);
                newSet.add(star2);
                list.add(newSet);          //新建一个组，并加入到list里面去
            }

            set.add(star1);                //不顾在不在都要加入到已经查过的库里面去
            set.add(star2);
        }
        return list;
    }
}
