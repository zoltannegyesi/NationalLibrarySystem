package hu.nye.national_library_system.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookLibraryRefPK implements Serializable {

    @Column(name = "book_isbn")
    private String bookIsbn;

    @Column(name = "library_id")
    private Long libraryId;
}
