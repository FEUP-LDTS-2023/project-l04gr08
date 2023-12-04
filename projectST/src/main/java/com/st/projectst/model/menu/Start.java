package com.st.projectst.model.menu;

import com.st.projectst.model.Position;

import java.util.Arrays;
import java.util.List;

public class Start {
    private final List<Position> options;
    private int currentOption;

    public Start(int selectedOption) {
        this.currentOption = selectedOption;

        Position positionStart = new Position(5, 10);
        Position positionInstructions = new Position(5, 11);
        Position positionExit = new Position(5, 12);
        options = Arrays.asList(positionStart, positionInstructions, positionExit);
    }

    public void previousOption() {
        if (currentOption > 0)
            currentOption--;
    }
    public void nextOption() {
        if (currentOption < (options.size() - 1))
            currentOption++;
    }
    public Position getOptionPosition(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }
    public String getOption(int i) {
        switch (i){
            case 0:
                return "Start";
            case 1 :
                return "Instructions";
            case 2:
                return "Exit";
            default:
        }
        return "";
    }
    public boolean isSelectedStart() {
        return isSelected(0);
    }
    public boolean isSelectedInstructions() {
        return isSelected(1);
    }
    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public int getNumber() {
        return options.size();
    }
}
