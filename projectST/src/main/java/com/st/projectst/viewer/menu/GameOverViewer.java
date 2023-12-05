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
        gui.setBackgroundColor("#BA6156");

        gui.drawText(new Position(3, 8), "  OOOO                                      OOOO                            " , "#f9dbbe");
        gui.drawText(new Position(3, 9), " OOOOOOO                                   OOOOOOO                          " , "#f9dbbe");
        gui.drawText(new Position(3, 10), "OOOO      OOOOOO   OOOOO OOO    OOO      OOO  OOO  OO  OOO   OOO    OOOO   " , "#ffbc6e");
        gui.drawText(new Position(3, 11), "OO       OOOOOO    OOOOOOOOO   OOOOO     OOO   OO  OO OOOO  OOOOOO  OOOOOO  " , "#ffbc6e");
        gui.drawText(new Position(3, 12), "OO  OOO  OOO OO    OOO OOO OO  OO  OO    OO    OO  OO  OO   OO  OO  OOO     " , "#FF9966");
        gui.drawText(new Position(3, 13), "OO OOOO  OO  OO    OO  OO  OO  OOOOOO    OO    OO  OOOOO   OOOOOO   OO      " , "#FF9966");
        gui.drawText(new Position(3, 14), "OO   OO  OO OOO    OO  OO  OO  OOO       OOO   OO   OOOO    OOO     OO      " , "#ff8066");
        gui.drawText(new Position(3, 15), "OOOOOOO  OOOOOOO  OOO OOO OOO  OOOOO      OOOOOOO   OOOO    OOOOO  OOO      " , "#ff9aab");
        gui.drawText(new Position(3, 16), " OOOOO   OOOOO OO OOO OOO OOO   OOOO       OOOO     OOO      OOOO  OOO      " , "#ff9aab");

        for (int i = 0; i < getModel().getNumberOptions(); i++)
            gui.drawText(
                    new Position(27, 20 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#F1A55E" : "#FFFFFF"
            );
    }
}
