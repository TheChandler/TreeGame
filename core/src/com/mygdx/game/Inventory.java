package com.mygdx.game;

/**
 * Created by chandler on 12/15/2016.
 */

public class Inventory {
    public static int normalTree=0;
    public static Building[] items=new Building[42];
    public static int[] count=new int[42];
    public static void initialize(){
        for (int i=0;i<42;i++){
            count[i]=0;
            items[i]=null;
        }
        count[0]=3;
        items[0]=new Tree();
    }
    public static boolean remove(int n){
        if (count[n]>0){
            count[n]--;
            if (count[n]==0){
                items[n]=null;
            }
            return true;
        }
        return false;
    }
}
