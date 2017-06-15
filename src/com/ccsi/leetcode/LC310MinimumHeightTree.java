package com.ccsi.leetcode;


import java.util.*;

/**
 * Created by gxliu on 2017/6/13.
 */
public class LC310MinimumHeightTree {
    private class Node{
        int number;
        List<Node> children;

        public Node(int number) {
            this.number = number;
            this.children = new ArrayList<>();
        }
    }

    public List<Integer> findMHT(int n,int[][] edges){
        List<Integer> result=new ArrayList<>();
        if(n==0)return result;

        Node[] nodes=new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i]=new Node(i);
        }

        for (int i = 0; i < edges.length; i++) {
            int from=edges[i][0];
            int to=edges[i][1];

            nodes[from].children.add(nodes[to]);
            nodes[to].children.add(nodes[from]);
        }
        int[] pre1=new int[n];
        int[] pre2=new int[n];
        for (int i = 0; i < n; i++) {
            pre1[i]=-1;
            pre2[i]=-1;
        }
        List<Integer> path1=findLongestPathBFSM(nodes[0],pre1);
        List<Integer> path2=findLongestPathBFSM(nodes[path1.get(path1.size()-1)],pre2);

        if(path2.size()%2==0){
            result.add(path2.get(path2.size()/2-1));
        }
        result.add(path2.get(path2.size()/2));
        return result;
    }
    //DFS
    private List<Integer> findLongestPath(Node start){
        List<Integer> max=new ArrayList<>();
        Set<Integer> visited=new HashSet<>();
        Stack<Integer> path=new Stack<>();
        DFS(start,path,max,visited);
        return max;
    }
    private void DFS(Node root,Stack<Integer> path,List<Integer> max,Set<Integer> visited){
        path.push(root.number);
        visited.add(root.number);

        for(Node child:root.children){
            if(!visited.contains(child.number)){
                DFS(child,path,max,visited);
            }
        }

        if(path.size()>max.size()){
            max.clear();
            max.addAll(path);
        }

        path.pop();
        visited.remove(root.number);
    }
    //BFS
    private List<Integer> findLongestPathBFS(Node start){
        List<Integer> result=new ArrayList<>();
        Queue<List<Node>> queue=new LinkedList<>();
        List<Node> init=new LinkedList<>();
        init.add(start);
        queue.offer(init);

        List<Node> res=new ArrayList<>();
        Set<Integer> visited=new HashSet<>();

        while(!queue.isEmpty()){
            Queue<List<Node>> nextQueue=new LinkedList<>();
            while(!queue.isEmpty()){
                List<Node> currList=queue.poll();
                Node curr=currList.get(currList.size()-1);

                res.clear();
                res.addAll(currList);
                visited.add(curr.number);

                for (Node child:curr.children) {
                    if(!visited.contains(child.number)){
                        List<Node> newList=new ArrayList<>(currList);
                        newList.add(child);
                        nextQueue.offer(newList);
                    }
                }
            }
            queue=nextQueue;
        }

        for(Node n:res){
            result.add(n.number);
        }
        return result;
    }
    //Modified BFS,利用一个数组来保存路径。注意要记住最后一个child，这样才能回溯。
    private List<Integer> findLongestPathBFSM(Node start,int[] pre){
        List<Integer> result=new ArrayList<>();
        Queue<Node> queue=new LinkedList<>();
        queue.offer(start);

        Set<Integer> visited=new HashSet<>();
        int last=-1;
        while(!queue.isEmpty()){
            Queue<Node> nextQueue=new LinkedList<>();
            while(!queue.isEmpty()){
                Node curr=queue.poll();
                visited.add(curr.number);

                last=curr.number;
                for(Node child:curr.children){
                    if(!visited.contains(child.number)){
                        nextQueue.offer(child);
                        pre[child.number]=curr.number;
                    }
                }
            }
            queue=nextQueue;
        }

        while(pre[last]!=-1){
            result.add(0,pre[last]);
            last=pre[last];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] edges={{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        LC310MinimumHeightTree MHT=new LC310MinimumHeightTree();
        List<Integer> result=MHT.findMHT(6,edges);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
