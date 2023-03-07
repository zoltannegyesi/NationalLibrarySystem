package hu.nye.national_library_system.entity;

import static hu.nye.national_library_system.key.KeyTypeConstants.*;

import static javax.persistence.GenerationType.IDENTITY;

import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.util.ValueConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    public static final String TYPE_NAME = "Library";
    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_NAME = "name";
    public static final String FIELD_NAME_ADDRESS = "address";
    public static final String FIELD_NAME_OPEN_TIME = "openTime";
    public static final String FIELD_NAME_CLOSE_TIME = "closeTime";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedString", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private String name;

    @Column(name = "address", length = 100)
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedString", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = SYSTEM_WIDE)})
    private String address;

    @Column(name = "open_time")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedTime", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = GROUP_WIDE)})
    private LocalTime openTime;

    @Column(name = "close_time")
    @Type(type = "hu.nye.national_library_system.customtype.type.EncryptedTime", parameters = {
            @org.hibernate.annotations.Parameter(name = KEY_TYPE, value = USER_WIDE)})
    private LocalTime closeTime;

    @OneToMany(mappedBy = "library", orphanRemoval = true)
    @ToString.Exclude private List<LibraryBook> libraryBooks;

    public Library(LibraryData libraryData) {
        apply(libraryData);
    }

    public void apply(LibraryData libraryData) {
        this.name = ValueConverter.getStringValue(libraryData.getName(), this.name);
        this.address = ValueConverter.getStringValue(libraryData.getAddress(), this.address);
        this.openTime = ValueConverter.getTimeValue(ValueConverter.stringToLocalTime(libraryData.getOpenTime()), this.openTime);
        this.closeTime = ValueConverter.getTimeValue(ValueConverter.stringToLocalTime(libraryData.getCloseTime()), this.closeTime);
    }
}
