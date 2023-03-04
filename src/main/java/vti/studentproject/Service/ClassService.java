package vti.studentproject.Service;

import org.springframework.stereotype.Service;
import vti.studentproject.Model.Entity.ClassEntity;
import vti.studentproject.Model.Request.ClassRequest;

import java.util.List;

@Service
public interface ClassService {
    List<ClassEntity> getAllClasses();

    ClassEntity getClassById(int id);

    void createClass(ClassRequest classRequest);

    ClassEntity updateClass(int id, ClassRequest classRequest);

    void deleteClassById(int id);
}
