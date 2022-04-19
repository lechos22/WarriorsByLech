package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Entity {
    protected int x;
    protected int y;

    protected boolean showStats = true;
    public boolean shouldShowStats() {
        return showStats;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    protected int health = 100;
    protected int maxHealth = 100;
    protected int mana = 100;
    protected int maxMana = 100;
    protected int manaRegen = 1;
    protected int attackStrength = 10;

    public void tick(){
        mana = Utils.clamp(0, mana + manaRegen, maxMana);
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = Utils.clamp(0, health, maxHealth);
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void damage(int damage){
        setHealth(getHealth() - damage);
    }
    public void heal(int amount){
        setHealth(getHealth() + amount);
    }
    public void attack(Entity e){
        e.damage(attackStrength);
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
        }
    }
    public Entity attackLeft(){
        Entity target = GameField.getInstance().getArena().getEntities().stream().reduce(null, (e1, e2) -> e2 != null && (e2.x == x - 1 && e2.y == y) ? e2 : e1);
        if (target != null) {
            attack(target);
        }
        return target;
    }
    public Entity attackRight(){
        Entity target = GameField.getInstance().getArena().getEntities().stream().reduce(null, (e1, e2) -> e2 != null && (e2.x == x + 1 && e2.y == y) ? e2 : e1);
        if (target != null) {
            attack(target);
        }
        return target;
    }
    public int getMana() {
        return mana;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMaxMana() {
        return maxMana;
    }
    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }
    public int getManaRegen() {
        return manaRegen;
    }
    public void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

    public abstract Image getImage();

    public boolean isDead() {
        return health <= 0;
    }
}
