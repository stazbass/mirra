package com.mirra.demo.dagger;

import com.badlogic.gdx.math.Vector2;
import com.mirra.demo.math.CircleMath;
import com.mirra.demo.components.clock.Clock;
import com.mirra.demo.components.clock.ClockView;
import com.mirra.demo.components.clock.time.TimeProvider;
import com.mirra.demo.entities.CurrentTime;
import com.mirra.demo.logger.GameLogger;
import com.mirra.demo.render.FramesPerSecond;
import com.mirra.demo.render.Renderer;
import com.mirra.demo.render.TextureManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class GameModule {
    @Provides
    @Singleton
    public static Clock provideClock(TimeProvider provider, ClockView clockView) {
        return new Clock(provider, clockView);
    }

    @Provides
    @Singleton
    public static TextureManager provideTextureManager(GameLogger logger) {
        return new TextureManager(logger);
    }

    @Provides
    @Singleton
    public static GameLogger provideLogger() {
        return new GameLogger();
    }

    @Provides
    @Singleton
    public static CircleMath provideCircleMath() {
        return new CircleMath();
    }

    @Provides
    @Singleton
    public static CurrentTime provideCurrentTime() {
        return new CurrentTime();
    }

    @Provides
    @Singleton
    public static TimeProvider provideTimeProvider(CurrentTime currentTime){
        return new TimeProvider(currentTime);
    }

    @Provides
    @Singleton
    public static ClockView provideClockView(){
        return new ClockView(new Vector2(1200/2, 1028/2), 7.0);
    }

    @Provides
    @Singleton
    public static FramesPerSecond provideFramesPerSecond(){
        return new FramesPerSecond();
    }

    @Provides
    @Singleton
    public static Renderer provideRenderer(TextureManager textureManager){
        return new Renderer(textureManager);
    }
}
