package a.nikolskii.archive.config;

import a.nikolskii.archive.dto.ArchiveUserRegisterDto;
import a.nikolskii.archive.entity.ArchiveUser;
import a.nikolskii.archive.repository.ArchiveRoleRepository;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ArchiveBaseMapperConfig implements OrikaMapperFactoryConfigurer {

    PasswordEncoder bCryptPasswordEncoder;
    ArchiveRoleRepository archiveRoleRepository;

    public ArchiveBaseMapperConfig(PasswordEncoder bCryptPasswordEncoder, ArchiveRoleRepository archiveRoleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.archiveRoleRepository = archiveRoleRepository;
    }

    @Override
    public void configure(@NotNull MapperFactory mapperFactory) {

        mapperFactory
                .classMap(ArchiveUserRegisterDto.class, ArchiveUser.class)
                .exclude("userId")
                .customize(new CustomMapper<ArchiveUserRegisterDto, ArchiveUser>() {
                    @Override
                    public void mapAtoB(ArchiveUserRegisterDto archiveUserRegisterDto,
                                        ArchiveUser archiveUser, MappingContext context) {
                        archiveUser.setPassword(bCryptPasswordEncoder.encode(archiveUserRegisterDto.getPassword()));
                        archiveUser.setUserRole(archiveRoleRepository.getArchiveRoleByRoleName(archiveUserRegisterDto.getUserRole()));
                    }
                })
                .byDefault()
                .register();
    }
}
