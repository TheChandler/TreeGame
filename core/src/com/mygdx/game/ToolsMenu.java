package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 1/26/2017.
 */

public class ToolsMenu extends MenuClass {
    boolean close=false;
    Texture toolsMenu=new Texture("toolsMenu1.0.png");
    @Override
    public void render(SpriteBatch sb) {
        sb.draw(toolsMenu,0,0);
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
