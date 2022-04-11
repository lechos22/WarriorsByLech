package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class ArrowEntity extends ProjectileEntity {
    private static final Image LEFT_IMAGE = Utils.loadResourceImage("/images/archer/projectile_left.png");
    private static final Image RIGHT_IMAGE = Utils.loadResourceImage("/images/archer/projectile_right.png");

    public ArrowEntity(Entity owner, int x, int y, int xSpeed, int ySpeed) {
        super(owner, x, y, xSpeed, ySpeed);
        this.attackStrength = 20;
    }

    @Override
    public Image getImage() {
        return xSpeed >= 0 ? RIGHT_IMAGE : LEFT_IMAGE;
    }
}
