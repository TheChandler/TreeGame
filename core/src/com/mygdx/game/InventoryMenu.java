package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by chandler on 12/15/2016.
 */

public class InventoryMenu extends MenuClass {
    Building building;      //This is basically a way for this menu to send information to the parent class. There should probably be a better way to do this. Maybe just sending back
                            // a number and the parent class, TreeManager, knows what the number means?
    boolean close;         //This acts as a flag that the parent class checks to know if it should close the menu.
    private Texture invenMenu = new Texture("inventoryMenu1.1.png");
    private Button closeButton = new Button(0, 0, 299, 160);    //range of the button that closes the menu
    private Button inventoryRange=new Button(96,65,985,654);
    //^^^^ The bottom left of all the inventory spaces to the top right of all of the inventory spaces.
    // Not really a button it just checks if the player clicked within the range. Prevents out of bounds errors for the inventory array.

    private int tempx, tempy;                                   //these are the coordinates of the selection.
    private Texture selection = new Texture("square140.png");       //this is the image that goes over the currently selected box.
    private Texture exitButton = new Texture("tempButton.png"); //The button you press to close the menu
    private int initSize = 180;

    public InventoryMenu(Building b) {
        System.out.println("Menu Opened");
        building = b;// I can't remember exactly how this interacts with TreeManager. It might be unimportant.
        close = false;//The menus shouldn't close initially so this is false.
        tempx = -3000; //initially the player shouldn't see any of the boxes as selected. This is the easiest way to do that
        tempy = -120;
    }
    @Override
    public void render(SpriteBatch sb) {
        sb.draw(invenMenu, 0, 130);
        sb.draw(exitButton, 0, 0);
        sb.draw(selection, tempx + 96, tempy + 225);//plus 96 and 225 to move it up and to the right to match with where the menu boxes start.
    }

    @Override
    public boolean close() {
        return close;
    }

    public Building building() {
        return building;
    }

    public void touch(Vector2 cords) {
        if (closeButton.check(cords)) {//checks if the player is trying to close the menu
            close = true;
        } else if (inventoryRange.check(cords)) {//checks if the player is touching within the appropriate area to select an inventory item.
            processInventorySelection(cords);    //there's a lot of stuff to do if the player is touching in the appropriate area so it's its own function
        }
    }
    private void processInventorySelection(Vector2 cords){
        //Offsetting the coordinates because the actual first box you can select is at 96,225. This makes it so that I can do calculations with that as 0,0
        int x= (int)cords.x-96;
        int y= (int)cords.y-225;
        //the space from the bottom left of one box to the bottom right of the next is 150 pixels.
        //so we can divide by 150 to determine how many boxes over and up the picked
        x/=150;
        y/=150;
        tempx=(int)x*150;
        tempy=(int)y*150;
        //this seems pointless to divide then multiply by the same number, but when they're divided by 150 it only gives a whole number. Multiplying that number by 150 lines it up with the boxes on the menu image.
        //otherwise the selection box would show up exactly where the player pressed. Not lined up
    }
}
