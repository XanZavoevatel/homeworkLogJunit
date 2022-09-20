import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class HorseTest {
    @Test
    public void testConstructorFirstParamTrowExIfNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
    }

    @Test
    public void testConstructorFirstParamTrowExIfNullMessage(){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 1,2)
        );
        Assertions.assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testConstructorFirstParamUncorrectedString(String strings){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(strings, 1, 2));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n"})
    public void testConstructorFirstParamUncorrectedStringMessage(String s){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(s, 1,2)
        );
        Assertions.assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    public void testConstructorSecondParamNotNegativeInt(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", -1, 2));
    }
    @Test
    public void testConstructorSecondParamNotNegativeIntMessage(){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("test", -1,2)
        );
        Assertions.assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    public void testConstructorThirdParamNotNegativeInt(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("test", 1, -2));
    }
    @Test
    public void testConstructorThirdParamNotNegativeIntMessage(){
        IllegalArgumentException thrown = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("test", 1,-2)
        );
        Assertions.assertEquals("Distance cannot be negative.", thrown.getMessage());
    }

    @Test
    public void getNameReturnFirstParamConstructor(){
        Assertions.assertEquals("test",
                new Horse("test",1,2).getName());
    }
    @Test
    public void getSpeedReturnSecondParamConstructor(){
        Assertions.assertEquals(1,
                new Horse("test",1,2).getSpeed());
    }

    @Test
    public void getDistanceReturnThirdParamConstructor(){
        Assertions.assertEquals(2,
                new Horse("test",1,2).getDistance());
    }

    @Test
    public void moveTest(){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("test", 1, 2);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }
    @Test
    public void moveTestCorrectWork(){
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("test", 1, 2);
            horse.move();
            double result = 1 + 2 * 0.5;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Assertions.assertEquals(result, horse.getDistance());
        }
    }


}
