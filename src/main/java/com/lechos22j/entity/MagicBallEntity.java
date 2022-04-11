package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class MagicBallEntity extends ProjectileEntity {
    private static final Image IMAGE = Utils.loadResourceImage("/images/mage/projectile.png");

    public MagicBallEntity(Entity owner, int x, int y, int xSpeed, int ySpeed) {
        super(owner, x, y, xSpeed, ySpeed);
        this.attackStrength = 35;
    }

    @Override
    public Image getImage() {
        return IMAGE;
    }
}
