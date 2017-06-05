package com.ccsi.graph;

/**
 * Created by gxliu on 2017/6/2.
 */
public class MakeMultipleTree {
    public static GraphNode make(){
        GraphNode a=new GraphNode("A");
        GraphNode b=new GraphNode("B");
        GraphNode c=new GraphNode("C");
        GraphNode d=new GraphNode("D");
        GraphNode e=new GraphNode("E");
        GraphNode f=new GraphNode("F");

        a.children.add(b);
        a.children.add(c);
        a.children.add(d);
        c.children.add(e);
        c.children.add(f);

        return a;
    }
}
