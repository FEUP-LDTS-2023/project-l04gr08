package com.st.projectst.controler.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Map;
import com.st.projectst.model.Mari;
import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;

public class MariController extends LevelController{
    private List<EnemyObserver> observers;
    public MariController(Map map) {
        super(map);
        observers = new ArrayList<>();
        BatEnemyController batEnemyController = new BatEnemyController(map);
        addObserver(batEnemyController);
    }

    public void moveMariLeft() {
        moveMari(getModel().getMari().getPosition().getLeft());
    }

    private void addObserver(EnemyObserver observer) {
        observers.add(observer);
    }

    public void moveMariRight() {
        moveMari(getModel().getMari().getPosition().getRight());
    }

    public void moveMariUp() {
        Mari mari = getModel().getMari();
        Position currentPosition = mari.getPosition();

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
        // Regular jump logic when mari is not touching a potion or no remaining jumps
        mari.jump();
        moveMari(currentPosition);
        notifyObservers(currentPosition);
    }

    private void notifyObservers(Position position) {
        for (EnemyObserver observer : observers) {
            observer.updateOnMariCross(position);
        }
    }

    private void moveMari(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getMari().setPosition(position);
            if (getModel().isEnemy(position)) getModel().getMari().decreaseLives();
        }
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) moveMariUp();
        if (action == GUI.ACTION.RIGHT) moveMariRight();
        if (action == GUI.ACTION.LEFT) moveMariLeft();
    }
}
