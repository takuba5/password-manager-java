import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    public ObjectMapper mapper = new ObjectMapper();
    private EncryptionService service = new EncryptionService();

    void save(List<PasswordEntry> list){
        try {
            service.config();
            String json = mapper.writeValueAsString(list);
            String encrypted = service.encrypt(service.loadKey(),json);

            Files.writeString(Path.of(Main.file), encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    List<PasswordEntry> read() {
        File file = new File(Main.file);
        if(!file.exists() || file.length()==0){
            return new ArrayList<>();
        }


        try {
            service.config();
            String encrypted = Files.readString(Path.of(Main.file));
            String json =service.decrypt(service.loadKey(),encrypted);

            return mapper.readValue(
                    json,
                    new TypeReference<List<PasswordEntry>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
