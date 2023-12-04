package com.st.projectst.model.game;

import com.st.projectst.model.Position;


public class BatEnemy extends Enemy implements EnemyObserver {
    private boolean attack;
    private Position initalPosition;
    private Position trapPosition;

    public BatEnemy(Position position) {
        super(position);
    }
    @Override
    public void move(Position position) {
        setPosition(new Position(0,0));
    }
    @Override
    public void update(Trap trap) {
        move(trap.getPosition());
    }
}
