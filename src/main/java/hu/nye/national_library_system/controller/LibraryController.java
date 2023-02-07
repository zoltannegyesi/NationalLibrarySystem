package hu.nye.national_library_system.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.data.LibraryData;
import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Nls/Library")
@Transactional
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    @ResponseBody
    public Flux<LibraryData> findAll() {
        return libraryService.findAll().map(LibraryData::new);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<LibraryData> load(@PathVariable("id") Long id) {
        return libraryService.load(id).map(LibraryData::new);
    }

    @PostMapping
    @ResponseBody
    public Mono<LibraryData> save(@RequestBody LibraryData data){
        Library library = new Library(data);
        return libraryService.save(library).map(LibraryData::new);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Mono<LibraryData> update(@PathVariable("id") Long id, @RequestBody LibraryData changes) {
        Library library = libraryService.load(id).block();
        if (library == null) {
            return null;
        }
        library.apply(changes);
        return libraryService.update(library).map(LibraryData::new);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Mono<LibraryData> patch(@PathVariable("id") Long id, @RequestBody ObjectNode changes){
        return this.update(id, new LibraryData(changes));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id) {
        return libraryService.delete(id);
    }
}
