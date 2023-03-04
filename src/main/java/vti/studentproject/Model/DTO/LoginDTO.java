package vti.studentproject.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.studentproject.Model.Entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    private int id;
    private String username;
    private Role role;
    private String fullName;

    private String userAgent;
    private String token;
}
