package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/3.
 */
public class GraphGears {
    public static void main(String[] args) {
        GraphNode root=MakeGraph.make();
        layerPrintGraph(root);
    }
    private static Set<String> visited=new HashSet<>();
    public static void graphDFS(GraphNode root){
        if(root==null||visited.contains(root.name))return;
        System.out.println(root.name);
        visited.add(root.name);
        for(GraphNode node:root.children){
            graphDFS(node);
        }
    }

    public static void graphBFS(GraphNode root){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            GraphNode curr=queue.poll();
            if(visited.contains(curr.name)){
                continue;
            }
            System.out.println(curr.name);
            visited.add(curr.name);
            for(GraphNode node:curr.children){
                if(node!=null)queue.offer(node);
            }
        }
    }
    public static void graphBFS1(GraphNode root){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.offer(root);
        visited.add(root.name);

        while(!queue.isEmpty()){
            GraphNode curr=queue.poll();
            System.out.println(curr.name);

            for(GraphNode child:curr.children){
                if(child!=null&&!visited.contains(child.name))queue.offer(child);
                visited.add(child.name);
            }
        }
    }

    public static void layerPrintGraph(GraphNode root){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Queue<GraphNode> nextQueue=new LinkedList<>();
            while(!queue.isEmpty()){
                GraphNode curr=queue.poll();
                if(visited.contains(curr.name))continue;
                System.out.print(curr.name+" ");
                visited.add(curr.name);
                for(GraphNode child:curr.children){
                    if(child!=null)nextQueue.offer(child);
                }
            }
            System.out.println();
            queue=nextQueue;
        }
    }
}
