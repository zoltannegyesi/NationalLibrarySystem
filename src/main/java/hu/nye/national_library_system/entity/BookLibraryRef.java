package hu.nye.national_library_system.entity;

import hu.nye.national_library_system.entity.pk.BookLibraryRefPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLibraryRef implements Serializable {

    @EmbeddedId
    private BookLibraryRefPK id;

    @ManyToOne
    @MapsId("bookIsbn")
    @JoinColumn(name = "book_isbn", columnDefinition = "VARCHAR(13)")
    private Book book;

    @ManyToOne
    @MapsId("libraryId")
    @JoinColumn(name = "library_id")
    private Library library;

    @Column(name = "available",  columnDefinition = "TINYINT(1)")
    private Boolean available;

    @Column(name = "lending_date")
    private LocalDateTime lending_date;
}
