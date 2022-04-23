package com.lechos22j.entity;

import com.lechos22j.Utils;

import java.awt.*;

public class HealerEntity extends PlayerEntity {
    public HealerEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.animations.put("base", Utils.loadResourceImage("/images/healer/base.png"));
        this.animations.put("left", Utils.loadResourceImage("/images/healer/left.png"));
        this.animations.put("right", Utils.loadResourceImage("/images/healer/right.png"));
    }

    @Override
    public void attack(Entity e) {
        super.attack(e);
        heal(5);
    }
}
