package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/15.
 */
public class LC200NumOfIslandsGraph {
    private class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int numIslands(char[][] grid){
        if(grid==null||grid.length==0||grid[0].length==0)return 0;
        int rowNum=grid.length;
        int colNum=grid[0].length;

        boolean[][] flag=new boolean[rowNum][colNum]; //需要一个数组来记录是否被访问过。
        Queue<Point> queue=new LinkedList<>();        //BFS方法需要一个queue。

        int result=0;
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                if(flag[row][col])continue;           //如果这个点被访问过了，直接略过
                if(grid[row][col]=='1'){              //仅考虑等于1的点
                    Point point=new Point(row,col);   //使用Point类来传递方便，一个实例就好了，里面包含了行和列
                    queue.offer(point);
                    flag[row][col]=true;

                    while(!queue.isEmpty()){
                        Point curr=queue.poll(); //只需再判断后和下两个相邻点就行了。
                        if(canVisit(grid,flag,curr.row+1,curr.col,rowNum,colNum)){
                            queue.offer(new Point(curr.row+1,curr.col));
                        }
                        if(canVisit(grid,flag,curr.row,curr.col+1,rowNum,colNum)){
                            queue.offer(new Point(curr.row,curr.col+1));
                        }

                    }
                    result++;
                }
            }
        }
        return result;
    }
    private boolean canVisit(char[][] grid,boolean[][] flag,int row,int col,int rowNum,int colNum){
        if(!(row>=0&&row<rowNum&&col>=0&&col<colNum&&!flag[row][col])){
            return false;
        }
        flag[row][col]=true;
        return grid[row][col]=='1';
    }

    public static void main(String[] args) {
        char[][] grids={{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        LC200NumOfIslandsGraph num=new LC200NumOfIslandsGraph();
        int nums=num.numIslands(grids);
        System.out.println(nums);
    }
}
