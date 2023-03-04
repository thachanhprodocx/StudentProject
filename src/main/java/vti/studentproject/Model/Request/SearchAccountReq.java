package vti.studentproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.studentproject.Model.Entity.Role;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAccountReq extends BaseRequest {

    private String username;

    private String address;

    private String fullName;

    private String email;

    private int classId;

    private Set<Role> role;

    private Long minPrice;
    private Long maxPrice;

}
