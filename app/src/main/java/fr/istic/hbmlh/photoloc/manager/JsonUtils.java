package fr.istic.hbmlh.photoloc.manager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.List;

/**
 * Created by malah on 11/12/17.
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

    public static <T> T parseToList(String json) {
        try {
            return MAPPER.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseToObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String parseToJson(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String json, Class<T> type) {
        if (json != null && !json.equals("")) {
            if (json.startsWith("[")) {
                return JsonUtils.parseToList(json);
            } else if (type != null) {
                return JsonUtils.parseToObject(json, type);
            }
        }
        return null;
    }
}
