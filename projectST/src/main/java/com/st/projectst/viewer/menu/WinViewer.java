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

        for (int i = 0; i < getModel().getNumber(); i++)
            gui.drawText(
                    new Position(0, 0),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFA212" : "#000000");
    }

}