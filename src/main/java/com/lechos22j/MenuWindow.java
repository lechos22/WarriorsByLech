package com.lechos22j;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuWindow extends JFrame {
    private static class PlayerOptions extends JComponent {
        private final JTextField className;
        private final JTextField playerName;
        private final JTextField leftButton;
        private final JTextField rightButton;
        private final JTextField upButton;
        private final JTextField downButton;
        private final JTextField attackLeftButton;
        private final JTextField attackRightButton;
        private final int playerNumber;
        public PlayerOptions(int playerNumber) {
            this.playerNumber = playerNumber;
            setLayout(new GridLayout(9, 2));
            add(new JLabel("Player " + playerNumber + ":"));
            add(new JComponent() {});
            if(ConfigReader.getConfig().containsKey("player" + playerNumber)) {
                add(new JLabel("Class name:"));
                className = new JTextField(ConfigReader.getConfig().get("player" + playerNumber));
                add(className);
                add(new JLabel("Name:"));
                playerName = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_name"));
                add(playerName);
                add(new JLabel("Left button:"));
                leftButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_left"));
                add(leftButton);
                add(new JLabel("Right button:"));
                rightButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_right"));
                add(rightButton);
                add(new JLabel("Up button:"));
                upButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_up"));
                add(upButton);
                add(new JLabel("Down button:"));
                downButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_down"));
                add(downButton);
                add(new JLabel("Attack left button:"));
                attackLeftButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_attack_left"));
                add(attackLeftButton);
                add(new JLabel("Attack right button:"));
                attackRightButton = new JTextField(ConfigReader.getConfig().get("player" + playerNumber + "_attack_right"));
                add(attackRightButton);
            } else {
                add(new JLabel("Class name:"));
                className = new JTextField();
                add(className);
                add(new JLabel("Name:"));
                playerName = new JTextField();
                add(playerName);
                add(new JLabel("Left button:"));
                leftButton = new JTextField();
                add(leftButton);
                add(new JLabel("Right button:"));
                rightButton = new JTextField();
                add(rightButton);
                add(new JLabel("Up button:"));
                upButton = new JTextField();
                add(upButton);
                add(new JLabel("Down button:"));
                downButton = new JTextField();
                add(downButton);
                add(new JLabel("Attack left button:"));
                attackLeftButton = new JTextField();
                add(attackLeftButton);
                add(new JLabel("Attack right button:"));
                attackRightButton = new JTextField();
                add(attackRightButton);
            }
        }
        public String getClassName() {
            return className.getText();
        }
        public String getPlayerName() {
            return playerName.getText();
        }
        public String getLeftButton() {
            return leftButton.getText();
        }
        public String getRightButton() {
            return rightButton.getText();
        }
        public String getUpButton() {
            return upButton.getText();
        }
        public String getDownButton() {
            return downButton.getText();
        }
        public void apply() {
            ConfigReader.getConfig().put("player" + playerNumber, className.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_name", playerName.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_left", leftButton.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_right", rightButton.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_up", upButton.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_down", downButton.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_attack_left", attackLeftButton.getText());
            ConfigReader.getConfig().put("player" + playerNumber + "_attack_right", attackRightButton.getText());
        }
    }
    private final JButton addPlayerButton;
    private final JButton removePlayerButton;
    private final JTextField arenaField;
    private final JComponent playersOptionsList;
    private final JButton saveButton;
    private final JButton startButton;
    private final AtomicBoolean playersOptionsListLocked;
    private final AtomicInteger playersCount;
    private MenuWindow() {
        super("Menu - WarriorsByLech");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        arenaField = new JTextField(ConfigReader.getConfig().get("arena"));
        arenaField.setToolTipText("Arena name");
        arenaField.setBounds(10, 10, 200, 30);
        add(arenaField);
        playersCount = new AtomicInteger(Integer.parseInt(ConfigReader.getConfig().get("players_count")));
        addPlayerButton = new JButton("players++");
        addPlayerButton.setBounds(10, 50, 90, 30);
        removePlayerButton = new JButton("players--");
        removePlayerButton.setBounds(110, 50, 90, 30);
        add(addPlayerButton);
        add(removePlayerButton);
        playersOptionsList = new JComponent(){};
        playersOptionsListLocked = new AtomicBoolean(true);
        playersOptionsList.setBounds(220, 10, 600, 400);
        playersOptionsList.setLayout(new GridLayout(3, 2, 5, 5));
        for (int i = 0; i < playersCount.get(); i++) {
            playersOptionsList.add(new PlayerOptions(i));
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
            playersOptionsList.add(new PlayerOptions(playersCount.get() - 1));
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
            playersOptionsList.remove(playersCount.decrementAndGet());
            playersOptionsList.validate();
            playersOptionsListLocked.set(false);
            repaint();
        });
        add(playersOptionsList);
        saveButton = new JButton("Save options!");
        saveButton.setBounds(10, 100, 200, 50);
        add(saveButton);
        startButton = new JButton("Start game!");
        startButton.setBounds(10, 160, 200, 50);
        add(startButton);
        setLayout(null);
        startButton.addActionListener(e -> {
            apply();
            Main.running = true;
        });
        saveButton.addActionListener(e -> {
            apply();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save options");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    ConfigReader.saveConfig(fileChooser.getSelectedFile().getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JDialog dialog = new JDialog(this, "File selection aborted", true);
                dialog.setSize(300, 100);
                dialog.setLayout(new GridLayout(2, 3));
                dialog.add(new JComponent() {});
                dialog.add(new JLabel("File not saved!"));
                dialog.add(new JComponent() {});
                dialog.add(new JComponent() {});
                JButton ok = new JButton("OK");
                ok.addActionListener(e1 -> dialog.dispose());
                dialog.add(ok);
                dialog.add(new JComponent() {});
                dialog.setVisible(true);
            }
        });
    }
    public void apply(){
        ConfigReader.getConfig().put("arena", arenaField.getText());
        ConfigReader.getConfig().put("players_count", String.valueOf(playersCount.get()));
        for (int i = 0; i < playersCount.get(); i++)
            if(playersOptionsList.getComponent(i) instanceof PlayerOptions playerOptions)
                playerOptions.apply();
    }
    private static MenuWindow instance;
    public static MenuWindow getInstance() {
        if (instance == null) {
            instance = new MenuWindow();
        }
        return instance;
    }
}
