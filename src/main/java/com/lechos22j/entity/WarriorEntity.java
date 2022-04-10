package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class WarriorEntity extends PlayerEntity {
    public WarriorEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.maxHealth = 200;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
        this.attackStrength = 20;
    }
    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/warrior/base.png");
    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
