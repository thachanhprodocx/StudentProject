package vti.studentproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vti.studentproject.Model.Entity.ClassEntity;
import vti.studentproject.Model.Request.ClassRequest;
import vti.studentproject.Service.ClassService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/class")
public class ClassController {


    @Autowired
    private ClassService classService;

    @GetMapping("/getAllClass")
    public List<ClassEntity> getAllClasses() {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ClassEntity getClassById(@PathVariable int id) {
        return classService.getClassById(id);
    }

    @PostMapping
    public void createClass(@RequestBody ClassRequest classRequest) {
        classService.createClass(classRequest);
    }

    @PutMapping("/{id}")
    public ClassEntity updateClass(@PathVariable int id, @RequestBody ClassRequest classRequest) {
        return classService.updateClass(id, classRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteClassById(@PathVariable int id) {
        classService.deleteClassById(id);
    }
}
