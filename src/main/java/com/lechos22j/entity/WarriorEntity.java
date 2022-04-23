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
        this.animations.put("base", Utils.loadResourceImage("/images/warrior/base.png"));
        this.animations.put("left", Utils.loadResourceImage("/images/warrior/left.png"));
        this.animations.put("right", Utils.loadResourceImage("/images/warrior/right.png"));
    }
}
