package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.BatEnemy;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.Position;

import java.io.IOException;

public class BatEnemyController extends LevelController{
    private long lastMove;

    public BatEnemyController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {

        /*
        if (time - lastMove > 500) {
            for (BatEnemy enemy : getModel().getBatEnemies())
                enemy.updateOnMariCross();
                //moveBEnemy(enemy, enemy.getPosition().getRandomNeighbour());
            this.lastMove = time;
        }

         */
    }

    private void moveBEnemy(BatEnemy enemy, Position position) {
        if (getModel().isEmpty(position)) {
            enemy.setPosition(position);
            if (getModel().getMari().getPosition().equals(position))
                getModel().getMari().decreaseLives();
        }
    }

    /*
    @Override
    public void updateOnMariCross(Position position) {

        for (BatEnemy enemy : getModel().getBatEnemies()) {
            Position enemyPosition = enemy.getPosition();
            // Assuming the BatEnemy moves towards Mari when she crosses a specific position
            if (enemyPosition.distance(position) <= 5) {
                moveBEnemy(enemy, position);
            }
        }
    }

    private void canBEnemyMove(BatEnemy enemy, Position position) {
        // Move BatEnemy towards Mari's position
        int diffX = (int) (position.getX() - enemy.getPosition().getX());
        int diffY = (int) (position.getY() - enemy.getPosition().getY());

        // Adjust BatEnemy's position towards Mari
        if (Math.abs(diffX) >= Math.abs(diffY)) {
            if (diffX > 0) {
                Position newPos = new Position(enemy.getPosition().getX() + 1, enemy.getPosition().getY());
                moveBEnemy(enemy, newPos);
            } else if (diffX < 0) {
                Position newPos = new Position(enemy.getPosition().getX() - 1, enemy.getPosition().getY());
                moveBEnemy(enemy, newPos);
            }
        } else {
            if (diffY > 0) {
                Position newPos = new Position(enemy.getPosition().getX(), enemy.getPosition().getY() + 1);
                moveBEnemy(enemy, newPos);
            } else if (diffY < 0) {
                Position newPos = new Position(enemy.getPosition().getX(), enemy.getPosition().getY() - 1);
                moveBEnemy(enemy, newPos);
            }
        }
    }
*/
}
