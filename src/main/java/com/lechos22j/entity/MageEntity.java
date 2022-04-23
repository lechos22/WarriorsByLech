package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

import java.awt.*;

public class MageEntity extends PlayerEntity {
    public MageEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.maxHealth = 200;
        this.health = maxHealth;
        this.maxMana = 100;
        this.mana = maxMana;
        this.animations.put("base", Utils.loadResourceImage("/images/mage/base.png"));
        this.animations.put("left", Utils.loadResourceImage("/images/mage/left.png"));
        this.animations.put("right", Utils.loadResourceImage("/images/mage/right.png"));
    }

    @Override
    public Entity attackLeft() {
        if(this.mana >= 30) {
            if(this.getX() - 1 >= 0) {
                GameField.getInstance().getArena().addEntity(new MagicBallEntity(this, this.getX() - 1, this.getY(), -1, 0));
                setMana(mana - 30);
            }
            animation = "left";
            animationLength = 5;
        }
        return null;
    }
    @Override
    public Entity attackRight() {
        if(this.mana >= 30) {
            if(this.getX() + 1 < GameField.getInstance().getWidth()) {
                GameField.getInstance().getArena().addEntity(new MagicBallEntity(this, this.getX() + 1, this.getY(), 1, 0));
                setMana(mana - 30);
            }
            animation = "right";
            animationLength = 5;
        }
        return null;
    }
}
