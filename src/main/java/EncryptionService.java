import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionService {
    private SecretKey secretKey;
    public String keyPath = "encryption.key";

    void config() throws Exception{
        if(Files.exists(Path.of(keyPath))){

            secretKey = loadKey();
        }else {
            secretKey = generateKey();
            saveKey(secretKey);
        }



    }
    SecretKey generateKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);

        return generator.generateKey();
    }
    void saveKey(SecretKey secretKey) throws Exception{
        byte[] keyBytes = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        Files.writeString(Path.of(keyPath), encodedKey);
    }

    SecretKey loadKey() throws Exception{
        String key = Files.readString(Path.of(keyPath));
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return  new SecretKeySpec(keyBytes, "AES");
    }
    String encrypt(SecretKey secretKey, String jsonText) throws Exception{
        SecureRandom random = new SecureRandom();

        byte[] nonce = new byte[12];
        random.nextBytes(nonce);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128,nonce);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,spec);
        byte[] encrypted = cipher.doFinal(jsonText.getBytes(StandardCharsets.UTF_8));
        String encodedNonce = Base64.getEncoder().encodeToString(nonce);
        String encodedEncrypted = Base64.getEncoder().encodeToString(encrypted);
        String text = encodedNonce + ":" + encodedEncrypted;
        return text;

    }
    String decrypt(SecretKey secretKey, String text) throws Exception{
        String[] parts = text.split(":");
        byte[] nonce = Base64.getDecoder().decode(parts[0]);
        byte[] encrypted = Base64.getDecoder().decode(parts[1]);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128,nonce);
        cipher.init(Cipher.DECRYPT_MODE,secretKey,spec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted,StandardCharsets.UTF_8);
    }

}

