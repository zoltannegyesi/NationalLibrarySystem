package hu.nye.national_library_system.repository;

import hu.nye.national_library_system.filter.Filter;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static hu.nye.national_library_system.Constants.SET_AUTOINCREMENT;


public class NLSRepository {

    protected <T> Predicate[] getPredicates(List<Filter<?>> filters, CriteriaBuilder criteriaBuilder, Root<T> root) {
        return filters.stream().map(filter -> filter.getPredicate(criteriaBuilder, root, filter.getFieldName()))
                .toArray(Predicate[]::new);
    }

    protected <T> void saveArrayField(EntityManager em, List<T> arrayField) {
        arrayField.forEach(em::persist);
    }

    protected <T> void setNextId(EntityManager em, String type, T value) {
        em.clear();
        em.createNativeQuery(String.format(SET_AUTOINCREMENT, type.toLowerCase(), value)).executeUpdate();
    }

}
