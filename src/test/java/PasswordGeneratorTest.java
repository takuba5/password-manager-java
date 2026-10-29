import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {
    @RepeatedTest(100)
    void GeneratePasswordShouldHaveCorrectLenght(){
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generateRandomPassword();

        assertTrue(password.length()>=10);
        assertTrue(password.length()<=24);
    }
}
