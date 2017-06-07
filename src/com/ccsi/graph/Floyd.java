package com.ccsi.graph;

/**
 * Created by gxliu on 2017/6/5.
 */
public class Floyd {
    public static void main(String[] args) {
        int len=6;
        int[][] graph=new int[len][len];

        int m=10000;
        graph[0]=new int[]{0,1,2,m,m,m};
        graph[1]=new int[]{m,0,9,3,m,m};
        graph[2]=new int[]{m,m,0,m,5,m};
        graph[3]=new int[]{m,m,4,0,13,15};
        graph[4]=new int[]{m,m,m,m,0,4};
        graph[5]=new int[]{m,m,m,m,m,0};
        System.out.println(floyd(graph));
    }
    public static int floyd(int[][] graph){
        int len=graph.length;
        int[][] aux=graph.clone();

        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    aux[i][j]=Math.min(aux[i][j],aux[i][k]+aux[k][j]);
                }
            }
        }

        return aux[0][len-1];
    }
}
