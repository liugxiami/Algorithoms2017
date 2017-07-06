package com.ccsi.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by gxliu on 2017/7/5.
 */
public class LC295FindMedianFromDataStream {
    //for first half;
    private PriorityQueue<Integer> bigQueue;
    //for second half;
    private PriorityQueue<Integer> smallQueue;
    private boolean isOdd;
    public LC295FindMedianFromDataStream() {
        bigQueue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        //for second half;
        smallQueue=new PriorityQueue<>();
        isOdd=true;
    }
    public void addNum(int num){
        if(isOdd){
            if(bigQueue.isEmpty()||num<=bigQueue.peek()){
                bigQueue.offer(num);
            }else{
                smallQueue.offer(num);
                int temp=smallQueue.poll();
                bigQueue.offer(temp);
            }
        }else{
            if(num<=bigQueue.peek()){
                int temp=bigQueue.poll();
                bigQueue.offer(num);
                smallQueue.offer(temp);
            }else{
                smallQueue.offer(num);
            }
        }
        isOdd=!isOdd;
    }

    public double findMedian(){
        double result;
        if(!isOdd){
            result=bigQueue.peek();
        }else{
            result=1.0*(bigQueue.peek()+smallQueue.peek())/2;
        }
        return result;
    }

    public static void main(String[] args) {
        LC295FindMedianFromDataStream findMedian=new LC295FindMedianFromDataStream();
        findMedian.addNum(1);
        findMedian.addNum(2);
        System.out.println(findMedian.findMedian());
        findMedian.addNum(3);
        System.out.println(findMedian.findMedian());
        findMedian.addNum(4);
        System.out.println(findMedian.findMedian());
        findMedian.addNum(5);
        System.out.println(findMedian.findMedian());
    }
}
