package com.bartosznowacki.app.authservice.shared;

import lombok.*;

import java.util.Objects;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoggedUserDto {
    private Integer id;
    private Role roleType;

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
