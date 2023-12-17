package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Mari;
import com.st.projectst.model.Position;

import java.util.ArrayList;
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

    }

    public void moveMari(Position position) {
        List<Position> mariPositions = new ArrayList<>();

        Position posEC = new Position(position);
        // Top of Mari head
        for (int x = 0; x <= 11; x++) {
            Position newPosition = new Position(posEC);
            newPosition.setX(newPosition.getX()+x);
            mariPositions.add(newPosition);
        }

        // Sides of Mari
        Position posDC = new Position(position); posDC.setX(posDC.getX()+11);
        for (int y = 0; y <= 13; y++) {
            Position newPos1 = new Position(posEC); Position newPos2 = new Position(posDC);
            newPos1.setY(newPos1.getY()+y); newPos2.setY(newPos2.getY()+y);
            mariPositions.add(newPos1); mariPositions.add(newPos2);
        }


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

    void updateMari(long time) {
        getModel().getMari().setGrounded(getModel().Grounded());

        if (getModel().touchPotion(getModel().getMari().getPosition())){
            getModel().getMari().setWithPotion(true);
        }

        if (getModel().getMari().getIsWithPotion() && getModel().getMari().getRemainingJumps() >= 0){
            moveMari(getModel().getMari().doubleJump());
        }
        else{
            getModel().getMari().resetJumps();
            moveMari(getModel().getMari().update());
        }

        Position currentMariPosition = getModel().getMari().getPosition();

        if (getModel().isAtPlatform(currentMariPosition)) {
            getModel().getMari().getPosition().setY(getModel().getMari().getPosition().getY()-1);
        }

        // Verify if Mari was attacked
        if ((time - lastAttack) > 1000) {
            if (getModel().isEnemy(getModel().getMari().getPosition())) {
                getModel().getMari().decreaseLives();
                lastAttack = time;
            }
        }
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) {
        updateMari(time);

        if (action == GUI.ACTION.UP) {
            if (getModel().getMari().getIsWithPotion()) getModel().getMari().decreaseJumps();
            moveMariUp();
        } else if (action == GUI.ACTION.RIGHT) {
            moveMariRight();
        } else if (action == GUI.ACTION.LEFT) {
            moveMariLeft();
        }

    }

}
