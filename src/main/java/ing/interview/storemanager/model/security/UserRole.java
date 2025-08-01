package ing.interview.storemanager.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private StoreUser storeUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id",  nullable = false)
    private Role role;
}
