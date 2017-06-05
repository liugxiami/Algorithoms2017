package com.ccsi.graph;

import java.util.*;

/**
 * Created by gxliu on 2017/6/4.
 */
public class GraphNodeWithWeight {
    String name;
    List<GraphNodeWithWeight> children;
    List<Integer> weights;

    public GraphNodeWithWeight(String name) {
        this.name = name;
        this.children = new ArrayList<>();
        this.weights = new ArrayList<>();
    }
}
