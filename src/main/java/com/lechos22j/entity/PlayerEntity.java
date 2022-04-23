package com.lechos22j.entity;

import com.lechos22j.ConfigReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class PlayerEntity extends Entity {
    protected String name;
    protected int up_key;
    protected int down_key;
    protected int left_key;
    protected int right_key;
    protected int attack_right_key;
    protected int attack_left_key;

    protected String animation = "base";
    protected Map<String, Image> animations;
    protected int animationLength = 0;

    protected PlayerEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        this.name = ConfigReader.getConfig().get("player" + player_id + "_name");
        this.up_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_up")).getInt(null);
        this.down_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_down")).getInt(null);
        this.left_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_left")).getInt(null);
        this.right_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_right")).getInt(null);
        this.attack_left_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_attack_left")).getInt(null);
        this.attack_right_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_attack_right")).getInt(null);
        this.animations = new HashMap<>();
    }

    @Override
    public Entity attackLeft() {
        Entity e = super.attackLeft();
        if(e instanceof MonsterEntity) {
            heal(e.isDead() ? 20 : 0);
        }
        animation = "left";
        animationLength = 5;
        return e;
    }

    @Override
    public Entity attackRight() {
        Entity e = super.attackRight();
        if(e instanceof MonsterEntity) {
            heal(e.isDead() ? 20 : 0);
        }
        animation = "right";
        animationLength = 5;
        return e;
    }

    public void keyPressed(int k){
        if(k == up_key){
            tryChangePosition(x, y - 1);
        }
        if(k == down_key){
            tryChangePosition(x, y + 1);
        }
        if(k == left_key){
            tryChangePosition(x - 1, y);
        }
        if(k == right_key){
            tryChangePosition(x + 1, y);
        }
        if(k == attack_left_key){
            attackLeft();
        }
        if(k == attack_right_key){
            attackRight();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public Image getImage() {
        if (animationLength <= 0)
            animation = "base";
        else
            animationLength--;
        return animations.get(animation);
    }
}
