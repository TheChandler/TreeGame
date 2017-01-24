package com.mygdx.game;
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
    boolean selectingTree;
    int maxTrees=10;
    int maxDepth=10;

    float xPos,yPos;
    float xPos2,yPos2;
    float time;

    Texture grass       =new Texture("grass.png");
    Texture underground=new Texture("underground.png");
    Texture MenuButton = new Texture("MenuButton.png");

    Texture moveTexture=grass;
    Building moveBuilding=null;

    Vector2 offset1,offset2;
    private boolean isSliding;

    MenuClass menu;
    Button openMenu = new Button(new Vector2(0,180),new Vector2(1080,545));
    Button openInvenMenu = new Button(new Vector2(0,0),new Vector2(180,180));
    public TreeManager(Forest forest){
        currentTree=new Vector2(0,0);
        trees=new Building[maxTrees][maxDepth];
        setAccessibility();
        time=0;
        xPos=0;
        yPos=0;
        xPos2=0;
        yPos2=0;
        offset1=new Vector2();
        offset2=new Vector2();
        isSliding=false;
        selectingTree=false;
        Inventory.initialize();
    }
    private void setAccessibility(){
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
            isSliding=true;
            moveTexture=getBackground();
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.x = (currentTree.x + 1) % maxTrees;
            xPos = 1080;
            xPos2 = -1080;
            yPos2 = 0;
        }
    }
    public void goLeft(){
        if (accessability[(int)((currentTree.x+maxTrees-1)%maxTrees)][(int)currentTree.y]){
            isSliding=true;
            moveTexture = getBackground();
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.x = (currentTree.x + maxTrees - 1) % maxTrees;
            xPos = -1080;
            xPos2 = 1080;
            yPos2 = 0;
        }
    }
    public void goDown(){
        if (currentTree.y+1<maxDepth&&accessability[(int)currentTree.x][(int)currentTree.y+1]) {
            isSliding=true;
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
            isSliding=true;
            moveTexture=underground;
            moveBuilding=trees[(int)currentTree.x][(int)currentTree.y];
            currentTree.y -= 1;
            yPos=1920;
            yPos2=-1920;
            xPos2=0;
        }
    }
    public void interact(Vector2 cords) {
        if (trees[(int) currentTree.x][(int) currentTree.y] != null) {

            if (openMenu.check(cords)) {
                openMenu();
            } else {
                trees[(int) currentTree.x][(int) currentTree.y].interact();
            }
        }
            //if (openInvenMenu.check(cords)) {
                openInventoryMenu();


        }

    public void handleInput(SwipeDetector sd,Vector2 cords){
        if (menu!=null){
            menu.touch(cords);
        }else {
            switch (sd.getDirection()) {
                case (0):
                    interact(cords);
                    break;
                case (1):
                    goUp();
                    break;
                case (2):
                    goRight();
                    break;
                case (3):
                    goDown();
                    break;
                case (4):
                    goLeft();
                    break;
            }
        }
    }
    public void update(float dt){
        if (menu!=null) {
            if (menu.close()) {
                if (selectingTree) {
                    trees[(int) currentTree.x][(int) currentTree.y] = menu.building();
                    selectingTree=false;
                }
                menu = null;
            }
        }
        time+=dt;
        if (time>.45) {
            time -= .45;
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
    }

    private void slideOver(){
        xPos*=.9;
        yPos*=.9;
        if (Math.abs(xPos)<1&&Math.abs(yPos)<1){
            xPos=0;
            yPos=0;
            moveBuilding=null;
            isSliding=false;
        }
        offset1.set(xPos,yPos);
        offset2.set(xPos+xPos2,yPos+yPos2);
    }
    public void openMenu(){
        if (currentTree()!=null) {
            menu = new Menu(currentTree());
        }
    }
    public void openInventoryMenu(){
        menu=new InventoryMenu(currentTree());
        selectingTree=true;
    }

    private Texture getBackground(){
        if (currentTree.y>0){
            return underground;
        }
        return grass;
    }
    public void render(SpriteBatch sb){

        if (isSliding) {
            slideOver();
            sb.draw(moveTexture, xPos + xPos2, yPos + yPos2+960);
            if(moveBuilding!=null) {
                moveBuilding.render(sb, offset2);
            }
        }
        sb.draw(getBackground(), xPos, yPos+960);
        if (trees[(int)currentTree.x][(int)currentTree.y]!=null){
            trees[(int)currentTree.x][(int)currentTree.y].render(sb,offset1);
        }
        sb.draw(MenuButton,0,0);
        if (menu!=null){
            menu.render(sb);
        }
    }
}
