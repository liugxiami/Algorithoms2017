package com.ccsi.leetcode;


import java.util.Stack;

/**
 * Created by gxliu on 2017/6/30.
 */
public class LC150EvaluateReversePolisNotation {
    public static void main(String[] args) {
        String[] tokens={"2","1","+","3","*"};
        String[] tokens2={"4","13","5","/","+"};
        System.out.println(evalRPN(tokens2));
    }
    public static int evalRPN(String[] tokens){
        if(tokens==null||tokens.length==0)return 0;
        Stack<Integer> stack=new Stack<>();
        for (String token:tokens
             ) {
             if(isOperator(token)){
                 int num2=stack.pop();
                 int num1=stack.pop();
                 int num3=calculate(num1,num2,token);
                 stack.push(num3);
             }else{
                 stack.push(Integer.parseInt(token));
             }
        }
        return stack.pop();
    }
    private static boolean isOperator(String s){
        return s.equals("+")||s.equals("-")||s.equals("/")||s.equals("*");
    }
    private static int calculate(int num1,int num2,String ope){
        switch (ope){
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
