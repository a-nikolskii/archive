package a.nikolskii.archive.repository;

import a.nikolskii.archive.entity.ArchiveDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArchiveDocumentRepository extends JpaRepository<ArchiveDocument, Long> {

    ArchiveDocument findArchiveDocumentByDocumentId(Long documentId);
    void deleteArchiveDocumentByDocumentId(Long documentId);
    ArchiveDocument findArchiveDocumentByDocumentAuthorAndDocumentTitle(String author, String title);
    Page<ArchiveDocument> findByDocumentTitleContainsOrDocumentAuthorContains(String documentTitle, String documentAuthor, Pageable pageable);
}
