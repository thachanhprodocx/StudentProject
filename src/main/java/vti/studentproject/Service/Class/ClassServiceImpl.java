package vti.studentproject.Service.Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vti.studentproject.Exception.AppException;
import vti.studentproject.Exception.ErrorResponseBase;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Entity.ClassEntity;
import vti.studentproject.Model.Entity.ClassRoom;
import vti.studentproject.Model.Entity.Zoom;
import vti.studentproject.Model.Request.ClassRequest;
import vti.studentproject.Repository.AccountRepository;
import vti.studentproject.Repository.ClassRepository;
import vti.studentproject.Repository.ClassRoomRepository;
import vti.studentproject.Repository.ZoomRepository;
import vti.studentproject.Service.ClassService;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ZoomRepository zoomRepository;

    @Autowired
    private ClassRoomRepository classRoomRepository;


    @Override
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }


    @Override
    public ClassEntity getClassById(int id) {
        return classRepository.findById(id).orElse(null);
    }

    @Override
    public void createClass(ClassRequest classRequest) {
        ClassEntity classEntity = new ClassEntity();

        // Set properties from classRequest
        classEntity.setClassName(classRequest.getClassName());
        classEntity.setStartDate(classRequest.getStartDate());
        classEntity.setEndDate(classRequest.getEndDate());


        // Set mentor
        Account mentor = accountRepository.findById(classRequest.getMentorId()).orElse(null);
        classEntity.setMentorId(mentor);

        // Set zoom
        Zoom zoom = zoomRepository.findById(classRequest.getZoomId()).orElse(null);
        classEntity.setZoomId(zoom);

        // Set classroom
        ClassRoom classroom = classRoomRepository.findById(classRequest.getClassRoomId()).orElse(null);
        classEntity.setClassRoomId(classroom);

        classRepository.save(classEntity);
    }

    @Override
    public ClassEntity updateClass(int id, ClassRequest classRequest) {
        ClassEntity classEntity = classRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorResponseBase.Id_not));

        // Set các thuộc tính mới từ classRequest vào classEntity
        classEntity.setClassName(classRequest.getClassName());
        classEntity.setStartDate(classRequest.getStartDate());
        classEntity.setEndDate(classRequest.getEndDate());
        classEntity.setClassStatus(classRequest.getClassStatus());
        classEntity.setTeachingForm(classRequest.getTeachingForm());
        classEntity.setMentorId(accountRepository.findById(classRequest.getMentorId()).orElse(null));
        classEntity.setZoomId(zoomRepository.findById(classRequest.getZoomId()).orElse(null));
        classEntity.setClassRoomId(classRoomRepository.findById(classRequest.getClassRoomId()).orElse(null));
        classEntity.setDescription(classRequest.getDescription());
        classEntity.setSchedule(classRequest.getSchedule());

        return classRepository.save(classEntity);
    }

    @Override
    public void deleteClassById(int id) {
        classRepository.deleteById(id);
    }
}
