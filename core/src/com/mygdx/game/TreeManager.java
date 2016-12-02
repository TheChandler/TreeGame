package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 11/18/2016.
 */

public class TreeManager {
    Vector2 currentTree;
    Building[][] trees;
    boolean[][] accessability;
    int maxTrees=10;
    int maxDepth=10;

    float xPos,yPos;
    float xPos2,yPos2;
    float time;

    Texture grass       =new Texture("grass.png");
    Texture underground=new Texture("underground.png");

    Texture moveTexture=grass;
    Building moveBuilding=null;

    Vector2 offset;
    public TreeManager(Forest forest){
        currentTree=new Vector2(0,0);
        trees=new Building[maxTrees][maxDepth];
        setAccessability();
        time=0;
        xPos=0;
        yPos=0;
        xPos2=0;
        yPos2=0;
        offset=new Vector2();
    }
    private void setAccessability(){
        accessability=new boolean[maxTrees][maxDepth];
        for (int i=0;i<this.trees.length;i++){
            for (int j =0;j<this.trees[i].length;j++){
                if (j==0){
                    this.accessability[i][j]=true;
                }else{
                    this.accessability[i][j]=false;
                }
            }
        }
    }
    public Building currentTree(){
        return trees[(int)currentTree.x][(int)currentTree.y];
    }
    public void goRight(){
        if (accessability[(int)(currentTree.x+1)%maxTrees][(int)currentTree.y]) {
            if (currentTree.y == 0) {
                moveTexture = grass;
            } else {
                moveTexture = underground;
            }
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.x = (currentTree.x + 1) % maxTrees;
            xPos = 1080;
            xPos2 = -1080;
            yPos2 = 0;
        }
    }
    public void goLeft(){
        if (accessability[(int)((currentTree.x+maxTrees-1)%maxTrees)][(int)currentTree.y]){
            if (currentTree.y == 0) {
                moveTexture = grass;
            } else {
                moveTexture = underground;
            }
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.x = (currentTree.x + maxTrees - 1) % maxTrees;
            xPos = -1080;
            xPos2 = 1080;
            yPos2 = 0;
        }
    }
    public void goDown(){
        if (currentTree.y+1<maxDepth&&accessability[(int)currentTree.x][(int)currentTree.y+1]) {
            if (currentTree.y==0){
                moveTexture=grass;
            }else{
                moveTexture=underground;
            }
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.y += 1;
            yPos=-1920;
            yPos2=1920;
            xPos2=0;
        }
    }
    public void goUp(){
        if (currentTree.y>0&&accessability[(int)currentTree.x][(int)(currentTree.y-1)]){
            moveTexture=underground;
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.y -= 1;
            yPos=1920;
            yPos2=-1920;
            xPos2=0;
        }
    }
    public void interact(){
        if (trees[(int)currentTree.x][(int)currentTree.y]!=null){
            trees[(int)currentTree.x][(int)currentTree.y].interact();
        }else{
            trees[(int)currentTree.x][(int)currentTree.y] = new Tree();
        }
    }
    public void update(float dt){
        time+=dt;
        if (time>.1) {
            time = 0;
            for (int j = 0; j < maxDepth; j++) {
                for (int i = 0; i < maxTrees; i++) {
                    if (trees[i][j] != null) {
                        if (trees[i][j].deleteThis()) {
                            trees[i][j] = null;
                        } else {
                            trees[i][j].update();
                        }
                    }
                }
            }
        }
        xPos*=.9;
        yPos*=.9;
        offset.set(xPos+xPos2,yPos+yPos2);
    }
    public void render(SpriteBatch sb){
        if(currentTree.y>0) {
            sb.draw(underground,xPos,yPos);
        }else{
            sb.draw(grass, xPos, yPos);
        }
        if (xPos!=0||yPos!=0) {
            sb.draw(moveTexture, xPos + xPos2, yPos + yPos2);
            if(moveBuilding!=null) {
                moveBuilding.render(sb, offset);
            }
        }
        if (trees[(int)currentTree.x][(int)currentTree.y]!=null){
            trees[(int)currentTree.x][(int)currentTree.y].render(sb,offset);
        }
    }
}
