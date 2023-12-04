package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;

public class InstructionsViewer extends Viewer<Instructions> {
    public InstructionsViewer(Instructions instructions) {
        super(instructions);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {

        gui.setBackgroundColor("#BA6156");
        gui.drawImage(new Position(30, 30), "cat.png", 1);

        gui.drawText(new Position(20, 3), "OO  OO OOOOO OO    OOOOO", "#FFA212");
        gui.drawText(new Position(20, 4), "OO  OO OO    OO    OO OO", "#FFA212");
        gui.drawText(new Position(20, 5), "OOOOOO OOOO  OO    OOOOO", "#FFA212");
        gui.drawText(new Position(20, 6), "OO  OO OO    OO    OO   ", "#FFA212");
        gui.drawText(new Position(20, 7), "OO  OO OOOOO OOOOO OO   ", "#FFA212");

        gui.drawText(new Position(5, 11), "Mari, our heroine, is looking for her cat.", "#FFFFFF");
        gui.drawText(new Position(5, 12), "To find her you will have to face the bats and ghosts present in her library.", "#FFFFFF");
        gui.drawText(new Position(5, 13), "She is careful with the traps, because the bats are cunning.", "#FFFFFF");

        gui.drawText(new Position(5, 16), "Rules:", "#FFA212");
        gui.drawText(new Position(5, 17), "  -> Arrow Up   - Jump          ", "#FFFFFF");
        gui.drawText(new Position(5, 18), "  -> Arrow Left - Move left     ", "#FFFFFF");
        gui.drawText(new Position(5, 19), "  -> Arrow Right- Move right    ", "#FFFFFF");
        gui.drawText(new Position(5, 20), "  -> Touch a potion - Double jump", "#FFFFFF");
        gui.drawText(new Position(5, 21), "  -> Enter - Go back to the Menu", "#FFFFFF");


        for (int i = 0; i < getModel().getNumber(); i++)
            gui.drawText(new Position(55, 40 + i), " ", "#FFFFFF");
    }

}
