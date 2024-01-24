package com.bartosznowacki.app.authservice.user;

import com.bartosznowacki.app.authservice.shared.LoggedUserDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ISecurityUserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void saveUser(SecurityUser user);

    void checkIfUserExists(String username) throws DuplicateKeyException;

    SecurityUser findUserAndCheckPassword(String username, String password) throws BadCredentialsException;

    LoggedUserDto getLoggedUser();

    SecurityUser getLoggedSecurityUser();
}
