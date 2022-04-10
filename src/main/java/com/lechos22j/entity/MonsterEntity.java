package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class MonsterEntity extends AIEntity {
    public MonsterEntity(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
    }

    @Override
    public void ai_tick() {
        //
    }

    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/monster/base.png");

    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
