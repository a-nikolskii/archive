package a.nikolskii.archive.service;

import a.nikolskii.archive.entity.ArchiveDocument;
import a.nikolskii.archive.exception.DocumentAlreadyExistException;
import a.nikolskii.archive.repository.ArchiveDocumentRepository;
import lombok.Data;
import ma.glasnost.orika.MapperFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@Transactional
public class ArchiveDocumentService {

    ArchiveDocumentRepository archiveDocumentRepository;
    MapperFacade mapperFacade;

    public ArchiveDocumentService(ArchiveDocumentRepository archiveDocumentRepository) {
        this.archiveDocumentRepository = archiveDocumentRepository;
    }

    public void createNewArchiveDocument(ArchiveDocument newArchiveDocument) {
        ArchiveDocument archiveDocumentByDocumentAuthorAndDocumentTitle = archiveDocumentRepository
                .findArchiveDocumentByDocumentAuthorAndDocumentTitle(
                        newArchiveDocument.getDocumentAuthor(),
                        newArchiveDocument.getDocumentTitle()
                );
        if (archiveDocumentByDocumentAuthorAndDocumentTitle != null) {
            throw new DocumentAlreadyExistException(
                    String.format("Document with title \"%s\" by %s already exist",
                            newArchiveDocument.getDocumentTitle(),
                            newArchiveDocument.getDocumentAuthor()));
        } else {
            archiveDocumentRepository.save(newArchiveDocument);
        }
    }

    public List<ArchiveDocument> listAllArchiveDocument() {
        return archiveDocumentRepository.findAll();
    }

    public Page<ArchiveDocument> listAllArchiveDocument(int pageNum, String keyword) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        if(keyword != null)
            return archiveDocumentRepository
                    .findByDocumentTitleContainsOrDocumentAuthorContains(keyword, keyword, pageable);
        return archiveDocumentRepository.findAll(pageable);
    }


    public ArchiveDocument getArchiveDocument(Long documentId) {
        return archiveDocumentRepository.findArchiveDocumentByDocumentId(documentId);
    }

    public void saveArchiveDocument(ArchiveDocument archiveDocument){
        archiveDocumentRepository.save(archiveDocument);
    }

    public void deleteArchiveDocument(long documentId) {
        archiveDocumentRepository.deleteArchiveDocumentByDocumentId(documentId);
    }

}
