package com.st.projectst.states;

import com.st.projectst.viewer.menu.PauseViewer;

public class PauseState extends State<Pause>{
    public PauseState(Pause pause) {
        super(pause);
    }

    @Override
    protected Controller<Pause> getController() {
        return new PauseController(getModel());
    }
    @Override
    protected Viewer<Pause> getViewer() {
        return new PauseViewer(getModel());
    }
}
