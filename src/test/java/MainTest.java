import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class MainTest {
    @Disabled
    @Timeout(22)
    @Test
    public void mainTest(){
        try {
            Main.main(new String[]{"f", "r"});

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
