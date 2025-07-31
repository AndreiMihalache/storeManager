package ing.interview.storemanager.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;



@Log4j2
public abstract class Dao <T>{

    @PersistenceContext
    protected EntityManager entityManager;

    public void delete(T entity){
        entityManager.remove(entity);
    }

    public void save(T entity){
        entityManager.persist(entity);
    }

    public T update(T entity){
        return entityManager.merge(entity);
    }
}
