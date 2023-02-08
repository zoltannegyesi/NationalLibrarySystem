package hu.nye.national_library_system.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryReference {

    public static final String TYPE_NAME = "LibraryLabel";

    private Long id;

    @JsonProperty("label")
    private String name;
}
