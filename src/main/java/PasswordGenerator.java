import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
    private static final SecureRandom random = new SecureRandom();


    public String generateRandomPassword(){

        int length = random.nextInt(15)+10;

        StringBuilder sb = new StringBuilder(length);
        for(int i =0;i<length;i++){
            int randomIndex = random.nextInt( ALLOWED_CHARS.length());
            sb.append(ALLOWED_CHARS.charAt(randomIndex));
        }

        return sb.toString();



    }
}
