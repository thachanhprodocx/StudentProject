package vti.studentproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vti.studentproject.Model.Entity.ClassRoom;
import vti.studentproject.Model.Request.ClassRoomRequest;
import vti.studentproject.Service.Class.ClassRoomService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/classrooms")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("/get-All")
    public List<ClassRoom> getAllClassRooms() {
        return classRoomService.getAllClassRooms();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createClassRoom(@RequestBody ClassRoomRequest classRoomRequest) {
        classRoomService.createClassRoom(classRoomRequest);
        return new ResponseEntity<>("Bạn đã tạo thành công ", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassRoom(@PathVariable int id) {
        classRoomService.deleteClassRoom(id);
        return new ResponseEntity<>("Deleted ClassRoom successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> getClassRoomById(@PathVariable int id) {
        ClassRoom classRoom = classRoomService.getClassRoomById(id);
        return new ResponseEntity<>(classRoom, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoom> updateClassRoom(@PathVariable int id, @RequestBody ClassRoomRequest classRoomRequest) {
        ClassRoom classRoom = classRoomService.updateClassRoom(id, classRoomRequest);
        return new ResponseEntity<>(classRoom, HttpStatus.OK);
    }
}
