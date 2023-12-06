package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Win;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;

public class WinViewer extends Viewer<Win> {
    public WinViewer(Win win) {
        super(win);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {
        gui.setBackgroundColor("#BA6156");

        gui.drawImage(new Position(20, 2), "sword.png", 1.5);
        gui.drawText(new Position(5, 5), " *** Level Complete *** ", "#FFFFFF");

        for (int i = 0; i < getModel().getNumber(); i++){
            gui.drawText(
                    new Position(5, 10 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }

}