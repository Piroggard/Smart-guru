package smartguru.organizerservice.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ORGANIZER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
