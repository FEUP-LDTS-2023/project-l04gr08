package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Mari;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Level;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.StartState;
import com.st.projectst.states.WinState;

import java.util.ArrayList;
import java.util.List;

public class MariController extends LevelController {
    public MariController(Map map) {
        super(map);
    }


    public void moveMariRight() {
        moveMari(getModel().getMari().getPosition().getRight());
    }
    public void moveMariLeft() {
        moveMari(getModel().getMari().getPosition().getLeft());
    }

    public void moveMariUp() {
        Mari mari = getModel().getMari();
        Position currentPosition = mari.getPosition();

        /*
        // Check if Mari touches a potion
        if (getModel().isPotion(currentPosition)) {
            if (mari.getRemainingJumps() > 0) {
                mari.jump();
                mari.decreaseJumps();

                // Move Mari after double jump
                moveMari(currentPosition.getUp());
                notifyObservers(currentPosition.getUp());
                return;
            }
        }

         */

        mari.jump();
        moveMari(currentPosition);
        //notifyObservers(currentPosition);

        // Regular jump logic when mari is not touching a potion or no remaining jumps
    }

    private void moveMari(Position position) {

        if (getModel().isEmpty(position)) {
            getModel().getMari().setPosition(position);

            getModel().verifyTrap(position);

            if (getModel().isEnemy(position)) getModel().getMari().decreaseLives();
            if (getModel().isKey(position)) getModel().getMari().setWithKey();
        }

    }

    private void updateMari() {
        getModel().getMari().setGrounded(getModel().Grounded());
        getModel().getMari().update();
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) {
        updateMari();

        if (action == GUI.ACTION.UP) moveMariUp();
        if (action == GUI.ACTION.RIGHT) moveMariRight();
        if (action == GUI.ACTION.LEFT) moveMariLeft();
    }
}
