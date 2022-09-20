import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.internal.verification.VerificationModeFactory.times;

public class HippodromeTest {

    @Test
    public void testConstructorListNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorListNullMessage(){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        Assertions.assertEquals("Horses cannot be null.", thrown.getMessage());
    }
    @Test
    public void testConstructorListIsEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void testConstructorListIsEmptyMessage(){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>())
        );
        Assertions.assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    public void getHorsesTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i,i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    // Это точно надо было сделать так?

    @Test
    public void moveTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        AtomicInteger result = new AtomicInteger();
        hippodrome.getHorses().forEach(h ->{
            result.getAndIncrement();
            h.move();
        });
        Assertions.assertEquals(50, result.get());
    }

    @Test
    public void getWinnerTest(){
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i,i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(30, hippodrome.getWinner().getDistance());
    }

}
