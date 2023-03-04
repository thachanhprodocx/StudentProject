package vti.studentproject.Model.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotNull()
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String fullName;

    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Facebook is mandatory")
    private String facebook;

    private String information;

    private int classId;
}
