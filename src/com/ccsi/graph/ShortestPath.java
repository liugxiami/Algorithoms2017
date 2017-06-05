package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/3.
 */
public class ShortestPath {
    public static void main(String[] args) {
        /*GraphNode root=MakeGraph.make();
        System.out.println(shortestPathBFS(root,"D"));*/
        GraphNodeWithWeight root=MakeGraphWithWeight.make();
        System.out.println(grahpDFS(root,"E"));
    }
    private static Set<String> visited=new HashSet<>();
    public static int shortestPathBFS(GraphNode root,String target){
        Queue<GraphNode> queue=new LinkedList<>();
        queue.offer(root);
        int layer=0;

        while(!queue.isEmpty()){
            Queue<GraphNode> nextQueue=new LinkedList<>();
            while(!queue.isEmpty()){
                GraphNode curr=queue.poll();
                visited.add(curr.name);
                if(target.equals(curr.name))return layer;

                for(GraphNode child:curr.children){
                    if(child!=null&&!visited.contains(child.name)){
                        nextQueue.offer(child);
                    }
                }
            }
            layer++;
            queue=nextQueue;
        }
        return -1;
    }

    private static int min=Integer.MAX_VALUE;

    public static int grahpDFS(GraphNodeWithWeight root,String target){
        if(root==null)return 0;
        visit(root,target,0,0);
        return min;
    }
    private static void visit(GraphNodeWithWeight curr,String target,int total,int weight){
        total+=weight;
        if(target.equals(curr.name)){
            min=Math.min(min,total);
            return;
        }

        visited.add(curr.name);
        for (int i=0;i<curr.children.size();i++) {
            GraphNodeWithWeight child=curr.children.get(i);
            if(child!=null&&!visited.contains(child)){
                visit(child,target,total,curr.weights.get(i));
            }
        }
        visited.remove(curr.name);
        //total-=weight;
    }

    
}
