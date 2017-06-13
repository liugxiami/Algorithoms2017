package com.ccsi.leetcode;

import java.util.*;

/**
 * Created by gxliu on 2017/6/12.
 */
public class LC207CourseSchedule {
    private static class Course{
        int number;
        Set<Integer> pre;
        Set<Integer> dep;

        public Course(int number) {
            this.number = number;
            this.pre = new HashSet<>();
            this.dep = new HashSet<>();
        }
    }
    private static Map<Integer,Course> courses=new HashMap<>();
    private static void makeCourses(int[][] prerequisites){
        for (int i = 0; i < prerequisites.length; i++) {
            int dep=prerequisites[i][0];
            int pre=prerequisites[i][1];
            if(!courses.containsKey(dep)){
                courses.put(dep,new Course(dep));
            }
            courses.get(dep).pre.add(pre);

            if(!courses.containsKey(pre)){
                courses.put(pre,new Course(pre));
            }
            courses.get(pre).dep.add(dep);
        }
    }
    public static boolean canFinish(int numCourses,int[][] prerequisites){
        if(numCourses==0||prerequisites==null||prerequisites.length==0)return true;
        makeCourses(prerequisites);
        PriorityQueue<Course> pq=new PriorityQueue<>(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.pre.size()-o2.pre.size();
            }
        });

        for (int i = 0; i < courses.size(); i++) {
            pq.offer(courses.get(i));
        }

        while (!pq.isEmpty()){
            Course curr=pq.poll();
            if(curr.pre.size()!=0)return false;

            for (Integer i:curr.dep){
                courses.get(i).pre.remove(curr.number);
                pq.remove(courses.get(i));
                pq.offer(courses.get(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int numcourses=5;
        int[][] prerequisites={{1,0},{0,2},{2,3},{3,4}};
        System.out.println(canFinish(numcourses,prerequisites));
    }
}
