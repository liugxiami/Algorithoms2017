package com.ccsi.utils;

/**
 * Created by gxliu on 2017/6/26.
 */
public class BigIntMultiply {
    public static void main(String[] args) {
        String a="12345678";
        String b="87654321";
        System.out.println(bigIntMultiply(a,b));
    }

    public static String bigIntMultiply(String a,String b){
        int lenA=a.length();
        int lenB=b.length();
        int lenAB=Math.max(lenA,lenB);
        if(lenAB<=4){
            return String.format("%d",Integer.parseInt(a)*Integer.parseInt(b));
        }

        a=formatString(a,lenAB);
        b=formatString(b,lenAB);

        int len1=lenAB/2;
        int len2=lenAB-len1;

        String A=a.substring(0,len1);
        String B=a.substring(len1);
        String C=b.substring(0,len1);
        String D=b.substring(len1);

        String AC=bigIntMultiply(A,C);
        String AD=bigIntMultiply(A,D);
        String BC=bigIntMultiply(B,C);
        String BD=bigIntMultiply(B,D);

        String ADBC=bigIntAdd(AD,BC);
        if(BD.length()>len2){
            ADBC=bigIntAdd(ADBC,BD.substring(0,BD.length()-len2));
        }

        if(ADBC.length()>len2){
            AC=bigIntAdd(AC,ADBC.substring(0,ADBC.length()-len2));
        }

        return AC+ADBC.substring(len2)+BD.substring(len2);

    }

    private static String formatString(String x,int len){
        int lenX=x.length();
        int delta=len-lenX;
        StringBuffer sb=new StringBuffer();
        while(delta-->0){
            sb.append("0");
        }
        sb.append(x);
        return sb.toString();
    }

    private static String bigIntAdd(String a,String b){
        int len=a.length()>b.length()?a.length():b.length();
        a=formatString(a,len);
        b=formatString(b,len);

        StringBuffer sb=new StringBuffer();
        int flag=0;
        for (int i = len-1; i >=0 ; i--) {
           int curr=flag+a.charAt(i)-'0'+b.charAt(i)-'0';
           flag=curr/10;
           curr=curr%10;
           sb.append(curr);
        }
        return flag==1?(sb.append(1).reverse().toString()):(sb.reverse().toString());
    }
}
