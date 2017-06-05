package com.ccsi.leetcode;

/**
 * Created by gxliu on 2017/6/1.
 */
public class LC130SurroundedRegions {
    private class position{
        int row;
        int col;

        public position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public boolean equals(position other){
            return this.row==other.row&&this.col==other.col;
        }
    }

    private class UFElement{
        position key;
        position parent;
        int rank;
        boolean canFlip;

        public UFElement(position key, position parent) {
            this.key = key;
            this.parent = parent;
            this.rank = 0;
            this.canFlip = true;
        }
    }

    private UFElement[][] items;
    private int count=0;

    public void makeSets(Character[][] grids){
        int rowLen=grids.length;
        int colLen=grids[0].length;

        items=new UFElement[rowLen][colLen];

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if(grids[row][col]=='o'){
                    items[row][col]=new UFElement(new position(row,col),new position(row,col));
                    count++;
                }
            }
        }
    }

    public void union(position p1,position p2){
        position parent1=find(p1);
        position parent2=find(p2);

        if(!parent1.equals(parent2)){
            UFElement uParent1=items[parent1.row][parent1.col];
            UFElement uParent2=items[parent2.row][parent2.col];

            if(uParent1.rank<uParent2.rank){
                uParent1.parent=parent2;
            }else if(uParent1.rank>uParent2.rank){
                uParent2.parent=parent1;
            }else{
                uParent1.parent=parent2;
                uParent2.rank++;
            }

            uParent1.canFlip&=uParent2.canFlip;
            uParent2.canFlip&=uParent1.canFlip;

            count--;
        }
    }

    public position find(position p){
        UFElement curr=items[p.row][p.col];
        UFElement temp=curr;
        while(true){
            position parent=curr.parent;
            if(parent.equals(curr.key)){
                temp.parent=parent;
                return parent;
            }
            curr=items[parent.row][parent.col];
        }
    }

    public void solve(Character[][] grids){
        if(grids==null||grids.length==0||grids[0].length==0)return;
        makeSets(grids);

        int rowLen=grids.length;
        int colLen=grids[0].length;
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if(grids[row][col]=='o'){
                    if(row==0||col==0||row==rowLen-1||col==colLen-1){
                        position parent=find(new position(row,col));
                        items[parent.row][parent.col].canFlip=false;
                    }

                    if(row+1<rowLen&&grids[row+1][col]=='o'){
                        union(new position(row,col),new position(row+1,col));
                    }

                    if(col+1<colLen&&grids[row][col+1]=='o'){
                        union(new position(row,col),new position(row,col+1));
                    }
                }
            }
        }

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if(grids[row][col]=='o'&&items[row][col].canFlip){
                    grids[row][col]='x';
                }
            }
        }

        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                System.out.print(grids[row][col]);
            }
            System.out.println();
        }
    }


}
