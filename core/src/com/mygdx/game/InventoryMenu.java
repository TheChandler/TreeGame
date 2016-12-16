package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by chandler on 12/15/2016.
 */

public class InventoryMenu extends MenuClass {
    Building building;
    boolean close;
    public InventoryMenu(Building b){
        System.out.println("Menu Opened");
        building=b;
        close=false;
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public boolean close() {
        return close;
    }
    public Building building(){
        return building;
    }

    public void touch(Vector2 cords){
        if (cords.y>900){
            close=true;
            System.out.println("close");
        }else{
            System.out.println("Chose Tree");
            building=new Tree();
        }
    }
}
