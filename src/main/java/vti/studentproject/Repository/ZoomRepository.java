package vti.studentproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vti.studentproject.Model.Entity.Zoom;

@Repository
public interface ZoomRepository extends JpaRepository<Zoom, Integer> {
}
