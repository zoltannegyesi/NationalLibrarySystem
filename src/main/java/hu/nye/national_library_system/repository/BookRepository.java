package hu.nye.national_library_system.repository;

import hu.nye.national_library_system.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    EntityManager em;

    public String save(Book book) {
        em.persist(book);
        em.flush();
        return book.getIsbn();
    }

    public Book update(Book book) {
        return em.merge(book);
    }

    public void deleteById(String id) {
        Book savedBook = em.find(Book.class, id);
        em.remove(savedBook);
        em.flush();
    }

    public boolean notExistsById(String id) {
        return em.find(Book.class, id) == null;
    }

    public List<Book> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        cq.distinct(true).
            orderBy(criteriaBuilder.asc(book.get(Book.FIELD_NAME_ISBN)));
        TypedQuery<Book> query = em.createQuery(cq);
        return query.getResultList();
    }

    public Book getById(String id) {
        return em.find(Book.class, id);
    }

}
