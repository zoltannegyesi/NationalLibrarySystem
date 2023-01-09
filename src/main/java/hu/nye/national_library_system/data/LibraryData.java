package hu.nye.national_library_system.data;

import hu.nye.national_library_system.entity.BookLibraryRef;
import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.util.JSONConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class LibraryData {

    private Long id;

    private String name;

    private String address;

    private String openTime;

    private String closeTime;

    private List<BookLibraryRefData> bookLibraryRefDataList;

    public LibraryData(Library library) {
        this.id = library.getId();
        this.name = JSONConverter.getString(library.getName());
        this.address = JSONConverter.getString(library.getAddress());
        this.openTime = JSONConverter.localTimeToString(library.getOpenTime());
        this.closeTime = JSONConverter.localTimeToString(library.getCloseTime());
        this.bookLibraryRefDataList = JSONConverter.getBookLibraryRef(library.getBookLibraryRefList());
    }
}
