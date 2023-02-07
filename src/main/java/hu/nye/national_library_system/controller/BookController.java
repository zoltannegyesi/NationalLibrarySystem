package hu.nye.national_library_system.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import hu.nye.national_library_system.data.BookData;
import hu.nye.national_library_system.entity.Book;
import hu.nye.national_library_system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Nls/Book")
@Transactional
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseBody
    public Flux<BookData> findAll() {
        return Flux.empty();
        //return bookService.findAll().map(BookData::new);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<BookData> load(@PathVariable("id") String id) {
        return bookService.load(id).map(BookData::new);
    }

    @PostMapping
    @ResponseBody
    public Mono<BookData> save(@RequestBody BookData data) {
        Book book = new Book(data);
        return bookService.save(book).map(BookData::new);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Mono<BookData> update(@PathVariable("id") String id, @RequestBody BookData changes) {
        Book book = bookService.load(id).block();
        if (book == null) {
            return null;
        }
        book.apply(changes);
        return bookService.update(book).map(BookData::new);
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public Mono<BookData> patch(@PathVariable("id") String id, @RequestBody ObjectNode changes) {
        return this.update(id, new BookData(changes));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") String id) {
        return bookService.delete(id);
    }
}
