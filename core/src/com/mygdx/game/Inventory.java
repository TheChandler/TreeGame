package com.mygdx.game;

/**
 * Created by chandler on 12/15/2016.
 */

public class Inventory {
    public static int normalTree=0;
    public static int[] itemId=new int[42];
    public static int[] count=new int[42];
    public static void initialize(){
        for (int i=0;i<42;i++){
            count[i]=0;
            itemId[i]=0;
        }
        count[0]=3;
        itemId[0]=1;
    }
    public static boolean remove(int n){
        if (count[n]>0){
            count[n]--;
            if (count[n]==0){
                itemId[n]=0;
            }
            return true;
        }
        return false;
    }
    public static Building getBuildingById(int g){
        if (g==1){
            return new Tree();
        }
        return null;
    }
}
