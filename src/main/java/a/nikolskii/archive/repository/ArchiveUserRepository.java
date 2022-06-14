package a.nikolskii.archive.repository;

import a.nikolskii.archive.entity.ArchiveUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveUserRepository extends JpaRepository<ArchiveUser, Long> {

    ArchiveUser findUserByEmail(String email);
}
