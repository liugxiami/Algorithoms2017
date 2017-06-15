package com.ccsi.graph;


import java.util.*;


public class Fleury {
    public void fleury(int[][] graph, Stack<Integer> path, int current, int start) {
        int num_edges=graph.length;  //graph中的边数
        boolean flag = false;  //是否还有与x关联的边
        path.push(current);
        for (int i = start; i < graph.length; i++) {
            //从start开始搜索是否有边
            if (graph[i][current] > 0) { //i与current有边
                flag = true;
                graph[i][current] = 0;
                graph[current][i] = 0; //删除边
                fleury(graph, path, i, 0);  //从i开始搜索
                break;
            }
        }

        if (flag) { //如果没有变与当前节点current相连
            path.pop();
            int m = path.peek();
            graph[m][current] = graph[current][m] = 1; //恢复边
            int new_start = current + 1;
            if (path.size() == num_edges) { //完成回路
                return;
            } else {
                fleury(graph, path, m, new_start);
            }
        }
    }
}