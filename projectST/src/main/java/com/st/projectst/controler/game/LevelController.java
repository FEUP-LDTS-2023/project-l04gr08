package com.st.projectst.controler.game;

import com.st.projectst.controler.Controller;
import com.st.projectst.model.Map;

public abstract class LevelController extends Controller<Map> {
    public LevelController(Map map) {
        super(map);
    }
}

