import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {
    @RepeatedTest(50)
    void GeneratePasswordShouldHaveCorrectLenght(){
        PasswordGenerator generator = new PasswordGenerator();
        String password = generator.generateRandomPassword();

        assertTrue(password.length()>=10);
        assertTrue(password.length()<=24);
    }
    @Test
    void generatedPassswordShouldNotBeNullOrEmpty(){
        PasswordGenerator generator = new PasswordGenerator();
        String password  = generator.generateRandomPassword();

        assertNotNull(password);
        assertFalse(password.isEmpty());
    }

    @Test
    void constructorShouldStoreEntryData(){
        PasswordEntry entry = new PasswordEntry("Github", "testlogin", "testpasswoRd123@", "github.com");

        assertEquals("Github", entry.getService());
        assertEquals("testlogin", entry.getLogin());
        assertEquals("testpasswoRd123@", entry.getPassword());
        assertEquals("github.com", entry.getURL());


    }
    @Test
    void textShouldBetheSameAfterEncryptandDecryptMode() throws Exception {
        EncryptionService service = new EncryptionService();
        String orginal ="Password123@";
        String encrypted = service.encrypt(service.loadKey(), orginal);
        String decrypted  = service.decrypt(service.loadKey(),encrypted);
        assertEquals(orginal,decrypted);

    }
    @Test
    void emptyTextAfterEncryptAndDecryptShouldBeEmptyAfterThis() throws Exception {
        EncryptionService service = new EncryptionService();
        String orginal ="";
        String encrypted = service.encrypt(service.loadKey(), orginal);
        String decrypted  = service.decrypt(service.loadKey(),encrypted);
        assertEquals(orginal,decrypted);
    }



}
