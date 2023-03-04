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
public class ClassRequest {


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

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getZoomId() {
        return zoomId;
    }

    public void setZoomId(int zoomId) {
        this.zoomId = zoomId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }


}
