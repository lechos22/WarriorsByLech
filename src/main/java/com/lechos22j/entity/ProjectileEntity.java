package com.lechos22j.entity;

import com.lechos22j.GameField;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ProjectileEntity extends Entity {
    protected int xSpeed, ySpeed;
    protected Entity owner;
    public ProjectileEntity(Entity owner, int x, int y, int xSpeed, int ySpeed){
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.maxHealth = 1;
        this.health = 1;
        this.maxMana = 0;
        this.mana = 0;
        this.showStats = false;
    }

    @Override
    public void tick() {
        super.tick();
        if(x < 0 || x >= GameField.getInstance().getArena().getColumns() || y < 0 || y >= GameField.getInstance().getArena().getRows()){
            setHealth(0);
        }
        if(xSpeed < 0){
            Entity e = attackLeft();
            if (e != null) {
                setHealth(0);
            }
            if (e instanceof MonsterEntity) {
                owner.heal(e.isDead() ? 20 : 0);
            }
        }
        if(xSpeed >= 0){
            Entity e = attackRight();
            if (e != null) {
                setHealth(0);
            }
            if (e instanceof MonsterEntity) {
                owner.heal(e.isDead() ? 20 : 0);
            }
        }
        x += xSpeed;
        y += ySpeed;
    }
}
