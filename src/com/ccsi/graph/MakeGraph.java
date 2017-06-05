package com.ccsi.graph;

/**
 * Created by gxliu on 2017/6/3.
 */
public class MakeGraph {
    public static GraphNode make(){
        GraphNode a=new GraphNode("A");
        GraphNode b=new GraphNode("B");
        GraphNode c=new GraphNode("C");
        GraphNode d=new GraphNode("D");
        GraphNode e=new GraphNode("E");
        GraphNode f=new GraphNode("F");

        a.children.add(d);
        a.children.add(f);
        b.children.add(c);
        c.children.add(b);
        c.children.add(d);
        d.children.add(c);
        d.children.add(a);
        d.children.add(e);
        e.children.add(d);
        e.children.add(f);
        f.children.add(e);
        f.children.add(a);

        return a;
    }
}
