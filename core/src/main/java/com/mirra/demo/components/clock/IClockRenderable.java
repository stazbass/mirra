package com.mirra.demo.components.clock;

import com.badlogic.gdx.math.Vector2;
import com.mirra.demo.entities.RenderItem;
import com.mirra.demo.entities.TimeRecord;

import java.util.List;

public interface IClockRenderable {
    List<RenderItem> render(TimeRecord time, Vector2 origin, double scale);
}
