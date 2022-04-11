package com.lechos22j;

import com.lechos22j.entity.ArcherEntity;
import com.lechos22j.entity.MageEntity;
import com.lechos22j.entity.PlayerEntity;
import com.lechos22j.entity.WarriorEntity;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static boolean running = false;
    public static void main(String[] args) throws Throwable {
        MenuWindow.getInstance().setVisible(true);
        while (!running) Thread.sleep(100);
        MenuWindow.getInstance().setVisible(false);
        List<PlayerEntity> players = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(ConfigReader.getConfig().get("players_count")); i++) {
            switch(ConfigReader.getConfig().get("player" + i)){
                case "warrior" -> players.add(new WarriorEntity(i));
                case "archer" -> players.add(new ArcherEntity(i));
                case "mage" -> players.add(new MageEntity(i));
            }
        }
        GameField gameField = GameField.getInstance();
        gameField.setArena(Arena.load(ConfigReader.getConfig().get("arena")));
        Arena arena = GameField.getInstance().getArena();
        arena.addPlayers(players);
        Thread mainLoop = new Thread(()->{
            try {
                while (true){
                    Thread.sleep(50);
                    arena.tick();
                    gameField.repaint();
                    if(arena.getPlayers().size() + arena.getAIEntities().size() == 1) throw new InterruptedException();
                }
            } catch (InterruptedException ignored){}
            gameField.gameOver();
        });
        mainLoop.start();
    }
}
