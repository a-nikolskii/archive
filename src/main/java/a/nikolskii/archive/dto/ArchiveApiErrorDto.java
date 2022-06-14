package a.nikolskii.archive.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
public class ArchiveApiErrorDto {

    private HttpStatus status;
    private Instant timestamp;
    private String description;

    public ArchiveApiErrorDto() {
        this.timestamp = Instant.now();
    }

    public ArchiveApiErrorDto(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.description = ex.getLocalizedMessage();
    }
}
