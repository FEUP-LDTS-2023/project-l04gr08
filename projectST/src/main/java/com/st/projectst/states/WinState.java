package com.st.projectst.states;

import com.st.projectst.viewer.menu.WinViewer;

public class WinState extends State<Win>{
    public WinState(Win win) {
        super(win);
    }

    @Override
    protected Controller<Win> getController() {
        return new WinController(getModel());
    }
    @Override
    protected Viewer<Win> getViewer() {
        return new WinViewer(getModel());
    }
}
