package a.nikolskii.archive.dto;

import lombok.Data;

@Data
public class ArchiveUserRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordConfirm;
    private String userRole;
}
