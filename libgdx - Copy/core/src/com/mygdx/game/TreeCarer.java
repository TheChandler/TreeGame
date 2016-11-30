package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Chandler on 11/20/2016.
 */

public class TreeCarer extends Building {
    TreeManager tm;
    Texture treeCarer=new Texture("treeCarer.png");
    public TreeCarer(TreeManager tm){
        this.tm=tm;
    }
    @Override
    public void interact() {

    }

    @Override
    public void update() {
        for (int i =0;i<10;i++){
            if (tm.trees[i]!=null&&tm.trees[i].getClass().getName().compareTo("com.mygdx.game.Tree")==0){
                tm.trees[i].interact();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(treeCarer,0,0);
    }

    @Override
    public boolean deleteThis() {
        return false;
    }
}
