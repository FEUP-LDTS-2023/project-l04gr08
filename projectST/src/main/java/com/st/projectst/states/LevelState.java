package com.st.projectst.states;


public class LevelState extends State<Level>{
    public LevelState(Level level) {
        super(level);
    }

    @Override
    protected Controller<Level> getController() {
        return new LevelController(getModel());
    }
    @Override
    protected Viewer<Level> getViewer() {
        return new LevelViewer(getModel());
    }
}
