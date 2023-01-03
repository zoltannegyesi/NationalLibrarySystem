package hu.nye.national_library_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String isbn;

    @Column(name = "eto")
    private Double eto;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "author", length = 100)
    private String author;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "number_of_pages")
    private Long numberOfPages;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    @Type(type = "hu.nye.national_library_system.customtype.type.LongString")
    private String description;

}
