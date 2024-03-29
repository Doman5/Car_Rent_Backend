package pl.domanski.carrent.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.domanski.carrent.security.model.RentUserDetails;
import pl.domanski.carrent.security.model.User;
import pl.domanski.carrent.common.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RentUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(username)).orElseThrow();
        RentUserDetails rentUserDetails = new RentUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities().stream()
                        .map(userRole -> (GrantedAuthority) userRole::getRole)
                        .toList()
        );
        rentUserDetails.setId(user.getId());
        return rentUserDetails;
    }
}
