package hu.nye.national_library_system.entity;

import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @OneToMany(mappedBy = "book")
    private List<BookLibraryRef> bookLibraryRefList;

    public Library(LibraryData libraryData) {
        apply(libraryData);
    }

    public void apply(LibraryData libraryData) {
        this.name = ValueConverter.getStringValue(libraryData.getName(), this.name);
        this.address = ValueConverter.getStringValue(libraryData.getAddress(), this.address);
        this.openTime = ValueConverter.getTimeValue(ValueConverter.stringToLocalTime(libraryData.getOpenTime()), this.openTime);
        this.closeTime = ValueConverter.getTimeValue(ValueConverter.stringToLocalTime(libraryData.getCloseTime()), this.closeTime);
        this.bookLibraryRefList = ValueConverter.getBookLibraryRefList(libraryData.getBookLibraryRefDataList(), this.bookLibraryRefList);
    }
}
