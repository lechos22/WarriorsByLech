package com.lechos22j;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuWindow extends JFrame {
    private MenuWindow() {
        super("Menu - WarriorsByLech");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextField arenaField = new JTextField("Syberia");
        arenaField.setToolTipText("Arena name");
        arenaField.setBounds(10, 10, 200, 30);
        add(arenaField);
        AtomicInteger playersCount = new AtomicInteger(2);
        JButton addPlayerButton = new JButton("players++");
        addPlayerButton.setBounds(10, 50, 90, 30);
        JButton removePlayerButton = new JButton("players--");
        removePlayerButton.setBounds(110, 50, 90, 30);
        add(addPlayerButton);
        add(removePlayerButton);
        JList<JComponent> playersOptionsList = new JList<JComponent>();
        AtomicBoolean playersOptionsListLocked = new AtomicBoolean(true);
        playersOptionsList.setBounds(220, 10, 200, 300);
        playersOptionsList.setLayout(new BoxLayout(playersOptionsList, BoxLayout.Y_AXIS));
        for (int i = 0; i < playersCount.get(); i++) {
            playersOptionsList.add(new JLabel("Player " + (i + 1)));
        }
        playersOptionsListLocked.set(false);
        addPlayerButton.addActionListener(e -> {
            while (playersOptionsListLocked.get()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            playersOptionsListLocked.set(true);
            playersCount.incrementAndGet();
            playersOptionsList.add(new JLabel("Player " + playersCount.get()));
            playersOptionsList.validate();
            playersOptionsListLocked.set(false);
            repaint();
        });
        removePlayerButton.addActionListener(e -> {
            while (playersOptionsListLocked.get()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            if (playersCount.get() <= 2){
                return;
            }
            playersOptionsListLocked.set(true);
            playersOptionsList.remove(playersCount.getAndDecrement());
            playersOptionsList.validate();
            playersOptionsListLocked.set(false);
            repaint();
        });
        add(playersOptionsList);
        JButton startButton = new JButton("Start game!");
        startButton.setBounds(10, 100, 200, 50);
        add(startButton);
        setLayout(null);
        startButton.addActionListener(e -> {
            Main.running = true;
        });
    }
    private static MenuWindow instance;
    public static MenuWindow getInstance() {
        if (instance == null) {
            instance = new MenuWindow();
        }
        return instance;
    }
}
