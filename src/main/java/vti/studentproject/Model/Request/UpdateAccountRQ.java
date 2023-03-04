package vti.studentproject.Model.Request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateAccountRQ {
    private String username;
    private Date dateOfBirth;
    private String address;
    private String password;
    private String fullName;
    private String role;
    private String phoneNumber;
    private String email;
    private String facebook;
    private String information;
    private int classEntityId;
}
