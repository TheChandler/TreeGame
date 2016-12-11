package com.mygdx.game;

/**
 * Created by chandler on 12/10/2016.
 */

public class TreeStats {
    int energy,water,air,sun,minerals;
    public TreeStats(){
        energy=0;
        water=0;
        air=0;
        sun=0;
        minerals=0;
    }
    public void createEnergy(){
        water-=50;
        air-=50;
        minerals-=50;
        sun-=50;
        energy+=1;
    }
    public void addWater(int w){
        water+=w;
    }
    public void addAir(int a){
        air+=a;
    }
    public void addSun(int s){
        sun+=s;
    }
    public void addMinerals(int m){
        minerals+=m;
    }
    public boolean canMakeEnergy(){
        return (water>50&&minerals>50&&air>50&&sun>50);
    }
}
