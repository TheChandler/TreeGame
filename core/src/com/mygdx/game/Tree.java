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
    private boolean deleteThis;

    private int state;

    private Texture texture;
    private Texture dirt=new Texture("dirt.png");
    private Texture waterIcon=new Texture("icon-water.png"),
            mineralIcon=new Texture("IconMineral.png"),
            sunIcon=new Texture("IconSUN.png");


    public TreeStats treeStats;
    private DynamicTree dt;
    BitmapFont font;
    public Tree() {
        dt=new DynamicTree(590,960,30,150,90,30);
        deleteThis = false;
        texture = new Texture("tree0.png");
        font = new BitmapFont(Gdx.files.internal("code.fnt"));
        state = 0;
        treeStats = new TreeStats();
    }
    public void upgrade(){
        switch (++state){
            case 1:
                texture=new Texture("sprout.png");
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
        //upgrade();
        dt.grow();

    }
    public void update(){
        treeStats.addValues();
    }
    public void render(SpriteBatch sb,Vector2 offset){

        sb.draw(texture,offset.x,offset.y);
        sb.draw(waterIcon,offset.x+900,offset.y+880,90,90);
        sb.draw(sunIcon,offset.x+900,offset.y+770,90,90);
        sb.draw(mineralIcon,offset.x+900,offset.y+990,90,90);
        font.draw(sb, "" + (int)treeStats.minerals, offset.x + 750, offset.y + 1050);
        font.draw(sb, "" + (int)treeStats.water, offset.x + 750, offset.y + 940);
        font.draw(sb, "" + (int)treeStats.sun, offset.x + 750, offset.y + 830);
        font.draw(sb, "Air " + (int)treeStats.air, offset.x + 750, offset.y + 720);
        dt.render(sb);
    }
}
