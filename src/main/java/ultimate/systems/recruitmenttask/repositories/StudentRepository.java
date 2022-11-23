package ultimate.systems.recruitmenttask.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ultimate.systems.recruitmenttask.models.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `ultimate-solutions`.student_teachers WHERE student_id=:studentId", nativeQuery = true)
    void deleteStudentFromStudentTeacherByID(@Param("studentId") Long studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `ultimate-solutions`.teacher_students WHERE students_id=:studentId", nativeQuery = true)
    void deleteStudentFromTeacherStudentByID(@Param("studentId") Long studentId);

    List<Student> findAllByNameAndSurname(String name, String surname);

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

}
