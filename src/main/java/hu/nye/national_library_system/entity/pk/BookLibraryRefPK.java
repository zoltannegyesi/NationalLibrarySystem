package hu.nye.national_library_system.entity.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class BookLibraryRefPK implements Serializable {

    @Column(name = "book_isbn")
    private String bookIsbn;

    @Column(name = "library_id")
    private Long libraryId;
}
