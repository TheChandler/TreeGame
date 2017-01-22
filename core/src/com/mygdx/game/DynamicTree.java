package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 1/17/2017.
 */

public class DynamicTree {
    private Texture trunk=new Texture("squar.png");
    private Texture ball=new Texture("sunBall.png");
    private DynamicTree[] branches;
    private int height,width;
    private float angle,angleChange;
    private Vector2 tip;
    private Vector2 base;
    public DynamicTree(float x,float y,int w,int h,float a,float ac){
        branches=new DynamicTree[10];
        height=h;
        width=w;
        angle=a;
        angleChange=ac;
        base=new Vector2();
        base.set(x,y);
        tip=new Vector2();
        tipSet();
    }
    private void tipSet(){
        tip.set((float)(Math.cos(Math.toRadians(angle))*height+base.x),(float) (Math.sin(Math.toRadians(angle))*height)+base.y);
    }
    public void setBase(Vector2 t){
        base.set(t.x,t.y);
    }
    public void render(SpriteBatch sb){
        sb.draw(trunk,base.x-width/2,base.y,width/2,0,width,height,1,1,angle-90,0,0,120,120,false,false);
        //sb.draw(trunk,tip.x,tip.y);
        for (int i=0;i<branches.length;i++){
            if (branches[i]!=null){
                branches[i].render(sb);
            }
        }
    }
    public void grow(){
        width*=1.4;
        height*=1.25;
        tipSet();

        if (branches[0]==null) {
            branches[0] = new DynamicTree(tip.x, tip.y, (int)(width*.8), (int)(height*.6), angle + angleChange,angleChange-15);
            branches[1] = new DynamicTree(tip.x,tip.y,(int)(width*.8),(int)(height*.6),angle-angleChange,angleChange+15);
        }else {
            for (int i=0;i<10;i++){
                if (branches[i]!=null){
                    branches[i].setBase(tip);
                    branches[i].grow();

                }
            }
        }
/*
        angle+=10;
        angle%=360;
        tip.set((float)(Math.cos(Math.toRadians(angle))*height)+base.x,(float) (Math.sin(Math.toRadians(angle))*height)+base.y);
        System.out.println(angle+" "+Math.cos(Math.toRadians(angle))+" "+Math.sin(Math.toRadians(angle)));
*/
    }
}
