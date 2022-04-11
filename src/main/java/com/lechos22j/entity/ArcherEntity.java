package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

import java.awt.*;

public class ArcherEntity extends PlayerEntity {
    private int arrowCooldown;
    public ArcherEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.maxHealth = 200;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
        this.arrowCooldown = 0;
    }

    @Override
    public Entity attackLeft() {
        if(arrowCooldown <= 0) {
            arrowCooldown = 5;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), -1, 0));
        }
        return null;
    }
    @Override
    public Entity attackRight() {
        if(arrowCooldown <= 0) {
            arrowCooldown = 5;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), 1, 0));
        }
        return null;
    }

    @Override
    public void tick(){
        arrowCooldown--;
    }

    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/archer/base.png");
    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
