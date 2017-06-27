package com.ccsi.utils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by gxliu on 2017/6/26.
 */
public class ClosestTwoPoints {
    public static void main(String[] args) {
        Point[] points=new Point[6];
        points[0]=new Point(2,3);
        points[1]=new Point(12,30);
        points[2]=new Point(40,50);
        points[3]=new Point(5,1);
        points[4]=new Point(12,10);
        points[5]=new Point(3,4);
        System.out.println("The smallest distance is "+closest(points)+".");
    }
    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double closest(Point[] points){
        if(points==null||points.length<=1)return 0;
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.x==o2.x)return o1.y-o2.y;
                else return o1.x-o2.x;
            }
        });
        return closest(points,0,points.length-1);
    }

    private static double closest(Point[] points,int start,int end){
        if(end-start<=4){
            return bruteforce(points,start,end);
        }

        int mid=start+(end-start)/2;

        double minP=closest(points,start,mid);
        double minQ=closest(points,mid+1,end);

        double min=Math.min(minP,minQ);

        Point[] newPoints=new Point[end-start+1];
        int index=0;
        for (int i = start; i <=end ; i++) {
            if(Math.abs(points[i].x-points[mid].x)<=min){
                newPoints[index++]=points[i];
            }
        }

        double minNew=closest(newPoints,0,index-1);
        return Math.min(min,minNew);
    }

    private static double bruteforce(Point[] points,int start,int end){
        double min=Double.MAX_VALUE;
        for (int i = start; i <= end-1; i++) {
            for (int j = i+ 1; j <= end; j++) {
                min=Math.min(min,distance(points[i],points[j]));
            }
        }
        return min;
    }

    private static double distance(Point a,Point b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
    }

}
