package hu.nye.national_library_system.repository;

import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.entity.LibraryBook;
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
public class LibraryBookRepository{

    @PersistenceContext
    EntityManager em;

    public Long save(LibraryBook libraryBook) {
        em.persist(libraryBook);
        em.flush();
        return libraryBook.getId();
    }

    public LibraryBook update(LibraryBook libraryBook) {
        return em.merge(libraryBook);
    }

    public void deleteById(Long id) {
        LibraryBook savedLibrary = em.find(LibraryBook.class, id);
        em.remove(savedLibrary);
        em.flush();
    }

    public boolean notExistsById(Long id) {
        return em.find(Library.class, id) == null;
    }

    public List<LibraryBook> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<LibraryBook> cq = criteriaBuilder.createQuery(LibraryBook.class);
        Root<LibraryBook> libraryBook = cq.from(LibraryBook.class);
        cq.distinct(true).
                orderBy(criteriaBuilder.asc(libraryBook.get("id")));
        TypedQuery<LibraryBook> query = em.createQuery(cq);
        return query.getResultList();
    }

    public LibraryBook getById(Long id) {
        return em.find(LibraryBook.class, id);
    }

}
