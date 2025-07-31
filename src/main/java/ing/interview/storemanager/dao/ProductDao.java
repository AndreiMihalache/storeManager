package ing.interview.storemanager.dao;

import ing.interview.storemanager.model.Product;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Log4j2
public class ProductDao extends Dao<Product> {

    public Product findById(Long id){

        final String queryString = "select p from Product p where p.id = :id";
        final TypedQuery<Product> query = entityManager.createQuery(queryString, Product.class);

        query.setParameter("id", id);

        Product product= null;

        try {
            product = query.getSingleResult();
        }
        catch (final NoResultException exception)
        {
            log.debug("No product found with id {}", id);
        }

        return product;
    }

    public void deleteById(Long id){
        final String queryString = "delete from Product p where p.id = :id";
        final TypedQuery<Product> query = entityManager.createQuery(queryString, Product.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }


    public List<Product> findAll() {
        final String queryString = "select p from Product p";
        final TypedQuery<Product> query = entityManager.createQuery(queryString, Product.class);
        List<Product> productList = null;
        try {
            productList = query.getResultList();
        }
        catch (final NoResultException exception){
            log.debug("No products found");
        }
        return  productList;
    }
}
