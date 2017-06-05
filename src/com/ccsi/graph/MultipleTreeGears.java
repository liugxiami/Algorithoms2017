package com.ccsi.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by gxliu on 2017/6/2.
 */
public class MultipleTreeGears {
    public static void main(String[] args) {
        GraphNode root=MakeMultipleTree.make();
        layerPrint(root);
    }

    public static void dfs(GraphNode root){
        if(root==null)return;
        System.out.println(root.name);
        for (GraphNode node :
                root.children) {
            dfs(node);
        }
    }

    public static void bfs(GraphNode root){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            GraphNode curr=queue.poll();
            System.out.println(curr.name);
            for(GraphNode node:curr.children){
                if(node!=null){
                    queue.offer(node);
                }
            }
        }
    }

    public static void layerPrint(GraphNode root){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Queue<GraphNode> nextQueue=new LinkedList<>();
            while(!queue.isEmpty()){
                GraphNode curr=queue.poll();
                System.out.print(curr.name+" ");
                for(GraphNode node:curr.children){
                    if(node!=null){
                        nextQueue.offer(node);
                    }
                }
            }
            System.out.println();
            System.out.println("==========");
            queue=nextQueue;
        }
    }
}
