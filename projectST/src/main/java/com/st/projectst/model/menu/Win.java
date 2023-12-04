package com.st.projectst.model.menu;

import java.util.Arrays;
import java.util.List;

public class Win {
    private final List<String> options;
    private int currentOption = 0;

    public Win() {
        this.options = Arrays.asList(" Go Back ", "Continue");
    }

    public void nextOption() {
        currentOption++;
        if (currentOption > this.options.size() - 1)
            currentOption = 0;
    }

    public void previousOption() {
        currentOption--;
        if (currentOption < 0)
            currentOption = this.options.size() - 1;
    }

    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return currentOption == i;
    }

    public boolean isSelectedGoBackToLevels() {
        return isSelected(0);
    }

    public boolean isSelectedContinue() {
        return isSelected(1);
    }

    public int getNumberOptions() {
        return this.options.size();
    }

}
