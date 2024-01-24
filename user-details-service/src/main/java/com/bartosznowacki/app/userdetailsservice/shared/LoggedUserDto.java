package com.bartosznowacki.app.userdetailsservice.shared;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Objects;

import static com.google.common.collect.Sets.newHashSet;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUserDto {
    private Integer id;
    private Role roleType;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return newHashSet(new SimpleGrantedAuthority("ROLE_" + roleType.name()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoggedUserDto that)) return false;
        return Objects.equals(id, that.id) && roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleType);
    }

    @Override
    public String toString() {
        return "SecurityLoggedUserDto{" +
                "id=" + id +
                ", roleType=" + roleType +
                '}';
    }
}
