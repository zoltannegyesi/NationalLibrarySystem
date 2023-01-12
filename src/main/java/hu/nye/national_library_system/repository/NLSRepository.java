package hu.nye.national_library_system.repository;

import javax.persistence.EntityManager;
import java.util.List;

import static hu.nye.national_library_system.Constants.SET_AUTOINCREMENT;


public class NLSRepository {

    protected <T> void saveArrayField(EntityManager em, List<T> arrayField) {
        arrayField.forEach(em::persist);
    }

    protected <T> void setNextId(EntityManager em, String type, T value) {
        em.clear();
        em.createNativeQuery(String.format(SET_AUTOINCREMENT, type.toLowerCase(), value)).executeUpdate();
    }

}
