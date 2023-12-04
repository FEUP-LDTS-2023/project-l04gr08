package com.st.projectst.model.menu;

import java.util.Arrays;
import java.util.List;

public class Level {
    private List<String> options;
    private List<Boolean> keysCollected;
    private int currentOption = 0;

    public Level() {
        this.options = Arrays.asList("Map 1", "Map 2", "Map 3", "GO BACK");
        this.keysCollected = Arrays.asList(false, false, false, false);
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

    public boolean isSelectedMap1() {
        return isSelected(0);
    }

    public boolean isSelectedMap2() {
        return isSelected(1);
    }

    public boolean isSelectedMap3() {
        return isSelected(2);
    }

    public boolean isSelectedGoBack() {
        return isSelected(4);
    }

    public int getNumberOptions() {
        return this.options.size();
    }

    public boolean isKeyCollected(int level) {
        return keysCollected.get(level);
    }

    public void collectKey(int level) {
        keysCollected.set(level, true);
    }

    public boolean transitionToNextLevel() {
        if (currentOption < options.size() - 1 && isKeyCollected(currentOption)) {
            nextOption();
            return true;
        }
        return false;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void setCurrentOption(int currentOption) {
        this.currentOption = currentOption;
    }
}
