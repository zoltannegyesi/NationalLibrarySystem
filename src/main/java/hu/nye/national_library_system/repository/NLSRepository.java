package hu.nye.national_library_system.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Predicate;

public class NLSRepository {

    protected <T> Predicate[] getPredicates(List<Filter<?>> filters, CriteriaBuilder criteriaBuilder, Root<T> root) {
        return filters.stream().map(filter -> filter.getPredicate(criteriaBuilder, root, filter.getFieldName()))
                .toArray(Predicate[]::new);
    }

    protected <T> void saveArrayField(EntityManager em, List<T> arrayField) {
        arrayField.forEach(em::persist);
    }

    protected void setNextId(EntityManager em, String type, Long value) {
        em.clear();
        em.createNativeQuery(String.format(SET_AUTOINCREMENT, type.toLowerCase(), value)).executeUpdate();
    }
}
