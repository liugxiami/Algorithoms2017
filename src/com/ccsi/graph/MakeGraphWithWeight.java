package com.ccsi.graph;

/**
 * Created by gxliu on 2017/6/4.
 */
public class MakeGraphWithWeight {
    public static GraphNodeWithWeight make(){
        GraphNodeWithWeight a=new GraphNodeWithWeight("A");
        GraphNodeWithWeight b=new GraphNodeWithWeight("B");
        GraphNodeWithWeight c=new GraphNodeWithWeight("C");
        GraphNodeWithWeight d=new GraphNodeWithWeight("D");
        GraphNodeWithWeight e=new GraphNodeWithWeight("E");
        GraphNodeWithWeight f=new GraphNodeWithWeight("F");

        a.children.add(b);
        a.weights.add(1);
        a.children.add(c);
        a.weights.add(12);
        b.children.add(c);
        b.weights.add(9);
        b.children.add(d);
        b.weights.add(3);
        c.children.add(e);
        c.weights.add(5);
        d.children.add(c);
        d.weights.add(4);
        d.children.add(e);
        d.weights.add(13);
        d.children.add(f);
        d.weights.add(15);
        e.children.add(f);
        e.weights.add(4);
        return a;
    }
}
