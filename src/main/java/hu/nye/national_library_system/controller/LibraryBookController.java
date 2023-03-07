package hu.nye.national_library_system.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.data.LibraryBookData;
import hu.nye.national_library_system.entity.LibraryBook;
import hu.nye.national_library_system.service.BookService;
import hu.nye.national_library_system.service.LibraryBookService;
import hu.nye.national_library_system.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Nls/LibraryBook")
@Transactional
public class LibraryBookController {

    private final LibraryBookService libraryBookService;

    private final BookService bookService;

    private final LibraryService libraryService;

    @Autowired
    public LibraryBookController(LibraryBookService libraryBookService, BookService bookService, LibraryService libraryService) {
        this.libraryBookService = libraryBookService;
        this.bookService = bookService;
        this.libraryService = libraryService;
    }

    @GetMapping
    @ResponseBody
    public Flux<LibraryBookData> findAll() {
        return libraryBookService.findAll().map(LibraryBookData::new);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<LibraryBookData> load(@PathVariable("id") Long id) {
        return libraryBookService.load(null).map(LibraryBookData::new);
    }

    @PostMapping
    @ResponseBody
    public Mono<LibraryBookData> save(@RequestBody LibraryBookData data){
        System.out.println(data.toString());
        LibraryBook libraryBook = new LibraryBook(data, bookService, libraryService);
        System.out.println(libraryBook.toString());
        return libraryBookService.save(libraryBook).map(LibraryBookData::new);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Mono<LibraryBookData> update(@PathVariable("id") Long id, @RequestBody LibraryBookData changes) {
        LibraryBook libraryBook = libraryBookService.load(null).block();
        if (libraryBook == null) {
            return null;
        }
        libraryBook.apply(changes, bookService, libraryService);
        return libraryBookService.update(libraryBook).map(LibraryBookData::new);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Mono<LibraryBookData> patch(@PathVariable("id") Long id, @RequestBody ObjectNode changes){
        return this.update(id, new LibraryBookData(changes));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Long id) {
        return libraryBookService.delete(null);
    }
}
