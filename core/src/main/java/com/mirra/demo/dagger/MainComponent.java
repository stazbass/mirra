package com.mirra.demo.dagger;

import com.mirra.demo.components.clock.Clock;
import com.mirra.demo.render.FramesPerSecond;
import com.mirra.demo.render.Renderer;
import com.mirra.demo.render.TextureManager;
import dagger.Component;

import javax.inject.Singleton;

@Component(modules = {GameModule.class})
@Singleton
public interface MainComponent {
    Clock provideClock();
    TextureManager provideTextureManager();
    Renderer provideRenderer();
    FramesPerSecond provideFramesPerSecond();
}
