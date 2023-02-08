package hu.nye.national_library_system.repository;

import static hu.nye.national_library_system.constant.Constants.SET_AUTOINCREMENT;

import javax.persistence.EntityManager;
import java.util.List;


public class NLSRepository {


    protected <T> void setNextId(EntityManager em, String type, T value) {
        em.clear();
        em.createNativeQuery(String.format(SET_AUTOINCREMENT, type.toLowerCase(), value)).executeUpdate();
    }

}
