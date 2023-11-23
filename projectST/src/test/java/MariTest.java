import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Mari;
import com.st.projectst.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MariTest {
    Mari mari;

    @BeforeEach
    public void setup() {
        Position initialPosition = new Position(40,23);
        mari = new Mari(initialPosition);
    }


    @Test
    public void mariMoveRight() {
        mari.moveRight();
        Position expected = new Position(41,23);

        Assertions.assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariMoveLeft() {
        mari.moveLeft();
        Position expected = new Position(39,23);

        Assertions.assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariJump() {
        mari.jump();

        Assertions.assertTrue(mari.getIsJumping());
    }

    @Test
    public void mariUpdateNoJumping() {
        mari.update();
        Position expected = new Position(40,23);

        Assertions.assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariUpdateJumping() {
        mari.jump();
        Assertions.assertTrue(mari.getIsJumping());
        Assertions.assertEquals(0, mari.getJumpCounter());

        // Testing the character moving up on the jump
        Position expected = new Position(40,23);
        for (int i = 1; i <= 5; i++) {
            mari.update();

            Assertions.assertTrue(mari.getIsJumping());
            expected.setY(expected.getY()-1);
            Assertions.assertEquals(expected, mari.getPosition());
            Assertions.assertEquals(i, mari.getJumpCounter());
        }

        // Testing the character reaching the highest position on the jump
        mari.update();
        Assertions.assertFalse(mari.getIsJumping());
        expected.setY(expected.getY()-1);
        Assertions.assertEquals(expected, mari.getPosition());
        Assertions.assertEquals(0, mari.getJumpCounter());

        // Testing the character moving down
        for (int i = 1; i <= 6; i++) {
            mari.update();
            expected.setY(expected.getY()+1);
            Assertions.assertEquals(expected, mari.getPosition());

            Assertions.assertFalse(mari.getIsJumping());
            Assertions.assertEquals(0, mari.getJumpCounter());
        }

        // Testing the character reach the ground
        mari.update();
        Assertions.assertEquals(expected, mari.getPosition());
    }
    /*
    @Test
    public void mariDraw() {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);

        mari.draw(mockGraphics);

        Mockito.verify(mockGraphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.CYAN);
        Mockito.verify(mockGraphics, Mockito.times(1)).putString(
                Mockito.eq(new TerminalPosition(mari.getPosition().getX(), mari.getPosition().getY())),
                Mockito.eq(" ")
        );
    }
    */
}
