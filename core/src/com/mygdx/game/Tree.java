package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
    private int maxSun;


    private Texture texture;
    private Texture dirt=new Texture("dirt.png");
    private Texture sunBlock=new Texture("sun.png");
    private Texture waterBlock=new Texture("water.png");

    public int health,air,minerals,energy,water,sun;
    private int healthRate,airRate,mineralRate,energyRate,waterRate,sunRate;

    BitmapFont font;
    public Tree(){
        texture=new Texture("tree0.png");
        font=new BitmapFont(Gdx.files.internal("code.fnt"));
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        state=0;
        waterLevel=0;
        sunLevel=0;
        water=0;
        sun=0;
        minerals=0;
        energy=0;
        air=0;
        health=100;
        healthRate=0;
        airRate=1;
        mineralRate=1;
        energyRate=1;
        waterRate=1;
        sunRate=1;
    }
    private void upgrade(){
        state++;
        switch (state){
            case 1:
                texture=new Texture("tree_realistic_1.png");
                break;
            case 2:
                texture=new Texture("tree2.png");
                break;
            case 3:
                texture=new Texture("tree3.png");
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
    private void addValues(){
        water+=waterRate;
        air+=airRate;
        minerals+=mineralRate;
        sun+=sunRate;
        energy+=energyRate;
    }
    public void update(){
        addValues();
    }
    public void render(SpriteBatch sb,Vector2 offset){
        sb.draw(texture,offset.x,offset.y);
        font.draw(sb, "Water " + water, offset.x + 750, offset.y + 940);
        font.draw(sb, "Sun " + sun, offset.x + 750, offset.y + 830);
        font.draw(sb, "Air " + air, offset.x + 750, offset.y + 720);
        font.draw(sb, "Minerals " + minerals, offset.x + 750, offset.y + 1050);

    }
}
