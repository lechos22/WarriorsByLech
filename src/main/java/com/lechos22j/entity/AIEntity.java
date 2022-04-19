package com.lechos22j.entity;

import com.lechos22j.Arena;

public abstract class AIEntity extends Entity {
    protected static int[][] map;
    protected static void refreshMap(Arena arena) {
        map = new int[arena.getRows()][arena.getColumns()];
        for(Entity e : arena.getEntities()) {
            if(e.getY() >= 0 && e.getY() < map.length && e.getX() >= 0 && e.getX() < map[0].length) {
                if (e instanceof AIEntity || e instanceof ObstacleEntity || e instanceof ProjectileEntity) {
                    map[e.getY()][e.getX()] = -1;
                } else {
                    map[e.getY()][e.getX()] = -2;
                }
            }
        }
    }
    public abstract void ai_tick();
    @Override
    public void tick() {
        super.tick();
        ai_tick();
    }
}
