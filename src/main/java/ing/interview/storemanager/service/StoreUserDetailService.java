package ing.interview.storemanager.service;

import ing.interview.storemanager.auth.StoreUserDetails;
import ing.interview.storemanager.repository.StoreUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreUserDetailService implements UserDetailsService {

    private final StoreUserRepository storeUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return storeUserRepository.findByUsername(username)
                .map(StoreUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
