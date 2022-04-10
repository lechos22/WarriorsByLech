package com.lechos22j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

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
        JTextField playersCount = new JTextField("2");
        playersCount.setToolTipText("Players count");
        playersCount.setBounds(10, 50, 200, 30);
        add(playersCount);
        JList<JComponent> playersOptionsList = new JList<>();
        playersOptionsList.setBounds(220, 10, 200, 300);
        playersOptionsList.setLayout(new BoxLayout(playersOptionsList, BoxLayout.Y_AXIS));
        for (int i = 0; i < Integer.parseInt(playersCount.getText()); i++) {
            playersOptionsList.add(new JLabel("Player " + (i + 1)));
        }
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
