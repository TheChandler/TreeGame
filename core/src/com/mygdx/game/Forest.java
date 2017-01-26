package com.mygdx.game;

import java.util.Queue;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chandler on 11/18/2016.
 */



//this class is for the more technical stuff I guess. it probably shouldn't be named forest, but I was making up the names as I went with out any Idea what class was going to do what.
//It handles stuff like transforming the player's touch input to be consistant across devices. (the input's location is given in terms of screen coordinates, which change with screen dimensions.
//it also does stuff like the numbers determining when a swipe happens. If the player is doing a new touch or if they were already pressing somewhere. without that when the player holds down the game will interpret as a new touch every frame
//This class probably shouldn't be drawing anything at all really, but I'll fix it later. Move the textures into tree manager
public class Forest extends State {
    Texture sky         =new Texture("sky.png");
    Texture botMenu =  new Texture("menuBottom1.0.png");
    Texture[] numbers;
    TreeManager tm;
    SwipeDetector sd=new SwipeDetector();
    boolean isTouched;
    static int touchX=0,touchY=0;
    public Forest(GameStateManager gsm){
        super(gsm);
        tm=new TreeManager(this);
        isTouched=false;
        setNumberTextures();
    }
    private void setNumberTextures(){
        numbers=new Texture[10];
        for (int i=0;i<10;i++){
            numbers[i]=new Texture("numbers\\"+String.valueOf(i)+".png");
        }
    }


    private void adjustTouch(){
        touchX=(int)(Gdx.input.getX()*((float)TreeGame.gameWidth/(float)Gdx.graphics.getWidth()));
        touchY=(int)((TreeGame.gameHeight)-(Gdx.input.getY()*((float)TreeGame.gameHeight/(float)Gdx.graphics.getHeight())));
    }
    @Override
    protected void handleInput() {
        adjustTouch();
        sd.handleInput();
        if (isTouched) {
            sd.handleInput();
            if (Gdx.input.isTouched()) {
                isTouched = true;
            } else {
                isTouched=false;
                handleTouch();
            }
        }else{
            if (Gdx.input.isTouched()){
                isTouched=true;
            }
        }
    }
    private void handleTouch(){
        Vector2 cords= new Vector2(touchX,touchY);
        tm.handleInput(sd,cords);
    }
    @Override
    public void update(float dt) {
        handleInput();
        tm.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.draw(sky,0,0);
        tm.render(sb);

        sb.draw(botMenu,0,-180);//why not just make the image not have a 180px space?

        tm.render(sb);


        sb.draw(numbers[(int)tm.currentTree.x],0,0);
        sb.draw(numbers[(int)tm.currentTree.y],100,0);


    }

    @Override
    public void dispose() {

    }
}
