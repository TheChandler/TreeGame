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


    public TreeStats treeStats;

    BitmapFont font;
    public Tree() {
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

    }
    public void update(){
        treeStats.addValues();
    }
    public void render(SpriteBatch sb,Vector2 offset){
        sb.draw(texture,offset.x,offset.y);
        font.draw(sb, "Water " + (int)treeStats.water, offset.x + 750, offset.y + 940);
        font.draw(sb, "Sun " + (int)treeStats.sun, offset.x + 750, offset.y + 830);
        font.draw(sb, "Air " + (int)treeStats.air, offset.x + 750, offset.y + 720);
        font.draw(sb, "Minerals " + (int)treeStats.minerals, offset.x + 750, offset.y + 1050);


    }
}
