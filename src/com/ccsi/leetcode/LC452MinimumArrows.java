package com.ccsi.leetcode;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by gxliu on 2017/6/16.
 */
public class LC452MinimumArrows {
    //方法1：
    public static int findMinArrowShots(int[][] points){
        if(points==null||points.length==0)return 0;
        int len=points.length;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])return o1[1]-o2[1];
                else return o1[0]-o2[0];
            }
        });

        int result=1;
        int minEnd=points[0][1];
        for (int i = 1; i < len; i++) {
            int start=points[i][0];
            int end=points[i][1];
            if(start>minEnd){
                minEnd=end;
                result++;
            }else{
                minEnd=Math.min(minEnd,end);
            }
        }
        return result;
    }
    //方法2：
    private static class Balloon{
        int start;
        int end;

        public Balloon(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static int findMinArrowShots1(int[][] points){
        if(points==null||points.length==0)return 0;
        PriorityQueue<Balloon> pq=new PriorityQueue<>(new Comparator<Balloon>() {
            @Override
            public int compare(Balloon o1, Balloon o2) {
                if(o1.start==o2.start)return o1.end-o2.end;
                else return o1.start-o2.start;
            }
        });
        int len=points.length;

        for (int i = 0; i < len; i++) {
            int start=points[i][0];
            int end=points[i][1];
            Balloon temp=new Balloon(start,end);
            pq.offer(temp);
        }

        int minEnd=Integer.MAX_VALUE;
        int result=1;

        while(!pq.isEmpty()){
            Balloon curr=pq.poll();
            int currStart=curr.start;
            int currEnd=curr.end;
            if(currStart>minEnd){
                result++;
                minEnd=currEnd;
            }else{
                minEnd=Math.min(minEnd,currEnd);
            }
        }
        return  result;
    }
    public static void main(String[] args) {
        int[][] points={{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println(findMinArrowShots1(points));
    }
}
