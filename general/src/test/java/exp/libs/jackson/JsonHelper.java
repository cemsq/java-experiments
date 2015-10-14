package exp.libs.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.internal.ws.server.sei.SEIInvokerTube;

/**
 *
 */
public class JsonHelper {

    public static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String readableJson(Object json) {
        ObjectWriter writer = mapper.writer().with(SerializationFeature.INDENT_OUTPUT);


        try {
            return writer.writeValueAsString(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
