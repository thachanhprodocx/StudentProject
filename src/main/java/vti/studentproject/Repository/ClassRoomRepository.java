package vti.studentproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vti.studentproject.Model.Entity.ClassRoom;


@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

}
