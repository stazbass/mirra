package com.mirra.demo.components.clock;

import com.badlogic.gdx.utils.Disposable;
import com.mirra.demo.components.clock.time.TimeProvider;
import com.mirra.demo.entities.RenderItem;
import com.mirra.demo.render.Renderable;

import javax.inject.Inject;
import java.util.List;

public class Clock implements Disposable, Renderable {
    private ClockView clockView;
    private TimeProvider timeProvider;

    @Inject
    public Clock(TimeProvider provider, ClockView clockView) {
        this.timeProvider = provider;
        this.clockView = clockView;
    }


    @Override
    public List<RenderItem> render() {
        return clockView.render(timeProvider.getTimeRecord());
    }

    @Override
    public void dispose() {
    }
}
