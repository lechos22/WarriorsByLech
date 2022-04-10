package com.lechos22j;

import javax.swing.*;
import java.util.Map;

public class MainWindow extends JFrame {
    private MainWindow() {
        super("WarriorsByLech");
        Map<String, String> config = ConfigReader.getConfig();
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(GameField.getInstance());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private static MainWindow instance;
    public static MainWindow getInstance() {
        if (instance == null) {
            instance = new MainWindow();
        }
        return instance;
    }
}
