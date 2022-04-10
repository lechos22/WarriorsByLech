package com.lechos22j.entity;

public abstract class AIEntity extends Entity {
    public abstract void ai_tick();
    @Override
    public void tick() {
        super.tick();
        ai_tick();
    }
}
