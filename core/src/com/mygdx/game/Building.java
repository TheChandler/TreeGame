package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 11/20/2016.
 */

public abstract class Building {
    public abstract void interact();
    public abstract void update();
    public abstract void render(SpriteBatch sb,Vector2 offset);
    public abstract boolean deleteThis();
}
