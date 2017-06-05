package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/2.
 */
public class GraphNode {
    String name;
    List<GraphNode> children;

    public GraphNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
}
