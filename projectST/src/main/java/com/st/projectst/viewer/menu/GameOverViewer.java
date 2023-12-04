package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.GameOver;
import com.st.projectst.viewer.Viewer;

public class GameOverViewer extends Viewer<GameOver> {
    public GameOverViewer(GameOver gameOver) {
        super(gameOver);
    }

    @Override
    public void drawObject(GUI gui) {
        gui.drawText(new Position(3, 8), "  OOOO                                      OOOO                            " , "#FFA212");
        gui.drawText(new Position(3, 9), " OOOOOOO                                   OOOOOOO                          " , "#FFA212");
        gui.drawText(new Position(3, 10), "OOOO      OOOOOO    OOOOO OOO    OOO      OOO  OOO  OO  OOO   OOO    OOOO   " , "#FFA212");
        gui.drawText(new Position(3, 11), "OO      OOOOOO    OOOOOOOOO  OOOOOO     OOO   OO  OO OOOO  OOOOOO  OOOOOO  " , "#FFA212");
        gui.drawText(new Position(3, 12), "OO  OOO  OOO OO    OOO OOO OO  OO  OO     OO    OO  OO OO   OO  OO  OOO     " , "#FFA212");
        gui.drawText(new Position(3, 13), "OO OOOO  OO  OO    OO  OO  OO  OOOOOO     OO   OOO  OOOOO   OOOOOO  OO      " , "#FFA212");
        gui.drawText(new Position(3, 14), "OO   OO  OO OOO    OO  OO  OO  OOO        OOO OOO   OOOO    OOO     OO      " , "#FFA212");
        gui.drawText(new Position(3, 15), "OOOOOOO  OOOOOOO  OOO OOO OOO  OOOOO      OOOOOOO   OOOO    OOOOO  OOO      " , "#FFA212");
        gui.drawText(new Position(3, 16), " OOOOO   OOOOO OO OOO OOO OOO   OOOO       OOOO     OOO      OOOO  OOO      " , "#FFA212");

        for (int i = 0; i < getModel().getNumberOptions(); i++)
            gui.drawText(
                    new Position(27, 35 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFA212" : "#FFFFFF"
            );
    }
}
