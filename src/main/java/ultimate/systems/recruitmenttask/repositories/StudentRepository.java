package ultimate.systems.recruitmenttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ultimate.systems.recruitmenttask.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
