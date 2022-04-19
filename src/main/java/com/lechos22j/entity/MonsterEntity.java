package com.lechos22j.entity;

import com.lechos22j.GameField;
import com.lechos22j.Utils;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.lechos22j.Utils.Vec2;

public class MonsterEntity extends AIEntity {
    private PlayerEntity target;
    private Vec2 direction;
    private int moveCooldown = 0;
    private int attackCooldown = 0;
    private static final int MAX_MOVE_COOLDOWN = 10;
    private static final int MAX_ATTACK_COOLDOWN = 10;

    public MonsterEntity(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.maxMana = 0;
        this.mana = maxMana;
        this.attackStrength = 10;
    }

    private void locateClosestTarget() {
        refreshMap(GameField.getInstance().getArena());
        target = null;
        double closestDistance = Double.MAX_VALUE;
        direction = new Vec2(0, 0);
        Queue<Vec2> pointQueue = new ArrayDeque<>();
        pointQueue.add(new Vec2(x, y).setRef(null));
        while (!pointQueue.isEmpty()) {
            Vec2 point = pointQueue.poll();
            if (point.x < 0 || point.x >= map[0].length || point.y < 0 || point.y >= map.length) {
                continue;
            }
            if(point.x > 0) {
                if (map[(int) point.y][(int) (point.x - 1)] == 0) {
                    map[(int) point.y][(int) (point.x - 1)] = map[(int) point.y][(int) point.x] + 1;
                    pointQueue.add(new Vec2(point.x - 1, point.y).setRef(point.getRef() == null ? new Vec2(-1, 0) : point.getRef()));
                } else if (map[(int) point.y][(int) (point.x - 1)] == -2) {
                    PlayerEntity targetTemp = GameField.getInstance().getArena().getPlayers().stream().reduce(
                            null,
                            (prev, curr) -> {
                                if (curr != null && curr.getX() == point.x - 1 && curr.getY() == point.y)
                                    return curr;
                                return prev;
                            }
                    );
                    if (targetTemp != null && closestDistance > Utils.squareDistance(x, y, targetTemp.x, targetTemp.y)) {
                        target = targetTemp;
                        closestDistance = Utils.squareDistance(x, y, target.x, target.y);
                        if(point.getRef() instanceof Vec2 direction) {
                            this.direction = direction;
                        }
                    }
                }
            }
            if(point.x < map[0].length - 1) {
                if (map[(int) point.y][(int) (point.x + 1)] == 0) {
                    map[(int) point.y][(int) (point.x + 1)] = map[(int) point.y][(int) point.x] + 1;
                    pointQueue.add(new Vec2(point.x + 1, point.y).setRef(point.getRef() == null ? new Vec2(1, 0) : point.getRef()));
                } else if (map[(int) point.y][(int) (point.x + 1)] == -2) {
                    PlayerEntity targetTemp = GameField.getInstance().getArena().getPlayers().stream().reduce(
                            null,
                            (prev, curr) -> {
                                if (curr != null && curr.getX() == point.x + 1 && curr.getY() == point.y)
                                    return curr;
                                return prev;
                            }
                    );
                    if (targetTemp != null && closestDistance > Utils.squareDistance(x, y, targetTemp.x, targetTemp.y)) {
                        target = targetTemp;
                        closestDistance = Utils.squareDistance(x, y, target.x, target.y);
                        if(point.getRef() instanceof Vec2 direction) {
                            this.direction = direction;
                        }
                    }
                }
            }
            if(point.y > 0) {
                if (map[(int) (point.y - 1)][(int) point.x] == 0) {
                    map[(int) (point.y - 1)][(int) point.x] = map[(int) point.y][(int) point.x] + 1;
                    pointQueue.add(new Vec2(point.x, point.y - 1).setRef(point.getRef() == null ? new Vec2(0, -1) : point.getRef()));
                }
            }
            if(point.y < map.length - 1) {
                if (map[(int) (point.y + 1)][(int) point.x] == 0) {
                    map[(int) (point.y + 1)][(int) point.x] = map[(int) point.y][(int) point.x] + 1;
                    pointQueue.add(new Vec2(point.x, point.y + 1).setRef(point.getRef() == null ? new Vec2(0, 1) : point.getRef()));
                }
            }
        }
    }

    @Override
    public void ai_tick() {
        locateClosestTarget();
        if (target != null) {
            if (Math.abs(x - target.x) <= 1 && y - target.y == 0 && moveCooldown >= MAX_MOVE_COOLDOWN) {
                if(attackCooldown >= MAX_ATTACK_COOLDOWN) {
                    attack(target);
                    attackCooldown = 0;
                }
            } else if (Utils.squareDistance(x, y, target.x, target.y) <= 100) {
                if (direction != null && moveCooldown >= MAX_MOVE_COOLDOWN) {
                    tryChangePosition((int) (this.x + direction.x), (int) (this.y + direction.y));
                    moveCooldown = 0;
                }
            }
        }
    }

    @Override
    public void tick() {
        moveCooldown = Math.min(moveCooldown + 1, MAX_MOVE_COOLDOWN);
        attackCooldown = Math.min(attackCooldown + 1, MAX_ATTACK_COOLDOWN);
        super.tick();
    }

    private static final Image BASE_IMAGE = Utils.loadResourceImage("/images/monster/base.png");

    @Override
    public Image getImage() {
        return BASE_IMAGE;
    }
}
