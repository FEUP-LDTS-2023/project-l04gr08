package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Win;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

public class WinViewer extends Viewer<Win> {
    public WinViewer(Win win) {
        super(win);
    }

    @Override
    public void drawObject(GUI gui) {

        for (int i = 0; i < getModel().getNumberOptions(); i++)
            gui.drawText(
                    new Position(19, 35 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFA212" : "#FFFFFF");
    }

}