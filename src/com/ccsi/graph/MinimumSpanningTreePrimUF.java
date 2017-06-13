package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/11.
 */
public class MinimumSpanningTreePrimUF {
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
            if(this.weight>o.weight)return 1;
            else if(this.weight<o.weight)return -1;
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
            this.rank = 1;
        }
    }
    private static Map<Integer,UFElement> map=new HashMap<>();
    //unionFind 第一步，makeSet
    private static void makeSet(Edge[] edges){
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
    //unionF第二步 union
    private static void union(int key1,int key2){
        int parent1=find(key1);
        int parent2=find(key2);

        if(parent1!=parent2){
            UFElement eParent1=map.get(parent1);
            UFElement eParent2=map.get(parent2);

            if(eParent1.rank<eParent2.rank){
                eParent1.parent=parent2;
            }else if(eParent1.rank>eParent2.rank){
                eParent2.parent=parent1;
            }else{
                eParent1.parent=parent2;
                eParent2.rank++;
            }
        }
    }
    //unionFind 第三步
    private static int find(int key){
        UFElement curr=map.get(key);
        UFElement temp=curr;
        while(true){
            int name=curr.key;
            if(name==curr.parent){
                temp.parent=name;
                return name;
            }
            curr=map.get(curr.parent);
        }
    }
    //建一个以节点为key，value是相连边的map(也就是图的另一种表示方法，该方法很实用)，方便取边。
    private static Map<Integer,List<Edge>> makeMap(Edge[] edges){
        Map<Integer,List<Edge>> result=new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int from=edges[i].from;
            int to=edges[i].to;
            Edge other=new Edge(to,from,edges[i].weight);

            if(!result.containsKey(from)){
                result.put(from,new ArrayList<>());
            }
            result.get(from).add(edges[i]);

            if(!result.containsKey(to)){
                result.put(to,new ArrayList<>());
            }
            result.get(to).add(other);
        }
        return result;
    }
    //主函数，利用一个priorityqueue结合BFS方法，来找最短边，要注意的是不要
    // 重复入queue，所以需要一个Set来记录访问过的节点。
    public static List<Edge> MSTPrimUF(Edge[] edges){
        List<Edge> result=new ArrayList<>();
        if(edges==null||edges.length==0)return result;

        PriorityQueue<Edge> pq=new PriorityQueue<>();
        Map<Integer,List<Edge>> graph=makeMap(edges);
        Set<Integer> visited=new HashSet<>();

        for(Edge e:graph.get(0)){
            pq.offer(e);
        }

        while(!pq.isEmpty()){
            Edge curr=pq.poll();
            int from=curr.from;
            int to=curr.to;

            int fromParent=find(from);
            int toParent=find(to);


            if(fromParent!=toParent){
                result.add(curr);
                union(from,to);
                if(!visited.contains(from)&&graph.containsKey(from)){
                    for(Edge e:graph.get(from)){
                        pq.offer(e);
                    }
                    visited.add(from);
                }

                if(!visited.contains(to)&&graph.containsKey(to)){
                    for(Edge e:graph.get(to)){
                        pq.offer(e);
                    }
                    visited.add(to);
                }
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

        makeSet(edges);
        List<Edge> mst = MSTPrimUF(edges);

        System.out.println("MST: ");
        for (Edge e : mst) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }
    }
}
