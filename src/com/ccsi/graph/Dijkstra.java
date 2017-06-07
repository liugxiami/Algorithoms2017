package com.ccsi.graph;


public class Dijkstra {
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
        System.out.println(dijkstraLength(graph));
    }
    public static int dijkstraLength(int[][] graph){
        if(graph==null||graph.length==0||graph[0].length==0)return -1;
        int len=graph[0].length;

        int[] dis=graph[0].clone();  //辅助数组

        boolean[] visited=new boolean[len];  //记录fix了的最小值
        visited[0]=true;

        for (int i = 0; i < graph.length-1; i++) {
            int min=Integer.MAX_VALUE;
            int idx=-1;
            for (int j = 0; j < len; j++) {
                if(!visited[j]&&min>dis[j]){
                    min=dis[j];
                    idx=j;                   //找到当前最小
                }
            }

            visited[idx]=true;
            for (int j = 0; j < len; j++) {
                if(!visited[j]){       //关键，跟新最小值
                    dis[j]=Math.min(dis[j],dis[idx]+graph[idx][j]);
                }
            }

            for (int j = 0; j < len; j++) {   //查看当前数组
                System.out.print(dis[j]+" ");
            }
            System.out.println();
            System.out.println("============");
        }


        return dis[len-1];
    }
}
