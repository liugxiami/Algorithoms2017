package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/9.
 */
public class MinimumSpanningTreeKruskalUF {
    private static class Edge implements Comparable<Edge>{
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight>o.weight) return 1;
            else if(this.weight<o.weight) return -1;
            else return 0;
        }
    }

    private static class UFElement{
        int key;
        int parent;
        int rank;

        public UFElement(int key, int parent) {
            this.key = key;
            this.parent = parent;
            this.rank =1;
        }
    }

    private static Map<Integer,UFElement> map=new HashMap<>();

    public static void makeSet(Edge[] edges){
        if(edges==null||edges.length==0)return;
        for (int i = 0; i < edges.length; i++) {
            int from=edges[i].from;
            int to=edges[i].to;
            if(!map.containsKey(from)){
                map.put(from,new UFElement(from,from));
            }

            if(!map.containsKey(to)){
                map.put(to,new UFElement(to,to));
            }
        }
    }

    public static void union(int from,int to){
        int fParent=find(from);
        int tParent=find(to);

        if(fParent!=tParent){
            UFElement eFrom= map.get(fParent);
            UFElement eTo=map.get(tParent);

            if(eFrom.rank<eTo.rank){
                eFrom.parent=tParent;
            }else if(eFrom.rank>eTo.rank){
                eTo.parent=fParent;
            }else {
                eFrom.parent=tParent;
                eTo.rank++;
            }
        }

    }

    public static int find(int num){
        UFElement curr=map.get(num);
        UFElement temp=curr;
        while(true){
            int key=curr.key;
            if(key==curr.parent){
                temp.parent=key;
                return key;
            }
            curr=map.get(curr.parent);
        }
    }

    public static List<Edge> kruskalMST(Edge[] edges){
        List<Edge> result=new ArrayList<>();
        if(edges==null||edges.length==0)return result;
        int len=edges.length;
        makeSet(edges);

        PriorityQueue<Edge> pq=new PriorityQueue<>();

        for (int i = 0; i < len; i++) {
            pq.offer(edges[i]);
        }

        while(!pq.isEmpty()){
            Edge curr=pq.poll();
            int from=curr.from;
            int to=curr.to;

            int fParent=find(from);
            int tParent=find(to);

            if(fParent==tParent)continue;
            else{
                union(from,to);
                result.add(curr);
            }
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

        List<Edge> mst = kruskalMST(edges);

        System.out.println("MST: ");
        for (Edge e : mst) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }
    }
}
