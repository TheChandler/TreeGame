package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 11/18/2016.
 */

public class TreeManager {
    //This is the class that actually does a lot of stuff.
    //it has stuff that should probably be removed like the ability to go up or down to a new tree spot and checking if the player is able to scroll to the next tree spot.
    Vector2 currentTree;        //It's a vector2 because it's the location of tree currently on screen. This is probably a dumb thing to do. could just be int x,y;
    Building[][] trees;         // a 2d array of building objects. Probably doesn't need to be 2d unless we're going to add trees above or below our current trees
    boolean[][] accessability; //this checks if the player can go the spot they are trying to go to. Like when they swipe left or right.
    boolean selectingTree;     //This is part of a system that should be reworked. When a menu is opened this can be set to true. Then when the menu is closed it grabs the building from the menu and puts it in the current spot
    int maxTrees=10;
    int maxDepth=10;  //another thing for moving up/down

    float xPos,yPos;    //rendering posistion of the main tree you're looking at
    float xPos2,yPos2;  //position of the tree that is sliding off the screen
    float time;         //I actually don't know what this is at all. Did you add this?

    Texture grass       =new Texture("grass.png");
    Texture underground=new Texture("underground.png");
    Texture MenuButton = new Texture("MenuButton.png");

    Texture moveTexture=grass;  //texture of the background that is moving off screen. Probably doesn't need to exist unless we're going to have different backgrounds on each tree
    Building moveBuilding=null; //the tree that's moving off screen

    Vector2 offset1,offset2;//This is how much each screen is offset by when it's moving. I think we only need 1 of these.
    private boolean isSliding;// Something to check if the game needs to be drawing 2 seperate trees, backgrounds ect. It only needs to draw two while the player is switching between tree spots.

    MenuClass menu; //This is the generic menu class. All menus you want to open on this screen should extend the menu class.
    Button openMenu = new Button(new Vector2(880,1720),new Vector2(1920,1080));//we probably don't need this anymore. This opens the stats. I moved it to the top right corner for now. Though atm you can't open it because you can't get a tree in the game

    Button openInvenMenu = new Button(0,0,360,180);
    Button openToolsMenu = new Button(360,0,720,180);
    Button openShopMenu = new Button(720,0,1080,180);
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
    //accessibility refers to the ability to navigate to each tree spot.
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
    //this just returns the tree that's currently on screen
    public Building currentTree(){
        return trees[(int)currentTree.x][(int)currentTree.y];
    }
   //Below methods are for navigation. Godown and goUp aren't currently used
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

        if (openInvenMenu.check(cords)) {
            openInventoryMenu();
        }else if(openToolsMenu.check(cords)){
            openToolMenu();
        } else if (openShopMenu.check(cords)) {
            openShop();
        }else
        //it can only interact if the tree exitst and none of the three menu buttons were hit
        if (trees[(int) currentTree.x][(int) currentTree.y] != null) {
            //checks if stat menu should open
            if (openMenu.check(cords)) {
                openMenu();
            } else {
                //if the player didn't try to open the menu it calls the tree.interact method. What that does is up to the tree.
                trees[(int) currentTree.x][(int) currentTree.y].interact();}
            }
        }


    //this is just the controls. only happens if the player tapped the screen. 0 is no swipe 1 is swiped up 2 is swiped right 3 is down
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
    //Update doesn't get called at a regular pace. When the game slows down it gets called less often. float dt stands for delta time. This can be used to account for the time change, but I don't really use it
    public void update(float dt){
        //if the menu is open it checks if it should close it.
        if (menu!=null) {
            if (menu.close()) {
                //if the menu should be closed it checks if the player was selecting a tree. if they player was then it takes the tree out of the menu and puts it in the current spot
                if (selectingTree) {
                    trees[(int) currentTree.x][(int) currentTree.y] = menu.building();
                    selectingTree=false;
                }
                menu = null;
            }
        }
        //this is the only place dt is used. it limits updates to only once every .45 seconds. these limits only apply to the trees.
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
    public void openToolMenu(){
        menu = new ToolsMenu();
    }
    public void openShop(){
        menu = new ShopMenu();
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
