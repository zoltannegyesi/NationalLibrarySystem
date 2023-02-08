package hu.nye.national_library_system.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReference {

    public static final String TYPE_NAME = "BookLabel";

    private String isbn;

    @JsonProperty("label")
    private String title;
}
