package vti.studentproject.Model.Request;

import lombok.Data;


@Data
public class ClassRoomRequest {
    private int id;


    private String name;


    private String address;


    private String note;


    private int size;

    private int mentorId;
}
