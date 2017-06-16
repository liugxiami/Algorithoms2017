package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/15.
 */
public class LC392IsSubsequence {
    public boolean isSubsequence(String s,String t){
        if(t.length()==0)return false;
        if(s.length()==0)return true;
        //双指针
        int indexS=0;
        int indexT=0;

        while(indexT<t.length()){  //外指针是长指针
            char c=t.charAt(indexT);
            if(s.charAt(indexS)==c){
                indexS++;
                if(indexS==s.length())return true; //短指针到头了，返回true。
            }
            indexT++;
        }
        return false;
    }

    public static void main(String[] args) {
        LC392IsSubsequence sub=new LC392IsSubsequence();
        System.out.println(sub.isSubsequence("ace","abdecf"));
    }
}
