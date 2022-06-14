package a.nikolskii.archive.entity;

import liquibase.pro.packaged.J;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="roles")
@Accessors(chain = true)
public class ArchiveRole {

    @Id
    @GeneratedValue(generator = "role_id_sequence")
    @SequenceGenerator(name = "role_id_sequence", sequenceName = "role_id_sequence", allocationSize = 1)
    private Integer roleId;
    private String roleName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="role_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<ArchiveAuthority> authoritySet;

}