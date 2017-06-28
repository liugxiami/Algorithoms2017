package com.ccsi.leetcode;

import java.util.Stack;

/**
 * Created by gxliu on 2017/6/27.
 */
public class LC155MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public LC155MinStack() {
        stack=new Stack<>();
        minStack=new Stack<>();
    }
    public void push(int x){
        if(minStack.isEmpty()||minStack.peek()>x){
            minStack.push(x);
        }
        stack.push(x);
    }

    public void pop(){
        int temp=stack.pop();
        if(!minStack.isEmpty()&&temp==minStack.peek()){
            minStack.pop();
        }
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return minStack.peek();
    }

    public static void main(String[] args) {
        LC155MinStack mini=new LC155MinStack();
        mini.push(-2);
        mini.push(0);
        mini.push(-3);
        System.out.println(mini.getMin());
        mini.pop();
        System.out.println(mini.top());
        System.out.println(mini.getMin());
    }
}
