package com.lechos22j;

import com.lechos22j.entity.Entity;
import com.lechos22j.entity.PlayerEntity;

import javax.swing.*;
import java.awt.event.KeyAdapter;

public class GameField extends JPanel {
    private GameField() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                for (PlayerEntity player : GameField.getInstance().arena.getPlayers()) {
                    player.keyPressed(e.getKeyCode());
                }
                GameField.getInstance().repaint();
            }
        });
    }
    private static GameField instance;
    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }
    private Arena arena;
    public void setArena(Arena arena) {
        this.arena = arena;
        setSize(arena.getColumns() * 40, arena.getRows() * 80);
        MainWindow.getInstance().setSize((arena.getColumns() + 1) * 40, (arena.getRows() + 1) * 80);
    }
    public Arena getArena() {
        return arena;
    }
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        if (arena != null) {
            for (Entity entity : arena.getEntities()) {
                g.drawImage(entity.getImage(), entity.getX() * 40, entity.getY() * 80, this);
                g.drawString(String.valueOf(entity.getHealth()), entity.getX() * 40, entity.getY() * 80 + 12);
                g.drawString(String.valueOf(entity.getMana()), entity.getX() * 40, entity.getY() * 80 + 26);
            }
        }
    }
    public void gameOver(){
        JOptionPane.showMessageDialog(this, "Game Over");
        System.exit(0);
    }
}