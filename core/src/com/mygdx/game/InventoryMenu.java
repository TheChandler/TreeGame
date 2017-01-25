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
    private Texture invenMenu = new Texture("inventoryMenu1.1.png");
    private Button closeButton = new Button(0, 0, 360, 176);
    private Button[][] inventorySlots;

    private int tempx, tempy; //delete these later
    private Texture tempTex = new Texture("square180.png");
    private Texture blueButton = new Texture("tempButton.png");
    private int initSize = 180;

    public InventoryMenu(Building b) {
        System.out.println("Menu Opened");
        building = b;
        close = false;
        initializeMenu();
        tempx = -120;
        tempy = -120;
    }

    private void initializeMenu() {
        inventorySlots = new Button[5][4];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                inventorySlots[i][j] = new Button(i * initSize + 180, j * initSize + 640, i * initSize + 280, j * initSize + 760);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(invenMenu, 0, 180);
        sb.draw(blueButton, 0, 0);
        sb.draw(tempTex, tempx + 121, tempy + 600);
    }

    @Override
    public boolean close() {
        return close;
    }

    public Building building() {
        return building;
    }

    public void touch(Vector2 cords) {
        if (closeButton.check(cords)) {
            close = true;
        } else if (cords.x > 120 && cords.x < 960 && cords.y > 600 && cords.y < 1320) {

            if (inventorySlots[(int) (cords.x - initSize) / initSize][(int) (cords.y - 600) / initSize].check(cords)) {
                tempx = 120 * (int) ((cords.x - 120) / 120);
                tempy = 120 * (int) ((cords.y - 600) / 120);
                System.out.println(tempx / 120 + tempy / 120 * 6);
                if (Inventory.count[tempx / 120 + tempy / 120 * 6] > 0) {
                    // building = Inventory.count[tempx / 120 + tempy / 120 * 6];
                    Inventory.count[tempx / 120 + tempy / 120 * 6]--;
                }
                tempx = (int) (cords.x - 120 - cords.x % 120);
                tempy = (int) (cords.y - 600 - cords.y % 120);
                System.out.println(tempx + " " + tempy);
                int inv = tempx / 120 + tempy / 120 * 6;
                if (Inventory.count[inv] > 0) {
                    building = Inventory.getBuildingById(Inventory.itemId[inv]);
                }
            }

        }
    }
}
