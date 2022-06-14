package a.nikolskii.archive.repository;

import a.nikolskii.archive.entity.ArchiveRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArchiveRoleRepository extends JpaRepository<ArchiveRole, Integer> {
    ArchiveRole getArchiveRoleByRoleName(String roleName);
}
