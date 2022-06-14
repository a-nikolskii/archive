package a.nikolskii.archive.controler;

import a.nikolskii.archive.dto.ArchiveApiErrorDto;
import a.nikolskii.archive.exception.DocumentAlreadyExistException;
import a.nikolskii.archive.exception.DocumentNotFoundException;
import a.nikolskii.archive.exception.PasswordsNotEqualsException;
import a.nikolskii.archive.exception.UserAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            PasswordsNotEqualsException.class,
            DocumentAlreadyExistException.class,
            DocumentNotFoundException.class,
            UserAlreadyExistException.class})
    public ArchiveApiErrorDto handleBadRequestException(Exception exception) {
        log.error("handleBadRequestException()", exception);
        return new ArchiveApiErrorDto(HttpStatus.BAD_REQUEST, exception);
    }

}
