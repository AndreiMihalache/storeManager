package ing.interview.storemanager.repository;

import ing.interview.storemanager.model.security.StoreUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreUserRepository extends JpaRepository<StoreUser, Integer> {
    Optional<StoreUser> findByUsername(String username);
}
