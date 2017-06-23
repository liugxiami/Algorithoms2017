package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/21.
 */
public class LC64MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid={{1,2,4,2},
                {2,3,4,5},
                {3,2,1,4},
                {5,6,7,8}};
        System.out.println(minPathSum(grid));
    }
    public static int minPathSum(int[][] grid){
        if(grid==null||grid.length==0||grid[0].length==0)return 0;
        int rowNum=grid.length;
        int colNum=grid[0].length;

        int[][] cache=new int[rowNum][colNum];
        cache[0][0]=grid[0][0];

        for (int row = 1; row < rowNum; row++) {
            cache[row][0]=cache[row-1][0]+grid[row][0];
        }

        for (int col = 1; col < colNum; col++) {
            cache[0][col]=cache[0][col-1]+grid[0][col];
        }

        for (int row = 1; row < rowNum; row++) {
            for (int col = 1; col < colNum; col++) {
                cache[row][col]=Math.min(cache[row-1][col],cache[row][col-1])+grid[row][col];
            }
        }

        return cache[rowNum-1][colNum-1];
    }
    //内存优化
    public static int minPathSum1(int[][] grid){
        if(grid==null||grid.length==0||grid[0].length==0)return 0;
        int rowNum=grid.length;
        int colNum=grid[0].length;

        int[] pre=new int[colNum];
        int sumRow=grid[0][0];

        pre[0]=grid[0][0];
        for (int col = 1; col < colNum; col++) {
            pre[col]=pre[col-1]+grid[0][col];
        }

        for (int row = 1; row < rowNum; row++) {
            int[] curr=new int[colNum];
            sumRow=sumRow+grid[row][0];
            curr[0]=sumRow;
            for (int col = 1; col < colNum; col++) {
                curr[col]=Math.min(curr[col-1],pre[col])+grid[row][col];
            }
            pre=curr;
        }
        return pre[colNum-1];
    }
}
