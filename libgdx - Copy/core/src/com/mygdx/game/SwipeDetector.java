package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
/**
 * Created by Chandler on 11/18/2016.
 */

public class SwipeDetector {

    Vector2 point1,point2;
    boolean isTouched;
    int direction;

    public SwipeDetector(){
        point1=new Vector2();
        point2=new Vector2();
        isTouched=false;
        direction=0;
    }
    public void handleInput(){
        if (isTouched){
            if (Gdx.input.isTouched()){
                point2.set(Gdx.input.getX(),Gdx.input.getY());
            }else{
                isTouched=false;
                setDirection();
                System.out.println(direction);
            }
        }else{
            if (Gdx.input.isTouched()){
                isTouched=true;
                point1.set(Gdx.input.getX(),Gdx.input.getY());
            }
        }
    }
    public int getDirection(){
        return direction;
    }
    private void setDirection(){
        float xvalue=point1.x-point2.x;
        if (xvalue>100){
            direction=1;
        }else if(xvalue<-100){
            direction=-1;
        }else{
            direction=0;
        }
    }
}