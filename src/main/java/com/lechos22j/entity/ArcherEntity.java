package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

import java.awt.*;

public class ArcherEntity extends PlayerEntity {
    private int arrowCountMax;
    private int arrowCount;
    public ArcherEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.maxHealth = 200;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
        this.arrowCountMax = 15;
        this.arrowCount = arrowCountMax;
    }

    @Override
    public Entity attackLeft() {
        if(arrowCount > 0) {
            arrowCount--;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), -1, 0));
        }
        return null;
    }
    @Override
    public Entity attackRight() {
        if(arrowCount > 0) {
            arrowCount--;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), 1, 0));
        }
        return null;
    }

    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/archer/base.png");
    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
