package com.st.projectst.controller.game;

import com.st.projectst.controller.Controller;
import com.st.projectst.model.game.Map;

public abstract class LevelController extends Controller<Map> {
    public LevelController(Map map) {
        super(map);
    }
}

