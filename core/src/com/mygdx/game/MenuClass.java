package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by chandler on 12/15/2016.
 */

public abstract class MenuClass {
    public abstract void render(SpriteBatch sb);
    public abstract boolean close();
    public abstract Building building();
    public abstract void touch(Vector2 cords);
}
