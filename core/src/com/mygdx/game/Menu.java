package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 12/6/2016.
 */

public class Menu {
    boolean isUpdating;
    Vector2 pos;
    Texture texture=new Texture("MenuLevel1.png");
    boolean close;
    boolean deleteThis;
    Button[] buttons;
    Tree tree;
    BitmapFont font=new BitmapFont(Gdx.files.internal("code.fnt"));
    public Menu(Building t){
        pos=new Vector2(1920,0);
        isUpdating=true;
        close = false;
        deleteThis=false;
        setButtons();
        tree = (Tree)t;
        font.setColor(Color.BLACK);
        font.getData().setScale(1);
    }
    private void setButtons(){
        buttons = new Button[20];
        buttons[0]=new Button(new Vector2(433,163),new Vector2(723,259));
        buttons[1]=new Button(new Vector2(648,1300),new Vector2(938,1379));
    }
    public void touch(Vector2 cord){
        for (int i =0;i<buttons.length;i++){
            if (buttons[i]!=null){
                if (buttons[i].check(cord)){
                    switch (i){
                        case(0):
                            close=true;
                            break;
                        case(1):
                            if (tree.treeStats.canMakeEnergy()){
                                tree.treeStats.createEnergy();
                            }
                    }
                }
            }
        }
    }
    public void update(){
        if (pos.x>0){
            pos.x-=108;
        }
        if (pos.x<0){
            pos.x=0;
            isUpdating=false;
        }
    }
    private void close(){
        close = true;
    }
    private void closing(){
        pos.x+=108;
        if (pos.x>1080){
            deleteThis=true;
            close=false;
        }
    }
    public void render(SpriteBatch sb){
        if (close){
            closing();
        }
        if (isUpdating) {
            update();
        }
        sb.draw(texture,pos.x,pos.y);
        font.draw(sb,Integer.toString(tree.treeStats.water),pos.x+682,pos.y+1204);
        font.draw(sb,Integer.toString(tree.treeStats.minerals),pos.x+682,pos.y+1084);
        font.draw(sb,Integer.toString(tree.treeStats.sun),pos.x+682,pos.y+964);
        font.draw(sb,Integer.toString(tree.treeStats.air),pos.x+682,pos.y+844);
        font.draw(sb,Integer.toString(tree.treeStats.energy),pos.x+682,pos.y+1420);
    }
}
