package vti.studentproject.Model.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vti.studentproject.Model.Entity.ClassStatus;
import vti.studentproject.Model.Entity.TeachingForm;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchClassReq {

    private String className;

    private Date startDate;

    private Date endDate;

    private ClassStatus classStatus;

    private TeachingForm teachingForm;

    private int mentorId;

    private int zoomId;

    private int classRoomId;

    private String description;

    private String schedule;
}
