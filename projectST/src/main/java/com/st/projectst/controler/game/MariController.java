package com.st.projectst.controler.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Map;
import com.st.projectst.model.Mari;
import com.st.projectst.model.Position;

public class MariController extends LevelController{
    public MariController(Map map) {
        super(map);
    }

    public void moveMariLeft() {
        moveHero(getModel().getMari().getPosition().getLeft());
    }

    public void moveMariRight() {
        moveHero(getModel().getMari().getPosition().getRight());
    }

    private void moveHero(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getMari().setPosition(position);
            if (getModel().isEnemy(position)) getModel().getMari().decreaseLives();
        }
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) {
        Mari mari = getModel().getMari();
        if (action == GUI.ACTION.UP) mari.jump();
        if (action == GUI.ACTION.RIGHT) moveMariRight();
        if (action == GUI.ACTION.LEFT) moveMariLeft();
    }
}
