package hu.nye.national_library_system.service;

import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.repository.LibraryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Transactional
public class LibraryBookService {

    private final LibraryBookRepository libraryBookRepository;

    @Autowired
    public LibraryBookService(LibraryBookRepository libraryBookRepository) {
        this.libraryBookRepository = libraryBookRepository;
    }

    public Flux<LibraryBook> findAll() {
        return Flux.fromIterable(libraryBookRepository.findAll());
    }

    public Mono<LibraryBook> load(Long id) {
        return Mono.just(libraryBookRepository.getById(id));
    }

    public Mono<LibraryBook> save(LibraryBook libraryBook) {
        Long id = libraryBookRepository.save(libraryBook);
        return Mono.just(libraryBookRepository.getById(id));
    }

    public Mono<LibraryBook> update(LibraryBook libraryBook){
        return Mono.just(libraryBookRepository.update(libraryBook));
    }

    public boolean delete(Long id) {
        libraryBookRepository.deleteById(id);
        return libraryBookRepository.notExistsById(id);
    }
}
