package hu.nye.national_library_system.service;

import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.repository.LibraryRepository;
import hu.nye.national_library_system.validation.ValidationError;
import hu.nye.national_library_system.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;
    //TODO: private final LibraryValidator libraryValidator;

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

    public Mono<Library> save(Library library) throws ValidationException {
        validate(library);
        Long libraryId = libraryRepository.save(library);
        //TODO: library.setArrayFieldIds(libraryId)
        libraryRepository.saveArrayFields(library);
        return Mono.just(libraryRepository.getById(libraryId));
    }

    public Mono<Library> update(Library library) throws ValidationException {
        validate(library);
        return Mono.just(libraryRepository.update(library));
    }

    public boolean delete(Long id) {
        libraryRepository.deleteById(id);
        return libraryRepository.notExistsById(id);
    }

    private void validate(Library library) throws ValidationException {
        //TODO: List<ValidationError> invalids = libraryValidator.validate(library);
        /*
        if (!invalids.isEmpty()) {
            throw new ValidationException(invalids, TYPE_NAME);
        }
         */
    }

}
