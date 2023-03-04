package vti.studentproject.Service;

import org.springframework.stereotype.Service;
import vti.studentproject.Model.Entity.ClassRoom;
import vti.studentproject.Model.Request.ClassRoomRequest;

import java.sql.SQLException;
import java.util.List;

@Service
public interface IClassRoomService {
    List<ClassRoom> getAllClassRooms();

    void createClassRoom(ClassRoomRequest classRoomRequest);

    void deleteClassRoom(int id);

    ClassRoom getClassRoomById(int id) throws SQLException;

    ClassRoom updateClassRoom(int id, ClassRoomRequest classRoomRequest);
}
