package hu.nye.national_library_system.data;

import static hu.nye.national_library_system.entity.Library.*;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryData {

    private Long id;

    private String name;

    private String address;

    private String openTime;

    private String closeTime;

    public LibraryData(Library library) {
        this.id = library.getId();
        this.name = JSONConverter.getString(library.getName());
        this.address = JSONConverter.getString(library.getAddress());
        this.openTime = JSONConverter.localTimeToString(library.getOpenTime());
        this.closeTime = JSONConverter.localTimeToString(library.getCloseTime());
    }

    public LibraryData(ObjectNode changes) {
        this.name = JSONConverter.getString(changes.get(FIELD_NAME_NAME));
        this.address = JSONConverter.getString(changes.get(FIELD_NAME_ADDRESS));
        this.openTime = JSONConverter.getTime(changes.get(FIELD_NAME_OPEN_TIME));
        this.closeTime = JSONConverter.getTime(changes.get(FIELD_NAME_CLOSE_TIME));
    }
}
