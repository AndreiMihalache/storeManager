package ing.interview.storemanager.repository;

import ing.interview.storemanager.model.store.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
