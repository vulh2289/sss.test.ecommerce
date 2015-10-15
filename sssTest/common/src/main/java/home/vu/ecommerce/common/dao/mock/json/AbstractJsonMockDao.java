package home.vu.ecommerce.common.dao.mock.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonMockDao {

    private ObjectMapper objectMapper;
    protected int currentId;

    private String basePath;
    private String jsonDbFile;

    /**
     * @param jsonDbFile
     */
    public AbstractJsonMockDao(String basePath, String jsonDbFile) {
        this.jsonDbFile = jsonDbFile;
        this.basePath = basePath;
    }

    // Setters
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Read database from json file
     * 
     * @param elementClass
     * @return
     * @throws Exception
     */
    protected <T> List<T> readDB(Class<T> elementClass) {
        StringBuilder jsonTestData = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(this.basePath + this.jsonDbFile);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                jsonTestData.append(line);
            }

            // Always close files.
            bufferedReader.close();

            List<T> terms = objectMapper.readValue(jsonTestData.toString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass));

            return terms;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Write to json db
     * 
     * @param collection
     * @throws FileNotFoundException
     */
    protected <T> void writeDB(List<T> collection) {
        try {
            String json = objectMapper.writeValueAsString(collection);

            BufferedWriter bw = new BufferedWriter(new FileWriter(this.basePath + this.jsonDbFile));
            bw.write(json);
            bw.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param classType
     * @return
     */
    protected <T> T getLast(Class<T> classType) {
        List<T> all = readDB(classType);
        if (all.size() > 0) {
            T last = all.get(all.size() - 1);
            return last;
        }
        return null;
    }
}
