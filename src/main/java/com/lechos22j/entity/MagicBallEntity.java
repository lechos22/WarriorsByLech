package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class MagicBallEntity extends ProjectileEntity {
    private static final Image IMAGE = Utils.loadResourceImage("/images/mage/projectile.png");

    public MagicBallEntity(Entity owner, int x, int y, int xSpeed, int ySpeed) {
        super(owner, x, y, xSpeed, ySpeed);
        this.maxHealth = 4;
        this.health = 4;
        this.attackStrength = 40;
    }

    @Override
    public void tick() {
        this.damage(1);
        super.tick();
    }

    @Override
    public Image getImage() {
        return IMAGE;
    }
}
