package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 12/6/2016.
 */

public class Button {
    private Vector2 bl,ur;
    public Button(Vector2 bottomLeft,Vector2 upperRight){
        bl=bottomLeft;
        ur=upperRight;
    }
    public boolean check(Vector2 cord){
        System.out.println(cord+" "+bl+" "+ur);
        return (cord.x>bl.x&&cord.x<ur.x&&cord.y>bl.y&&cord.y<ur.y);
    }
}
