package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Start;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

public class StartViewer extends Viewer<Start> {
    public StartViewer(Start start) {
        super(start);
    }

    @Override
    public void drawObject(GUI gui) {
        gui.drawImage(new Position(0,0), "mari1.png");

        for (int i = 0; i < getModel().getNumber(); i++) {
            gui.drawText(
                    getModel().getOptionPosition(i),
                    ".",
                    getModel().isSelected(i) ? "#FFFFFF" : "#BA6156");

        }

    }
}
