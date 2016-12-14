package com.mygdx.game;

/**
 * Created by chandler on 12/10/2016.
 */

public class TreeStats {
    public float energy,water,air,sun,minerals;
    public int treeLevel,rootLevel,leafLevel;
    public float treeLevelCost,rootLevelCost,leafLevelCost;
    public float airRate,mineralRate,energyRate,waterRate,sunRate;
    public TreeStats(){
        treeLevel=1;
        rootLevel=1;
        leafLevel=1;
        energy=0;
        water=0;
        air=0;
        sun=0;
        minerals=0;
        treeLevelCost = 1;
        rootLevelCost = 1;
        leafLevelCost = 1;
        airRate = 1;
        mineralRate = 1;
        energyRate = 1;
        waterRate = 1;
        sunRate = 1;
    }
    public void createEnergy(){
        water-=50;
        air-=50;
        minerals-=50;
        sun-=50;
        energy+=1;
    }
    public void levelUpLeaves(){
        leafLevel++;
        energy-=(int)leafLevelCost;
        sunRate*=1.25*(1+treeLevel*.01);
        airRate*=1.25*(1+treeLevel*.01);
        leafLevelCost=(float)(leafLevel*1.5);
    }
    public void levelUpRoots(){
        rootLevel++;
        energy-=(int)rootLevelCost;
        waterRate*=1.25*(1+treeLevel*.01);
        mineralRate*=1.25*(1+treeLevel*.01);
        rootLevelCost=(float)(rootLevel*1.5);
    }
    public void levelUpTree(){
        energy-=(int)treeLevelCost;
        treeLevel++;
        treeLevelCost*=1.3;
    }
    public void addValues() {
        addWater(waterRate);
        addAir(airRate);
        addMinerals(mineralRate);
        addSun(sunRate);
    }
    public void addWater(float w)   {water+=w;}
    public void addAir(float a)     {air+=a;}
    public void addSun(float s)     {sun+=s;}
    public void addMinerals(float m){minerals+=m;}
    public boolean canMakeEnergy()      {return (water>=50&&minerals>=50&&air>=50&&sun>=50);}
    public boolean canLevelUpLeaves()   {return (energy>=(int)leafLevelCost);}
    public boolean canLevelUpRoots()    {return (energy>=(int)rootLevelCost);}
    public boolean canLevelUpTree()     {return (energy>=(int)treeLevelCost);}
}
