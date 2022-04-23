package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

public class ArcherEntity extends PlayerEntity {
    private int arrowCooldown;
    public ArcherEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        super(player_id);
        this.maxHealth = 200;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
        this.arrowCooldown = 0;
        this.animations.put("base", Utils.loadResourceImage("/images/archer/base.png"));
        this.animations.put("left", Utils.loadResourceImage("/images/archer/left.png"));
        this.animations.put("right", Utils.loadResourceImage("/images/archer/right.png"));
    }

    @Override
    public Entity attackLeft() {
        if(arrowCooldown <= 0) {
            arrowCooldown = 5;
            animation = "left";
            animationLength = 5;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), -1, 0));
        }
        return null;
    }
    @Override
    public Entity attackRight() {
        if(arrowCooldown <= 0) {
            arrowCooldown = 5;
            animation = "right";
            animationLength = 5;
            GameField.getInstance().getArena().addEntity(new ArrowEntity(this, this.getX(), this.getY(), 1, 0));
        }
        return null;
    }

    @Override
    public void tick(){
        arrowCooldown--;
    }
}
