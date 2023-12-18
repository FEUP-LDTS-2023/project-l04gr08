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
        else
            newPosition.setY(FinalPosition.getY());
        return newPosition;
    }
    @Override
    public void update(Trap trap) {
        Position FinalP = new Position(trap.getPosition());
        FinalP.setY(trap.getPosition().getY()-13);
        this.FinalPosition = FinalP;
    }

    public Position getFinalPosition() {
        return FinalPosition;
    }

    public void setFinalPosition(Position finalPosition) {
        FinalPosition = finalPosition;
    }
}
