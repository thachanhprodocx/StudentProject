package vti.studentproject.Model.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SearchClassRoomReq {


    private String name;


    private String address;


    private String note;


    private int size;

    private int mentorId;
}
