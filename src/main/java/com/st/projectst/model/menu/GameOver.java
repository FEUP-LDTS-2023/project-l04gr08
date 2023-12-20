package com.st.projectst.model.menu;

import java.util.Arrays;
import java.util.List;

public class GameOver {
    private final List<String> options;
    private int currentOption = 0;

    public GameOver() {
        this.options = Arrays.asList(" Return to the Menu ");
    }

    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }

    public boolean isSelectedGoBack() {
        return isSelected(0);
    }

}
