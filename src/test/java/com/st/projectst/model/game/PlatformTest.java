package com.st.projectst.model.game;

import com.st.projectst.model.Position;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlatformTest {
    @Test
    void sameLevelPlatformsTest() {
        Position position1 = new Position(0, 5);
        Position position2 = new Position(10, 5);
        Platform platform1 = new Platform(position1);
        Platform platform2 = new Platform(position2);

        boolean result = platform1.isOnSameLevel(platform2);

        assertTrue(result, "Platforms should be on the same level");
    }

    @Test
    void addConnectedPlatformTest() {
        Position position = new Position(0, 0);
        Platform platform1 = new Platform(position);
        Platform platform2 = new Platform(position);

        platform1.addConnectedPlatform(platform2);

        List<Wall> connectedPlatforms = platform1.getConnectedPlatforms();
        assertTrue(connectedPlatforms.contains(platform2));
    }

    @Test
    void movePlatformsTest() {
        Position position = new Position(0, 0);
        Platform platform = new Platform(position);
        double initialY = position.getY();
        for (int i = 0; i < 34; i++) {
            platform.moveAllPlatforms();
            if (i <= 15) {
                assertTrue(platform.isMovingUp());
                assertEquals(platform.getMoveCounter(), i+1);
            }
            if (i == 16) {
                assertFalse(platform.isMovingUp());
            }

            if (i == 31) {
                assertFalse(platform.isMovingUp());
                assertEquals(16, platform.getMoveCounter());
            }
            if (i == 32) {
                assertTrue(platform.isMovingUp());
                assertEquals(1, platform.getMoveCounter());
            }

        }

        double newY = platform.getPosition().getY();
        assertTrue(initialY != newY);
    }

}
