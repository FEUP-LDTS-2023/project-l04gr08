package com.st.projectst.viewer.menu;

import com.st.projectst.model.menu.Start;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;

import java.io.IOException;

public class StartViewer extends Viewer<Start> {
    public StartViewer(Start start) {
        super(start);
    }

    @Override
    public void drawObject(GUI gui) throws IOException {
        gui.drawMenuElements();
        gui.drawText(new Position(5, 5), "Searching For", "#FFFFFF");
        gui.drawText(new Position(5, 6), "   Key-Ty    ", "#DAA520");

        for (int i = 0; i < getModel().getNumber(); i++) {
            gui.drawText(
                    getModel().getOptionPosition(i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }
}
