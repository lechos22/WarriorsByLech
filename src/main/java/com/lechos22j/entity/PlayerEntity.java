package com.lechos22j.entity;

import com.lechos22j.ConfigReader;
import com.lechos22j.GameField;

import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class PlayerEntity extends Entity {
    protected String name;
    protected int up_key;
    protected int down_key;
    protected int left_key;
    protected int right_key;
    protected int attack_right_key;
    protected int attack_left_key;

    protected PlayerEntity(int player_id) throws NoSuchFieldException, IllegalAccessException {
        this.name = ConfigReader.getConfig().get("player" + player_id + "_name");
        this.up_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_up")).getInt(null);
        this.down_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_down")).getInt(null);
        this.left_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_left")).getInt(null);
        this.right_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_right")).getInt(null);
        this.attack_left_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_attack_left")).getInt(null);
        this.attack_right_key = KeyEvent.class.getField(ConfigReader.getConfig().get("player" + player_id + "_attack_right")).getInt(null);
    }

    public void tryChangePosition(int x, int y){
        var arena  = GameField.getInstance().getArena();
        AtomicBoolean canMove = new AtomicBoolean(x < arena.getColumns() && x >= 0 && y < arena.getRows() && y >= 0);
        if(canMove.get()) arena.getEntities().forEach(e -> {
            if(e.getX() == x && e.getY() == y) {
                canMove.set(false);
            }
        });
        if(canMove.get()) {
            this.x = x;
            this.y = y;
        } else {
            this.damage(10);
        }
    }

    @Override
    public Entity attackLeft() {
        Entity e = super.attackLeft();
        if(e instanceof MonsterEntity) {
            heal(e.isDead() ? 20 : 0);
        }
        return e;
    }

    @Override
    public Entity attackRight() {
        Entity e = super.attackRight();
        if(e instanceof MonsterEntity) {
            heal(e.isDead() ? 20 : 0);
        }
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
}
