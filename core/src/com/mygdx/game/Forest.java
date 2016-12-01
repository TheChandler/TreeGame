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
import com.badlogic.gdx.input.GestureDetector;

/**
 * Created by Chandler on 11/18/2016.
 */

public class Forest extends State {
    GameStateManager gsm;
    Texture grass       =new Texture("grass.png");
    Texture sky         =new Texture("sky.png");
    Texture sunBall     =new Texture("sunBall.png");
    Texture sunRays     =new Texture("sunRays.png");
    Texture dot         =new Texture("dot.png");
    Texture underground=new Texture("underground.png");

    Texture[] numbers;

    TreeManager tm;
    float time=0;
    SwipeDetector sd=new SwipeDetector();
    MyInputProcessor mip =new MyInputProcessor();
    boolean isTouched;
    float rotation=0;

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
            numbers[i]=new Texture(String.valueOf(i)+".png");
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
                if (sd.getDirection() == 0) {
                    super.mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                    super.cam.unproject(super.mouse);
                    tm.interact();
                } else if (sd.getDirection() == 2) {
                    tm.goRight();
                } else if (sd.getDirection() == 4) {
                    tm.goLeft();
                } else if (sd.getDirection() == 1){
                    tm.goUp();
                } else if (sd.getDirection() == 3){
                    tm.goDown();
                }
            }
        }else{
            if (Gdx.input.isTouched()){
                isTouched=true;
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        time+=dt;
        if (time>0){
            time=0;
            tm.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(sky,0,0);
        if(tm.currentTree.y>0) {
            sb.draw(underground,tm.xPos,tm.yPos);
        }else{
            sb.draw(grass, tm.xPos, tm.yPos);
        }
        sb.draw(sunBall,0,0);
        sb.draw(sunRays,1080-480,1920-480,422,422,844,844,1,1,rotate(),0,0,844,844,false,false);
        sb.draw(numbers[(int)tm.currentTree.x],0,0);
        sb.draw(numbers[(int)tm.currentTree.y],100,0);
        sb.draw(dot,touchX,touchY);
        tm.render(sb);
    }
    private float rotate(){
        rotation=(float)(rotation+.03);
        return rotation;
    }

    @Override
    public void dispose() {

    }
}
