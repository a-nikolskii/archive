package a.nikolskii.archive.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "authorities")
@Accessors(chain = true)
public class ArchiveAuthority {

    @Id
    @GeneratedValue(generator = "authority_id_sequence")
    @SequenceGenerator(name = "authority_id_sequence", sequenceName = "authority_id_sequence", allocationSize = 1)
    private Integer authorityId;
    private String authorityName;
}
