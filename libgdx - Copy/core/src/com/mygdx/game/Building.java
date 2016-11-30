package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Chandler on 11/20/2016.
 */

public abstract class Building {
    public abstract void interact();
    public abstract void update();
    public abstract void render(SpriteBatch sb);
    public abstract boolean deleteThis();
}
