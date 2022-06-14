package a.nikolskii.archive.service;

import a.nikolskii.archive.dto.ArchiveUserRegisterDto;
import a.nikolskii.archive.entity.ArchiveUser;
import a.nikolskii.archive.exception.PasswordsNotEqualsException;
import a.nikolskii.archive.exception.UserAlreadyExistException;
import a.nikolskii.archive.repository.ArchiveUserRepository;
import lombok.Data;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class ArchiveUserRegisterService {

    private final ArchiveUserRepository archiveUserRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final MapperFacade mapperFacade;

    public ArchiveUserRegisterService(ArchiveUserRepository archiveUserRepository,
                                      PasswordEncoder bCryptPasswordEncoder,
                                      MapperFacade mapperFacade) {
        this.archiveUserRepository = archiveUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mapperFacade = mapperFacade;
    }

    public void registerNewUser(ArchiveUserRegisterDto newUserDto){

        if (!newUserDto.getPassword().equals(newUserDto.getPasswordConfirm())) {
            throw new PasswordsNotEqualsException("Passwords not equals");
        }
        ArchiveUser userByEmail = archiveUserRepository.findUserByEmail(newUserDto.getEmail());
        if (userByEmail != null) {
            throw new UserAlreadyExistException(String.format("User with email %s already exist", newUserDto.getEmail()));
        } else {
            ArchiveUser newArchiveUser = mapperFacade.map(newUserDto, ArchiveUser.class);
            archiveUserRepository.save(newArchiveUser);
        }
    }

}
