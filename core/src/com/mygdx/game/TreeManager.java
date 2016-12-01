package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 11/18/2016.
 */

public class TreeManager {
    Vector2 currentTree;
    Building[][] trees;
    int maxTrees=10;
    int maxDepth=10;

    float xPos,yPos;

    public TreeManager(Forest forest){
        currentTree=new Vector2(0,0);
        trees=new Building[maxTrees][maxDepth];
        xPos=0;
        yPos=0;
    }
    public Building currentTree(){
        return trees[(int)currentTree.x][(int)currentTree.y];
    }
    public void goRight(){
        currentTree.x=(currentTree.x+1)%maxTrees;
        xPos=1080;
    }
    public void goLeft(){
        currentTree.x=(currentTree.x+maxTrees-1)%maxTrees;
        xPos=-1080;
    }
    public void goDown(){
        if (currentTree.y+1<maxDepth) {
            currentTree.y += 1;
            yPos=-1920;
        }
    }
    public void goUp(){
        if (currentTree.y>0){
            currentTree.y -= 1;
            yPos=1920;
        }
    }
    public void interact(){
        if (trees[(int)currentTree.x][(int)currentTree.y]!=null){
            trees[(int)currentTree.x][(int)currentTree.y].interact();
        }else{
            trees[(int)currentTree.x][(int)currentTree.y] = new Tree();
        }
    }
    public void update(){
        for (int j=0;j<maxDepth;j++){
            for (int i=0;i<maxTrees;i++){
                if (trees[i][j]!=null){
                    if (trees[i][j].deleteThis()){
                        trees[i][j]=null;
                    }else {
                        trees[i][j].update();
                    }
                }
            }
        }
        xPos*=.9;
        yPos*=.9;

    }
    public void render(SpriteBatch sb){
        if (trees[(int)currentTree.x][(int)currentTree.y]!=null){
            trees[(int)currentTree.x][(int)currentTree.y].render(sb);
        }
    }

}
