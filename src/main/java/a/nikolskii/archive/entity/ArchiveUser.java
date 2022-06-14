package a.nikolskii.archive.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class ArchiveUser {

    @Id
    @GeneratedValue(generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<ArchiveRole> roleSet;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private ArchiveRole userRole;

}
