package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by chandler on 12/15/2016.
 */

public class InventoryMenu extends MenuClass {
    Building building;
    boolean close;
    private Texture texture=new Texture("inventoryMenu2.png");
    private Button closeButton=new Button(508,530,567,570);
    private Button[][] inventorySlots;

    private int tempx,tempy; //delete these later
    private Texture tempTex=new Texture("squar.png");
    public InventoryMenu(Building b){
        System.out.println("Menu Opened");
        building=b;
        close=false;
        initializeMenu();
        tempx=-120;
        tempy=-120;
    }
    private void initializeMenu() {
        inventorySlots=new Button[7][6];
        for (int i = 0; i <7; i++) {
            for (int j=0;j<6;j++){
                inventorySlots[i][j]=new Button(i*120+120,j*120+600,i*120+240,j*120+720);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(texture,90,479);
        sb.draw(tempTex,tempx+121,tempy+600);
    }

    @Override
    public boolean close() {
        return close;
    }
    public Building building(){
        return building;
    }

    public void touch(Vector2 cords){
        if(closeButton.check(cords)){
            close=true;
        }else if(cords.x>120&&cords.x<960&&cords.y>600&&cords.y<1320){
            tempx=(int)(cords.x-120-cords.x%120);
            tempy=(int)(cords.y-600-cords.y%120);
            System.out.println(tempx+" "+tempy);
            int inv=tempx/120+tempy/120*6;
            if (Inventory.count[inv]>0) {
                building = Inventory.getBuildingById(Inventory.itemId[inv]);
            }
        }

    }
}
