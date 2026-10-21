import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    public ObjectMapper mapper = new ObjectMapper();

    void save(List<PasswordEntry> list){
        try {
            mapper.writeValue(new File(Main.file), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    List<PasswordEntry> read() {
        if(!new File(Main.file).exists() || new File(Main.file).length()==0){
            return new ArrayList<>();
        }

        try {
            return mapper.readValue(
                    new File(Main.file),
                    new TypeReference<List<PasswordEntry>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
