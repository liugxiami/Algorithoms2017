package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/6.
 */

public class MinimumSpanningTreePrim {
    //用Edge来表示图，有一个int类型的from和一个to，加上一个weight。
    //为了能实现按weight排序，加入一个comparator。
    private static class Edge implements Comparable<Edge>{
        @Override
        public int compareTo(Edge o) {
            if(this.weight>o.weight) return 1;
            else if(this.weight<o.weight)return -1;
            else return 0;
        }

        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    //建图，用一个Map来表示图，输入是Edge数组，转换成Map类型的图，key是节点，因为有from和to，
    // 其实from与to是没有方向的，所以在建图的时候要加两遍，一是以from作为key，一是以to作为key
    public static Map<Integer,List<Edge>> createGraph(Edge[] edges) {
        Map<Integer,List<Edge>> graph=new HashMap<>();
        if(edges==null||edges.length==0)return graph;

        for (int i = 0; i < edges.length; i++) {
            Edge other=new Edge(edges[i].to,edges[i].from,edges[i].weight);
            if(!graph.containsKey(edges[i].from)){
                graph.put(edges[i].from,new ArrayList<>());
            }
            graph.get(edges[i].from).add(edges[i]);

            if(!graph.containsKey(edges[i].to)){
                graph.put(edges[i].to,new ArrayList<>());
            }
            graph.get(edges[i].to).add(other);
        }
        return graph;
    }
    //主函数，传入图，返回路径。
    //利用一个priorityQueue作为主要数据结构，将visited的节点相关的edge传入pq，poll出来的必定是最小的边，
    //用类似BFS算法来找到最小生成树。
    public static List<Edge> primMST(Map<Integer,List<Edge>> graph){
        List<Edge> result=new ArrayList<>();
        if(graph==null)return result;

        Set<Integer> visited=new HashSet<>();
        PriorityQueue<Edge> pq=new PriorityQueue<>();

        visited.add(0);
        for (Edge e:graph.get(0)){
            pq.offer(e);
        }

        while(!pq.isEmpty()){
            Edge curr=pq.poll();
            int from=curr.from;
            int to=curr.to;
            if(visited.contains(from)&&visited.contains(to))continue;
            if(visited.contains(from)){
                for(Edge e:graph.get(to)){
                    pq.offer(e);
                }
                visited.add(to);
            }
            if(visited.contains(to)){
                for(Edge e:graph.get(from)){
                    pq.offer(e);
                }
                visited.add(from);
            }
            result.add(curr);
        }
        return result;
    }
    public static void main(String[] args) {
        Edge[] edges = new Edge[16];

        edges[0] = new Edge(0, 7, 0.16);
        edges[1] = new Edge(2, 3, 0.17);
        edges[2] = new Edge(1, 7, 0.19);
        edges[3] = new Edge(0, 2, 0.26);

        edges[4] = new Edge(5, 7, 0.28);
        edges[5] = new Edge(1, 3, 0.29);
        edges[6] = new Edge(1, 5, 0.32);
        edges[7] = new Edge(2, 7, 0.34);

        edges[8] = new Edge(4, 5, 0.35);
        edges[9] = new Edge(1, 2, 0.36);
        edges[10] = new Edge(4, 7, 0.37);
        edges[11] = new Edge(0, 4, 0.38);

        edges[12] = new Edge(6, 2, 0.40);
        edges[13] = new Edge(3, 6, 0.52);
        edges[14] = new Edge(6, 0, 0.58);
        edges[15] = new Edge(6, 4, 0.93);

        Map<Integer,List<Edge>> graph = createGraph(edges);
        List<Edge> mst = primMST(graph);

        System.out.println("MST: ");
        for (Edge e : mst) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }
    }
}
