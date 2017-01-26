package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 12/6/2016.
 */

public class Button {
    private Vector2 bl,ur;

    //I realized that making Vector2s just to make a button was a pretty dumb idea so you can either make vectors when you make the button or just put in the coords and it will do it for you
    public Button(Vector2 bottomLeft,Vector2 upperRight){
        bl=bottomLeft;
        ur=upperRight;
    }

    //The one below makes the vector2s for you. This one is probably more efficient, but I don't really know
    public Button(int blx,int bly,int urx,int ury){
        bl=new Vector2(blx,bly);
        ur=new Vector2(urx,ury);
    }
    public boolean check(Vector2 cord){
        System.out.println(cord+" "+bl+" "+ur+" "+(cord.x>bl.x&&cord.x<ur.x&&cord.y>bl.y&&cord.y<ur.y));
        return (cord.x>bl.x&&cord.x<ur.x&&cord.y>bl.y&&cord.y<ur.y);
    }
}
