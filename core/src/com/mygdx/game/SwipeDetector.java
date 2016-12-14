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
    float deltax;
    float deltay;

    public SwipeDetector(){
        point1=new Vector2();
        point2=new Vector2();
        isTouched=false;
        direction=0;
        deltax=0;
        deltay=0;
    }
    public boolean testCollision(Vector2 vector1, Vector2 vector2){
        return (Forest.touchX>vector1.x&&
                Forest.touchX<vector2.x&&
                Forest.touchY>vector1.y&&
                Forest.touchX<vector2.y);
    }
    public void handleInput(){
        if (isTouched){
            if (Gdx.input.isTouched()){
                point2.set(Forest.touchX,Forest.touchY);
                deltax=point2.x-point1.x;
                deltay=point2.y-point1.y;
            }else{
                isTouched=false;
                setDirection();
                deltax=0;
                deltay=0;
            }
        }else{
            if (Gdx.input.isTouched()){
                isTouched=true;
                point1.set(Forest.touchX,Forest.touchY);
            }
        }
    }
    public int getDirection(){
        return direction;
    }
    private void setDirection(){
        float value=point1.x-point2.x;
        int pos=2,neg=4;
        if(Math.abs(point1.y-point2.y)>Math.abs(value)){
            pos=1;
            neg=3;
            value=point1.y-point2.y;
        }
        if (value>100){
            direction=pos;
        }else if(value<-100){
            direction=neg;
        }else{
            direction=0;
        }
    }
}