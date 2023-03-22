import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SettingsReader {

    private JSONObject jsonObject;

    public void readFile(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(path);
        Object obj = parser.parse(reader);
        jsonObject = (JSONObject) obj;
    }
    public String getValue(String key) {
        return (String) jsonObject.get(key);
    }


    public List<Object> getList(String key) {
        String value = (String) jsonObject.get(key);
        if (value != null) {
            String[] parts = value.split(",");
            return Arrays.asList(parts);
        } else {
            return Collections.emptyList();
        }
    }

    public Map<Object, Object> getMap(String key) {
        Map<Object, Object> result = new HashMap<>();
        String value = (String) jsonObject.get(key);
        if (value != null) {
            String[] parts = value.split(",");
            for (String part : parts) {
                String[] keyValue = part.split(":");
                result.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return result;
    }
}
