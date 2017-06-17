package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/16.
 */
public class LC402RemoveKDigits {
    public static String removeKDights(String num,int k){
        if(num==null||num.length()==0)return null;
        int len=num.length();

        if(len-k<=0)return null;
        int top=0;

        char[] result=new char[len];
        for (int i = 0; i < len; i++) {
            char c=num.charAt(i);
            while(top>0&&result[top-1]>c&&k>0){
                top-=1;
                k-=1;
            }
            result[top++]=c;

        }

        int index=0;
        while(index<len&&result[index]=='0')index++;
        return new String(result,index,num.length()-k-index);
    }

    public static String removeKDigits1(String num,int k){
        if(num==null||num.length()==0)return null;
        int len=num.length();
        if(len<=k)return null;

        Stack<Character> stack=new Stack<>();
        for (int i = 0; i < len; i++) {
            char c=num.charAt(i);
            while(!stack.isEmpty()&&stack.peek()>c&&k>0){
                stack.pop();
                k-=1;
            }
            stack.push(c);
        }
        while(stack.get(0)=='0')stack.remove(0);

        StringBuilder res=new StringBuilder();
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }
    public static void main(String[] args) {
        String num="1432219";
        System.out.println(removeKDigits1(num,3));
    }
}
