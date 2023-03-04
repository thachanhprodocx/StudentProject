package vti.studentproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vti.studentproject.Model.Entity.ClassEntity;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

    Optional<ClassEntity> findById(Integer id);
}
