package com.st.projectst.controller.game;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Potion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class PotionControllerTest {

    @Test
    void testPotionVisibilityToggle() throws IOException {
        Map mockedMap = Mockito.mock(Map.class);
        Potion potion1 = Mockito.mock(Potion.class);
        Potion potion2 = Mockito.mock(Potion.class);

        List<Potion> potions = new ArrayList<>();
        potions.add(potion1);
        potions.add(potion2);

        when(mockedMap.getPotions()).thenReturn(potions);

        Position position1 = new Position(10, 10);
        Position position2 = new Position(20, 20);

        when(potion1.getPosition()).thenReturn(position1);
        when(potion2.getPosition()).thenReturn(position2);

        PotionController potionController = new PotionController(mockedMap);
        long currentTime = 0;
        boolean initialVisibility = true;

        for (int i = 0; i < 10; i++) {
            currentTime += 1000;
            potionController.step(null, null, currentTime);

            boolean isVisible = arePotionsVisible(potion1, potion2);
            if (i < 4){
                assertEquals(initialVisibility, isVisible);
            }
            else if (i == 6 || i == 8){
                assertNotEquals(initialVisibility, isVisible);
            }
            if (i >= 3) {
                initialVisibility = !initialVisibility;
            }
        }
    }
    private boolean arePotionsVisible(Potion potion1, Potion potion2) {
        return potion1.getPosition().getX() != -10 && potion2.getPosition().getX() != -10;
    }
}

