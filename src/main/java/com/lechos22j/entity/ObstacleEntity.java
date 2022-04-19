package com.lechos22j.entity;

import java.awt.*;

public class ObstacleEntity extends Entity {
    public ObstacleEntity(int x, int y) {
        this.x = x;
        this.y = y;
        this.health = Integer.MAX_VALUE;
        this.showStats = false;
    }
    @Override
    public Image getImage() {
        return null;
    }
}
