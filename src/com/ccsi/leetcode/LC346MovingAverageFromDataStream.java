package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/7/5.
 */
public class LC346MovingAverageFromDataStream {
    private Queue<Integer> queue;
    private int size;
    private int sum;
    public LC346MovingAverageFromDataStream(int size) {
        queue=new LinkedList<>();
        this.size=size;
        this.sum=0;
    }
    public double next(int val){
        if(queue.size()>=size){
            int temp=queue.poll();
            sum-=temp;
            queue.offer(val);
            sum+=val;
        }else {
            queue.offer(val);
            sum+=val;
        }
        return 1.0*sum/queue.size();
    }

    public static void main(String[] args) {
        LC346MovingAverageFromDataStream avg=new LC346MovingAverageFromDataStream(3);
        System.out.println(avg.next(1));
        System.out.println(avg.next(2));
        System.out.println(avg.next(3));
        System.out.println(avg.next(4));
        System.out.println(avg.next(5));
        System.out.println(avg.next(6));
    }
}
