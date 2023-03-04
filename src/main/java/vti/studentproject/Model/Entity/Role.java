package vti.studentproject.Model.Entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MENTOR, TUTOR, STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
