package com.lechos22j;

import com.lechos22j.entity.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Arena {
    private int rows = 0;
    private int columns = 0;

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    private final List<Entity> entities = new ArrayList<>();
    private final List<AIEntity> aiEntities = new ArrayList<>();
    private final List<Trap> traps = new ArrayList<>();
    private final List<PlayerEntity> players = new ArrayList<>();
    private final List<PlayerSpawn> playerSpawns = new ArrayList<>();
    public List<PlayerSpawn> getPlayerSpawns() {
        return playerSpawns;
    }
    public static Arena load(String resource) {
        Scanner scanner = new Scanner(Objects.requireNonNull(Arena.class.getResourceAsStream("/arenas/" + resource)));
        Arena arena = new Arena();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == 'p') arena.playerSpawns.add(new PlayerSpawn(i, arena.rows));
                if(line.charAt(i) == 'm') arena.addEntity(new MonsterEntity(i, arena.rows));
                if(line.charAt(i) == 'o') arena.addEntity(new ObstacleEntity(i, arena.rows));
                if(line.charAt(i) == 't') arena.traps.add(new Trap(i, arena.rows));
            }
            arena.rows++;
            arena.columns = line.length();
        }
        arena.background = Utils.loadResourceImage("/arenas/backgrounds/" + resource + ".png");
        return arena;
    }

    public void tick() {
        entities.removeIf(Entity::isDead);
        players.removeIf(Entity::isDead);
        aiEntities.removeIf(Entity::isDead);
        entities.forEach(Entity::tick);
        traps.forEach(Trap::tick);
    }

    public List<Entity> getEntities() {
        return entities;
    }
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof PlayerEntity player) {
            players.add(player);
        }
        if (entity instanceof AIEntity aiEntity) {
            aiEntities.add(aiEntity);
        }
    }
    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void addPlayers(List<PlayerEntity> players) {
        int oldSize = this.players.size();
        for(int i = 0; i < players.size(); i++) {
            addEntity(players.get(i));
            players.get(i).setX(playerSpawns.get(oldSize + i).getX());
            players.get(i).setY(playerSpawns.get(oldSize + i).getY());
        }
    }

    public List<AIEntity> getAIEntities() {
        return aiEntities;
    }

    private Image background = null;

    public Image getBackground() {
        return background;
    }
}
