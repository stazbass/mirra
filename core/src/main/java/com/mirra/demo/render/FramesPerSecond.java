package com.mirra.demo.render;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;
import java.util.Queue;

public class FramesPerSecond {
    private BitmapFont font;
    private long lastTime = 0;
    private long currentFPS = 0;
    private long lastFPS = 0;
    private static final int FRAME_TIME_MEASURE_COUNT = 100;
    private Queue<Long>  frameMeasures = new LinkedList<>();
    private long frameStartTime = 0;
    private long frameTimeAvarage = 0;

    private long itemsRendered = 0;
    public FramesPerSecond(){
        font = new BitmapFont();
    }

    public void frameTimeStart(){
        frameStartTime = System.nanoTime();
    }

    public void frameTimeEnd(Long itemsRendered){
        frameMeasures.add(System.nanoTime() - frameStartTime);
        if(frameMeasures.size() > FRAME_TIME_MEASURE_COUNT){
            frameTimeAvarage = frameMeasures.stream().reduce((x,y)->{
                return (x + y)/2;
            }).get();
            frameMeasures.clear();
        }
        this.itemsRendered = itemsRendered;
    }

    public void draw(SpriteBatch batch){
        long time = System.currentTimeMillis();
        if(time - lastTime < 1000){
            currentFPS++;
        }else{
            lastTime = time;
            lastFPS = currentFPS;
            currentFPS = 0;
        }
        batch.begin();
        font.draw(batch, "FPS : " + String.valueOf(lastFPS) + " avg frame time (ms): " + String.valueOf(frameTimeAvarage/1000) + " Items rendered : "+ itemsRendered, 20, 20);
        batch.end();
    }
}
