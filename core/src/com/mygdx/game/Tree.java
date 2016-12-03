package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;


/**
 * Created by Chandler on 11/18/2016.
 */

public class Tree extends Building {
    private boolean deleteThis=false;

    private int state;
    private int waterLevel;
    private int maxWater;
    private int sunLevel;



    private Texture texture;
    private Texture dirt=new Texture("dirt.png");
    private Texture sun=new Texture("sun.png");
    private Texture water=new Texture("water.png");

    BitmapFont font;
    public Tree(){
        texture=new Texture("tree0.png");
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(10);
        state=0;
        waterLevel=0;
        sunLevel=0;
    }
    private void upgrade(){
        state++;
        maxWater=state*115;
        sunLevel=0;
        switch (state){
            case 1:
                texture=new Texture("tree_realistic_1.png");
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
    private void killTree(){
        deleteThis = true;
        texture.dispose();
        dirt.dispose();
        sun.dispose();
        water.dispose();
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
            sunLevel+=1;
            if (sunLevel >= state * (100+(50*state-1)) && state < 3 && waterLevel > 0) {
                upgrade();
            }
        }
    }
    public void render(SpriteBatch sb,Vector2 offset){
        sb.draw(dirt,offset.x,offset.y);
        sb.draw(texture,offset.x,offset.y);
        if(state>0) {
            for (int i = 0; i <((float) (waterLevel) /(float) maxWater)*40; i++) {
                sb.draw(water, offset.x, i * 19+offset.y);
            }
            for (int i = 0; i < ((float)sunLevel  / (float)(state * 150))*40; i++) {
                sb.draw(sun, offset.x, i * 19+offset.y);
            }
        }
        font.draw(sb,"Water",offset.x,offset.y);
        font.draw(sb,String.valueOf(waterLevel),offset.x,offset.y);
    }
}
