package hu.nye.national_library_system.util;

import com.fasterxml.jackson.databind.JsonNode;
import hu.nye.national_library_system.data.BookReference;
import hu.nye.national_library_system.data.LibraryReference;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReferenceFactory {

    private final String idName;
    private final String labelName;

    public BookReference getBookReference(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        return new BookReference(jsonNode.get(idName).asText(), jsonNode.get(labelName).asText());
    }

    public LibraryReference getLibraryReference(JsonNode jsonNode) {
        if (jsonNode == null) {
            return null;
        }
        return new LibraryReference(jsonNode.get(idName).asLong(), jsonNode.get(labelName).asText());
    }
}
