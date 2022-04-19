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
    }

    @Override
    public Entity attackLeft() {
        if(this.mana >= 30) {
            GameField.getInstance().getArena().addEntity(new MagicBallEntity(this, this.getX() - 1, this.getY(), -1, 0));
            setMana(mana - 30);
        }
        return null;
    }
    @Override
    public Entity attackRight() {
        if(this.mana >= 30) {
            GameField.getInstance().getArena().addEntity(new MagicBallEntity(this, this.getX() + 1, this.getY(), 1, 0));
            setMana(mana - 30);
        }
        return null;
    }

    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/mage/base.png");
    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
