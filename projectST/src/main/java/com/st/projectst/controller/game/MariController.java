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
        moveMari(getModel().getMari().moveRight());
    }
    public void moveMariLeft() {
        moveMari(getModel().getMari().moveLeft());
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
        List<Position> mariPositions = new ArrayList<>();

        Position posEC = new Position(position);
        // Top of Mari head
        for (int x = 0; x <= 11; x++) {
            Position newPosition = new Position(posEC);
            newPosition.setX(newPosition.getX()+x);
            mariPositions.add(newPosition);
        }

        // Sides of Mari head
        Position posDC = new Position(position); posDC.setX(posDC.getX()+11);
        for (int y = 0; y <= 10; y++) {
            Position newPos1 = new Position(posEC); Position newPos2 = new Position(posDC);
            newPos1.setY(newPos1.getY()+y); newPos2.setY(newPos2.getY()+y);
            mariPositions.add(newPos1); mariPositions.add(newPos2);
        }

        // Sides of head
        Position posEB = new Position(posEC); Position posDB = new Position(posEC);
        posEB.setX(posEB.getX() + 3); posDB.setX(posDB.getX() + 8);
        for (int y = 11; y <= 13; y++) {
            Position newPos3 = new Position(posEB); Position newPos4 = new Position(posDB);
            newPos3.setY(newPos3.getY()+y); newPos4.setY(newPos4.getY()+y);
            mariPositions.add(newPos3); mariPositions.add(newPos4);
        }

        //Position posEB = new Position(position); posEB.setY(posEC.getY()+13); posEB.setX(posEC.getX()+3);
        //Position posDC = new Position(position); posDC.setX(posEC.getX()+11);
        //Position posDB = new Position(position); posDB.setY(posEC.getY()+13); posDB.setX(posEC.getX()+8);
        //List<Position> mariPositions = Arrays.asList(posEC, posEB, posDC, posDB);

        boolean Empty = true;
        for (Position pos: mariPositions) {
            if (!getModel().isEmpty(pos)) {
                Empty = false; break;
            }
        }
        if (Empty && position.getX() >= 0) {
            getModel().getMari().setPosition(position);
            for (Position pos2: mariPositions) {
                getModel().isTrap();
                if (getModel().isKey(pos2)) {
                    getModel().getMari().setWithKey(true);
                    getModel().removeKey();
                }
            }
        }
    }

    private void updateMari(long time) {
        // Verify if Mari is grounded
        getModel().getMari().setGrounded(getModel().Grounded());
        moveMari(getModel().getMari().update());

        // Verify if Mari was attacked
        if ((time - lastAttack) > 1000)
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
