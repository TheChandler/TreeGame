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
        buttons[2]=new Button(new Vector2(649,674),new Vector2(937,752));
        buttons[3]=new Button(new Vector2(649,532),new Vector2(937,610));
        buttons[4]=new Button(new Vector2(649,1562),new Vector2(937,1640));
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
                            makeEnergy();
                            break;
                        case(2):
                            levelUpRoots();
                            break;
                        case(3):
                            levelUpLeaves();
                            break;
                        case(4):
                            levelUpTree();
                            break;
                    }
                }
            }
        }
    }
    private void makeEnergy(){
        if (tree.treeStats.canMakeEnergy()){
            tree.treeStats.createEnergy();
        }
    }
    private void levelUpRoots(){
        if (tree.treeStats.canLevelUpRoots()) {
            tree.treeStats.levelUpRoots();
        }
    }
    private void levelUpLeaves(){
        if (tree.treeStats.canLevelUpLeaves()){
            tree.treeStats.levelUpLeaves();
        }
    }
    private void levelUpTree(){
        if (tree.treeStats.canLevelUpTree()){
            tree.upgrade();
            tree.treeStats.levelUpTree();
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
        font.draw(sb,Integer.toString((int)tree.treeStats.water),pos.x+789,pos.y+1204);
        font.draw(sb,Integer.toString((int)tree.treeStats.minerals),pos.x+789,pos.y+1084);
        font.draw(sb,Integer.toString((int)tree.treeStats.sun),pos.x+789,pos.y+964);
        font.draw(sb,Integer.toString((int)tree.treeStats.air),pos.x+789,pos.y+844);
        font.draw(sb,Integer.toString((int)tree.treeStats.energy),pos.x+789,pos.y+1500);
        font.draw(sb,Integer.toString((int)tree.treeStats.leafLevelCost),pos.x+838,pos.y+594);
        font.draw(sb,Integer.toString((int)tree.treeStats.rootLevelCost),pos.x+838,pos.y+736);
        font.draw(sb,Integer.toString((int)tree.treeStats.treeLevelCost),pos.x+838,pos.y+1622);
        font.draw(sb,"50",pos.x+822,pos.y+1361);
    }
}
