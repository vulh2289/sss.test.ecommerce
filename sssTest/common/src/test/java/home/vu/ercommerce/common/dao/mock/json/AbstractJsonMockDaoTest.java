package home.vu.ercommerce.common.dao.mock.json;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonMockDaoTest {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected void tearDown() {

    }

    protected String readJson(String filePath) throws Exception {
        InputStream in = getClass().getResourceAsStream(filePath);
        return FileCopyUtils.copyToString(new InputStreamReader(in));
    }

}
