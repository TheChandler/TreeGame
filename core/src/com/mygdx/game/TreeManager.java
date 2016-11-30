package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Chandler on 11/18/2016.
 */

public class TreeManager {
    int currentTree;
    Building[] trees;
    int maxTrees=10;
    public TreeManager(){
        currentTree=0;
        trees=new Building[maxTrees];
    }
    public Building currentTree(){
        return trees[currentTree];
    }
    public void goRight(){
        currentTree=(currentTree+1)%maxTrees;
    }
    public void goLeft(){
        currentTree=currentTree-1;
        if (currentTree<0){
            currentTree+=maxTrees;
        }
    }
    public void interact(){
        if (trees[currentTree]!=null){
            trees[currentTree].interact();
        }else{
            if (Gdx.input.getY()>1000) {
                trees[currentTree] = new Tree();
            }else{
                trees[currentTree]= new TreeCarer(this);
            }
        }
    }
    public void update(){
        for (int i=0;i<maxTrees;i++){
            if (trees[i]!=null){
                if (trees[i].deleteThis()){
                    trees[i]=null;
                }else {
                    trees[i].update();
                }
            }
        }
    }
    public void render(SpriteBatch sb){
        if (trees[currentTree]!=null){
            trees[currentTree].render(sb);
        }
    }

}
