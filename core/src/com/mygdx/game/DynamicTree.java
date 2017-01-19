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
    private float angle;
    private Vector2 tip;
    private Vector2 base;
    public DynamicTree(float x,float y,int w,int h,float a){
        branches=new DynamicTree[10];
        height=h;
        width=w;
        angle=a-90;
        base=new Vector2();
        base.set(x,y);
        tip=new Vector2();
        tip.set((float)(Math.sin(angle)*height)+base.x,(float) (Math.cos(angle)*height)+base.y);
        System.out.println("tip: "+tip.x+" "+tip.y);
    }
    public void render(SpriteBatch sb){
        sb.draw(trunk,base.x,base.y,width/2,0,width,height,1,1,angle,0,0,120,120,false,false);
        sb.draw(ball,tip.x,tip.y);
        for (int i=0;i<branches.length;i++){
            if (branches[i]!=null){
                branches[i].render(sb);
            }
        }
    }
    public void grow(){
       /* if (branches[0]==null) {
            branches[0] = new DynamicTree(tip.x, tip.y, (int)(width*.9), (int)(height*.9), angle + 10);
        }else{
            branches[0].grow();
        }*/
        angle+=10;
        tip.set((float)(Math.sin(angle)*height)+base.x,(float) (Math.cos(angle)*height)+base.y);

    }
}
