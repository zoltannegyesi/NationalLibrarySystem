package hu.nye.national_library_system.service;

import hu.nye.national_library_system.entity.Library;
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

    public Flux<Library> findAll() {
        return Flux.fromIterable(libraryBookRepository.findAll());
    }

    public Mono<Library> load(Long id) {
        return Mono.just(libraryBookRepository.getById(id));
    }

    public Mono<Library> save(Library library) {
        Long libraryId = libraryBookRepository.save(library);
        return Mono.just(libraryBookRepository.getById(libraryId));
    }

    public Mono<Library> update(Library library){
        return Mono.just(libraryBookRepository.update(library));
    }

    public boolean delete(Long id) {
        libraryBookRepository.deleteById(id);
        return libraryBookRepository.notExistsById(id);
    }
}
