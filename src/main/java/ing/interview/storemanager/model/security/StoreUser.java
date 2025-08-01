package ing.interview.storemanager.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class StoreUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "storeUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<UserRole> userRoles;
}
