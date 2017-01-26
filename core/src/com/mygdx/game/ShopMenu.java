package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 1/26/2017.
 */

public class ShopMenu extends MenuClass {
    boolean close=false;
    Texture shopMenu=new Texture("shopMenu1.0.png");
    @Override
    public void render(SpriteBatch sb) {
        sb.draw(shopMenu,0,0);
    }

    @Override
    public boolean close() {
        return close;
    }

    @Override
    public Building building() {
        return null;
    }

    @Override
    public void touch(Vector2 cords) {
        close=true;
    }
}
