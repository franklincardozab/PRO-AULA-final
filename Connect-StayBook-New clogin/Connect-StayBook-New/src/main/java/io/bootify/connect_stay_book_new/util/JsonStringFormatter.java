package io.bootify.connect_stay_book_new.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 * Generic class for printing an Object as a JSON String and parsing a String to the given type.
 * Extends TypeReference to keep generic type information.
 */
public class JsonStringFormatter<T> extends TypeReference<T> implements Formatter<T> {

    private final ObjectMapper objectMapper;

    public JsonStringFormatter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @org.springframework.lang.NonNull
    public String print(@org.springframework.lang.NonNull final T object, @org.springframework.lang.NonNull final Locale locale) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (final JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    @org.springframework.lang.NonNull
    public T parse(@org.springframework.lang.NonNull final String text, @org.springframework.lang.NonNull final Locale locale) {
        try {
            T result = objectMapper.readValue(text, this);
            if (result == null) {
                throw new RuntimeException("Parsed value is null");
            }
            return result;
        } catch (final JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}