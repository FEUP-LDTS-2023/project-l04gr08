package com.st.projectst.viewer.menu;

import com.st.projectst.states.Start;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

public class StartViewer extends Viewer<Start> {
    public StartViewer(Start start) {
        super(start);
    }

    @Override
    //TESTE
    public void drawObject(GUI gui) {
        gui.drawText(new Position(5, 5), "Menu", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(5, 10 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
