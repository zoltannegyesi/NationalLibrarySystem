package hu.nye.national_library_system.repository;

import hu.nye.national_library_system.entity.Library;
import hu.nye.national_library_system.filter.Filter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class LibraryRepository extends NLSRepository{

    @PersistenceContext
    EntityManager em;

    public Long save(Library library) {
        em.persist(library);
        em.flush();
        return library.getId();
    }

    public Library merge(Library library) {
        Library result = em.merge(library);
        em.flush();
        return result;
    }

    public void saveArrayFields(Library library) {
        saveArrayField(em, library.getBookLibraryRefList());
        em.flush();
    }

    public Library update(Library library) {
        setNextId(em, Library.TYPE_NAME, library.getId());
        return em.merge(library);
    }

    public void deleteById(Long id) {
        Library savedLibrary = em.find(Library.class, id);
        em.remove(savedLibrary);
        em.flush();
    }

    public boolean notExistsById(Long id) {
        return em.find(Library.class, id) == null;
    }

    public List<Library> findAll(List<Filter<?>> filters) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Library> cq = criteriaBuilder.createQuery(Library.class);
        Root<Library> library = cq.from(Library.class);
        Predicate[] filterPredicates = getPredicates(filters, criteriaBuilder, library);
        cq.distinct(true).where(filterPredicates).
                orderBy(criteriaBuilder.asc(library.get(Library.FIELD_NAME_NAME)));
        TypedQuery<Library> query = em.createQuery(cq);
        return query.getResultList();
    }

    public Library getById(String id) {
        return em.find(Library.class, id);
    }

}
