package a.nikolskii.archive.security;

import a.nikolskii.archive.entity.ArchiveAuthority;
import a.nikolskii.archive.entity.ArchiveRole;
import a.nikolskii.archive.entity.ArchiveUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ArchiveUserDetails implements UserDetails {

    public ArchiveUserDetails(ArchiveUser archiveUser) {
        this.archiveUser = archiveUser;
    }

    private final ArchiveUser archiveUser;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return archiveUser.getUserRole().getAuthoritySet()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return archiveUser.getPassword();
    }

    @Override
    public String getUsername() {
        return archiveUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
