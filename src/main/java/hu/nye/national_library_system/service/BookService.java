package hu.nye.national_library_system.service;

import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Flux<Book> findAll() {
        return Flux.fromIterable(bookRepository.findAll());
    }

    public Mono<Book> load(String id) {
        return Mono.just(bookRepository.getById(id));
    }

    public Mono<Book> save(Book book) {
        String bookId = bookRepository.save(book);
        book.setArrayFieldIds(bookId);
        bookRepository.saveArrayFields(book);
        return Mono.just(bookRepository.getById(bookId));
    }

    public Mono<Book> update(Book book) {
        return Mono.just(bookRepository.update(book));
    }

    public boolean delete(String id) {
        bookRepository.deleteById(id);
        return bookRepository.notExistsById(id);
    }
}
