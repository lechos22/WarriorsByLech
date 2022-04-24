package com.lechos22j.entity;

import com.lechos22j.GameField;

import java.awt.*;

public class Trap {
    private int attackCooldown = 100;
    public int x;
    public int y;
    public int attackStrength;
    public Trap(int x, int y) {
        this.x = x;
        this.y = y;
        this.attackStrength = 20;
    }

    public void tick() {
        if(attackCooldown <= 0) {
            Entity target = GameField.getInstance()
                    .getArena()
                    .getEntities()
                    .stream()
                    .reduce(null, (e1, e2) ->
                            e2 != null && (e2.x == x && e2.y == y)
                                    ? e2
                                    : e1
                    );
            if (target != null) {
                target.damage(attackStrength);
                attackCooldown = 100;
            }
        } else {
            attackCooldown--;
        }
    }
}
