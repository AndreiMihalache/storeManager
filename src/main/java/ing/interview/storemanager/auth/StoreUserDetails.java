package ing.interview.storemanager.auth;

import ing.interview.storemanager.model.security.StoreUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class StoreUserDetails implements UserDetails {

    private final transient StoreUser storeUser;

    public StoreUserDetails(StoreUser user) {
        this.storeUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return storeUser.getUserRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .toList();
    }

    @Override
    public String getPassword() {
        return storeUser.getPassword();
    }

    @Override
    public String getUsername() {
        return storeUser.getUsername();
    }
}
