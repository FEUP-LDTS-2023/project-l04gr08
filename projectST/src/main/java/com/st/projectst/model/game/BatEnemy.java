package com.st.projectst.model.game;

import com.st.projectst.model.Position;


public class BatEnemy extends Enemy implements EnemyObserver {
    private Position FinalPosition;
    public BatEnemy(Position position) {
        super(position); FinalPosition = position;
    }
    @Override
    public Position move() {
        Position newPosition = new Position(getPosition());
        if (getPosition().getY()<FinalPosition.getY()) {
            newPosition.setY(newPosition.getY()+3);
        }
        return newPosition;
    }
    @Override
    public void update(Trap trap) {
        this.FinalPosition = trap.getPosition();
    }
}
