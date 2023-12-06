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
import java.util.Arrays;
import java.util.List;

public class MariController extends LevelController {
    private long lastAttack;
    public MariController(Map map) {
        super(map);
        this.lastAttack = 0;
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
        mari.jump();
        moveMari(currentPosition);

        /*
        // Check if Mari touches a potion
        if (getModel().isPotion(currentPosition)) {
            if (mari.getRemainingJumps() > 0) {
                mari.jump();
                mari.decreaseJumps();
            }
        }

         */

    }

    private void moveMari(Position position) {
        Position posEC = new Position(position);
        Position posEB = new Position(position); posEB.setY(posEC.getY()+13); posEB.setX(posEC.getX()+3);
        Position posDC = new Position(position); posDC.setX(posEC.getX()+11);
        Position posDB = new Position(position); posDB.setY(posEC.getY()+13); posDB.setX(posEC.getX()+8);
        List<Position> mariPositions = Arrays.asList(posEC, posEB, posDC, posDB);

        boolean Empty = true;
        for (Position pos: mariPositions) {
            if (!getModel().isEmpty(pos)) {
                Empty = false; break;
            }
        }
        if (Empty) {
            getModel().getMari().setPosition(position);
            for (Position pos2: mariPositions) {
                getModel().isTrap();
                if (getModel().isKey(pos2)) {
                    getModel().getMari().setWithKey();
                    getModel().removeKey();
                }
            }
        }
    }

    private void updateMari(long time) {
        // Verify if Mari is grounded
        getModel().getMari().setGrounded(getModel().Grounded());
        getModel().getMari().update();

        // Verify if Mari was attacked
        if ((time - lastAttack) > 600)
            if (getModel().isEnemy(getModel().getMari().getPosition())) {
                getModel().getMari().decreaseLives();
                lastAttack = time;
            }

    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) {
        updateMari(time);

        if (action == GUI.ACTION.UP) moveMariUp();
        if (action == GUI.ACTION.RIGHT) moveMariRight();
        if (action == GUI.ACTION.LEFT) moveMariLeft();

    }

}
