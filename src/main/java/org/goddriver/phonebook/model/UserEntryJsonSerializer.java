package org.goddriver.phonebook.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserEntryJsonSerializer
        extends JsonSerializer<UserEntry> {

    @Override
    public void serialize(UserEntry userEntry, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", userEntry.getFullName());
        jsonGenerator.writeEndObject();
    }
}
