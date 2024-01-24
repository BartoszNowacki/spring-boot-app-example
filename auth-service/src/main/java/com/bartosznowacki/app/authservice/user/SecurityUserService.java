package com.bartosznowacki.app.authservice.user;

import com.bartosznowacki.app.authservice.shared.LoggedUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class SecurityUserService implements ISecurityUserService {
    private final SecurityUserRepository securityUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityUser> user = securityUserRepository.findSecurityUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void saveUser(SecurityUser user) {
        securityUserRepository.save(user);
    }

    public void checkIfUserExists(String username) throws DuplicateKeyException {
        Optional<SecurityUser> userDB = securityUserRepository.findSecurityUserByUsername(username);
        if (userDB.isPresent()) {
            throw new DuplicateKeyException("Username is already used.");
        }
    }

    public SecurityUser findUserAndCheckPassword(String username, String password) throws BadCredentialsException {
        Optional<SecurityUser> user = securityUserRepository.findSecurityUserByUsername(username)
                .filter(securityUser -> passwordEncoder.matches(password, securityUser.getPassword()));
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new BadCredentialsException("User or password is incorrect");
        }
    }

    public LoggedUserDto getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) loadUserByUsername(authentication.getName());
        return LoggedUserDto.builder()
                .id(Math.toIntExact(securityUser.getId()))
                .roleType(securityUser.getRole())
                .build();
    }

    public SecurityUser getLoggedSecurityUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SecurityUser) loadUserByUsername(authentication.getName());
    }
}