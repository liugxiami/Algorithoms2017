package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/1.
 * Classic unionFind
 */
public class LC200NumOfIslands {
    private class UFElement{
        int key;
        int parent;
        int rank;

        public UFElement(int key, int parent) {
            this.key = key;
            this.parent = parent;
            this.rank = 0;
        }
    }

    private UFElement[][] items;
    private int count=0;

    public void makeSets(int[][] grids){
        int rowLength=grids.length;
        int colLength=grids[0].length;
        items=new UFElement[rowLength][colLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if(grids[i][j]==1){
                    items[i][j]=new UFElement(i*colLength+j,i*colLength+j);
                    count++;
                }
            }
        }
    }

    public void union(int ele1,int ele2,int len){
        int parent1=find(ele1,len);
        int parent2=find(ele2,len);
        if(parent1!=parent2){
            UFElement uParent1=items[parent1/len][parent1%len];
            UFElement uParent2=items[parent2/len][parent2%len];

            if(uParent1.rank<uParent2.rank){
                uParent1.parent=parent2;
            }else if(uParent1.rank>uParent2.rank){
                uParent2.parent=parent1;
            }else{
                uParent1.parent=parent2;
                uParent2.rank++;
            }
            count--;
        }
    }

    public int find(int ele,int len){
        UFElement curr=items[ele/len][ele%len];
        UFElement temp=curr;
        while(true){
            int parent=curr.parent;
            if(parent==curr.key){
                temp.parent=parent;
                return parent;
            }
            curr=items[parent/len][parent%len];
        }
    }

    public int numOfIslands(int[][] grids){
        if(grids==null||grids.length==0||grids[0].length==0)return 0;
        makeSets(grids);

        int colLength=grids[0].length;
        for (int row = 0; row < grids.length; row++) {
            for (int col = 0; col < grids[0].length; col++) {
                if(grids[row][col]==1){
                    if(row+1<grids.length&&grids[row+1][col]==1){
                        union(row*colLength+col,(row+1)*colLength+col,colLength);
                    }

                    if(col+1<colLength&&grids[row][col+1]==1){
                        union(row*colLength+col,row*colLength+col+1,colLength);
                    }
                }
            }
        }
        return count;
    }
}
