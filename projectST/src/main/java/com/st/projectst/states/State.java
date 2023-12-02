package com.st.projectst.states;
import com.st.projectst.controler.Controller;

import com.st.projectst.viewer.Viewer;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.viewer = getViewer();
        this.controller = getController();
    }

    protected abstract Controller<T> getController();
    protected abstract Viewer<T> getViewer();
    public T getModel() {
        return model;
    }

}
