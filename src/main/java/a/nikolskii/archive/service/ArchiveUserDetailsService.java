package a.nikolskii.archive.service;

import a.nikolskii.archive.entity.ArchiveUser;
import a.nikolskii.archive.repository.ArchiveUserRepository;
import a.nikolskii.archive.security.ArchiveUserDetails;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Data
@Service
public class ArchiveUserDetailsService implements UserDetailsService {

    private final ArchiveUserRepository archiveUserRepository;

    public ArchiveUserDetailsService(ArchiveUserRepository archiveUserRepository) {
        this.archiveUserRepository = archiveUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ArchiveUser archiveUser = archiveUserRepository.findUserByEmail(email);
        if(archiveUser != null) {
            return new ArchiveUserDetails(archiveUser);
        } else {
            throw new UsernameNotFoundException(String.format("User with email %s not found", email));
        }
    }
}
