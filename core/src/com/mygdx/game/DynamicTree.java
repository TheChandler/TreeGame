package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Chandler on 1/17/2017.
 */

public class DynamicTree {
    private Texture trunk=new Texture("squar.png");
    private DynamicTree[] branches;
    private int height,width;
    private float angle;
    public DynamicTree(int w,int h,float a){
        branches=new DynamicTree[10];
        height=h;
        width=w;
        angle=a;
    }
    public void render(SpriteBatch sb){
        sb.draw(trunk,540,960,60,0,width,height,1,1,angle,0,0,120,120,false,false);
        for (int i=0;i<branches.length;i++){
            if (branches[i]!=null){
                branches[i].render(sb);
            }
        }
    }
}
