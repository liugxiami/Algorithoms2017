package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/4.
 */
public class LC332ReconstrustItinerary {
    public static void main(String[] args) {
        //String[][] tickets={{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        String[][] tickets={{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<String> res=getItinerary(tickets);
        res.forEach(str-> System.out.print(str+" "));
    }

    private static Map<String,PriorityQueue<String>> map=new HashMap();
    private static List<String> result=new LinkedList<>();

    public static List<String> getItinerary(String[][] tickets){
        if(tickets==null||tickets.length==0||tickets[0].length==0)return null;
        for (int i = 0; i < tickets.length; i++) {
            String from=tickets[i][0];
            String to=tickets[i][1];
            if(!map.containsKey(from)){
                map.put(from,new PriorityQueue<String>());
            }
            map.get(from).offer(to);
        }

        helper("JFK");
        return result;

    }
    private static void helper(String from){
        result.add(from);
        while(!map.isEmpty()){
            PriorityQueue<String> pq=map.get(from);
            String to=pq.poll();
            result.add(to);
            if(pq.isEmpty())map.remove(from);
            from=to;
        }
    }
}
