package a.nikolskii.archive.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "archive_document")
public class ArchiveDocument {

    @Id
    @GeneratedValue(generator = "document_id_sequence")
    @SequenceGenerator(name = "document_id_sequence", sequenceName = "document_id_sequence", allocationSize = 1)
    private Long documentId;
    @Temporal(TemporalType.DATE)
    private Date documentDate;
    private String documentTitle;
    private String documentAuthor;
    private String documentContent;
}
