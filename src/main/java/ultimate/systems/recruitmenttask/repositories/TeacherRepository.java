package ultimate.systems.recruitmenttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ultimate.systems.recruitmenttask.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
