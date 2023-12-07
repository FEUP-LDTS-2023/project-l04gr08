package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.viewer.Viewer;
import com.st.projectst.model.menu.Pause;


public class PauseViewer extends Viewer<Pause> {
    public PauseViewer(Pause pause) {
        super(pause);
    }
    @Override
    public void drawObject(GUI gui) {
        gui.setBackgroundColor("#BA6156");

        gui.drawText(new Position(39, 10), "OOOOO  OOOOOO  OO  OO  OOOOOO  OOOOOO", "#f9dbbe");
        gui.drawText(new Position(39, 11), "OO OO  OO  OO  OO  OO  OO      OO", "#ffbc6e");
        gui.drawText(new Position(39, 12), "OOOOO  OOOOOO  OO  OO  OOOOOO  OOOOOO", "#FF9966");
        gui.drawText(new Position(39, 13), "OO     OO  OO  OO  OO      OO  OO", "#ff8066");
        gui.drawText(new Position(39, 14), "OO     OO  OO  OOOOOO  OOOOOO  OOOOOO", "#ff9aab");

        for (int i = 0; i < getModel().getNumberOptions(); i++){
            gui.drawText(
                    new Position(52, 25 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
    }
}
