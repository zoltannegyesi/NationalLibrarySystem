package hu.nye.national_library_system.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryBookPK implements Serializable {

    @Column(name = "book_isbn")
    private String bookIsbn;

    @Column(name = "library_id")
    private Long libraryId;

}
