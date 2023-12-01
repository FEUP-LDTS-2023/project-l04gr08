package com.st.projectst.controler;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Main game, GUI.ACTION action, long time) throws IOException;
}