package lab.dao.jpa;

import lab.dao.CountryDao;
import lab.model.Country;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityTransaction;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryJpaDaoImpl extends AbstractJpaDao implements CountryDao {

    private static final String SELECT_COUNTRY = "SELECT c FROM Country c WHERE c.name LIKE :name";

    @Override
    public void save(Country country) {
        Optional.ofNullable(emf.createEntityManager())
                .ifPresent(em -> {
                    EntityTransaction transaction = em.getTransaction();
                    transaction.begin();
                    try {
                        em.persist(country);
                    } finally {
                        transaction.commit();
                        em.close();
                    }
                });
    }

    @Override
    public List<Country> getAllCountries() {

        return Optional.ofNullable(emf.createEntityManager())
                .map(entityManager -> {
                    try {
                        return entityManager.createQuery("select c from Country c", Country.class)
                                .getResultList();
                    } finally {
                        entityManager.close();
                    }
                })
                .orElseGet(Collections::emptyList);
    }

    @Override
    public Country getCountryByName(String name) {
        return Optional.ofNullable(emf.createEntityManager())
                .map(entityManager -> {
                    try {
                        return entityManager.createQuery(
                                SELECT_COUNTRY,
                                Country.class)
                                .setParameter("name", name)
                                .getSingleResult();
                    } finally {
                        entityManager.close();
                    }
                })
                .orElseThrow(() -> new RuntimeException("Нет страны с таким именем: " + name));
    }

}
