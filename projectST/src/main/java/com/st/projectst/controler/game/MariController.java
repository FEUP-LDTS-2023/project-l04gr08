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
        mari.jump();
        moveMari(mari.getPosition());
        notifyObservers(mari.getPosition());
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
