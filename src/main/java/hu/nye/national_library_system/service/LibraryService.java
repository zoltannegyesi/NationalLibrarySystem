package hu.nye.national_library_system.service;

import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Flux<Library> findAll() {
        return Flux.fromIterable(libraryRepository.findAll());
    }

    public Mono<Library> load(Long id) {
        return Mono.just(libraryRepository.getById(id));
    }

    public Mono<Library> save(Library library) {
        Long libraryId = libraryRepository.save(library);
        return Mono.just(libraryRepository.getById(libraryId));
    }

    public Mono<Library> update(Library library){
        return Mono.just(libraryRepository.update(library));
    }

    public boolean delete(Long id) {
        libraryRepository.deleteById(id);
        return libraryRepository.notExistsById(id);
    }
}
