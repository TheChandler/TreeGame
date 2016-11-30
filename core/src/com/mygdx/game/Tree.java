package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Created by Chandler on 11/18/2016.
 */

public class Tree extends Building {
    boolean deleteThis=false;

    int state;
    int waterLevel;
    int maxWater;
    int sunLevel;



    Texture texture;
    Texture dirt=new Texture("dirt.png");
    Texture sun=new Texture("sun.png");
    Texture water=new Texture("water.png");
    public Tree(){
        texture=new Texture("tree0.png");
        state=0;
        waterLevel=0;
        sunLevel=0;
    }
    public void upgrade(){
        state++;
        maxWater=state*115;
        sunLevel=0;
        switch (state){
            case 1:
                texture=new Texture("tree1.png");
                break;
            case 2:
                texture=new Texture("tree2.png");
                break;
            case 3:
                texture=new Texture("tree3.png");
                waterLevel=0;
                sunLevel=0;
                break;
        }
    }
    public boolean deleteThis(){
        return deleteThis;
    }
    public void killTree(){
        deleteThis = true;
        state=0;
        sunLevel=0;
        texture=new Texture("tree0.png");
    }
    public void interact(){
        if (state==0){
            upgrade();
        }
        if (state<3){
            waterLevel=maxWater;
        }else if(state==3){
            Supplies.addWood(5);
            killTree();
        }
    }
    public void update(){
        if (state>0&&state<3) {
            waterLevel--;
            if (waterLevel == 0) {
                killTree();
            }
            sunLevel+=13;
            if (sunLevel >= state * 150 && state < 3 && waterLevel > 0) {
                upgrade();
            }
        }
    }
    public void render(SpriteBatch sb){
        sb.draw(dirt,0,0);
        sb.draw(texture,0,0);
        if(state>0) {
            for (int i = 0; i <((float) (waterLevel) /(float) maxWater)*40; i++) {
                sb.draw(water, 0, i * 19);
            }
            for (int i = 0; i < ((float)sunLevel  / (float)(state * 150))*40; i++) {
                sb.draw(sun, 0, i * 19);
            }
        }
    }
}
