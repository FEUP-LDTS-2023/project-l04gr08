package com.st.projectst;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.st.projectst.controler.GameEngine;
import com.st.projectst.gui.LanternaGUI;

import java.io.IOException;

public class Menu {
    public static void main(String[] args) throws IOException {
        Screen screen = new TerminalScreen(new DefaultTerminalFactory().createTerminal());
        screen.startScreen();
        LanternaGUI lanternaGUI = new LanternaGUI(screen);

        BasicWindow window = new BasicWindow("Game Menu");

        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(4));

        panel.addComponent(new Button("Map 1", () -> {
            GameEngine gameEngine = new GameEngine(80, 24);
            gameEngine.run();
        }));

        panel.addComponent(new Button("Load Game", () -> {
        }));

        panel.addComponent(new Button("Options", () -> {

        }));

        panel.addComponent(new Button("Exit", () -> {
            lanternaGUI.drawMenu();
            new MessageDialogBuilder()
                    .setTitle("Exit Game")
                    .setText("Are you sure you want to exit the game?")
                    .addButton(MessageDialogButton.Yes)
                    .addButton(MessageDialogButton.No)
                    .build();
        }));

        window.setComponent(panel);

        MultiWindowTextGUI textGUI = new MultiWindowTextGUI(screen);
        textGUI.addWindowAndWait(window);
    }
}





