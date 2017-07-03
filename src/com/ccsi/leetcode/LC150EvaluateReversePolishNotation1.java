package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/7/2.
 * 先算乘除，后算加减
 */
public class LC150EvaluateReversePolishNotation1 {
    public static void main(String[] args) {
        String[] tokens={"2","1","+","3","*"};
        String[] tokens2={"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }
    public static int evalRPN(String[] tokens){
        if(tokens==null||tokens.length==0)return 0;
        Stack<Integer> stack=new Stack<>();
        Stack<String> operators=new Stack<>();

        //第一个循环，碰到乘除就计算，否则，加减入operators，数字入stack
        for(String token:tokens){
            if(isMulDiv(token)){
                int num2=stack.pop();
                int num1=stack.pop();
                int num3=calculate(num1,num2,token);
                stack.push(num3);
            }else if(isAddMin(token)){
                operators.push(token);
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        //第二个循环，碰到加减就计算
        while(!operators.isEmpty()){
            String operator=operators.pop();
            int num2=stack.pop();
            int num1=stack.pop();
            int num3=calculate(num1,num2,operator);
            stack.push(num3);
        }
        return stack.pop();
    }
    private static boolean isMulDiv(String token){
        return token.equals("*")||token.equals("/");
    }
    private static boolean isAddMin(String token){
        return token.equals("+")||token.equals("-");
    }
    private static int calculate(int num1,int num2,String token){
        switch (token){
            case "+":
                return num1+num2;
            case "-":
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                return num1/num2;
        }
        return 0;
    }
}
