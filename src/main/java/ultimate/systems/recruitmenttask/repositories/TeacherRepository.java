package ultimate.systems.recruitmenttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ultimate.systems.recruitmenttask.models.Student;
import ultimate.systems.recruitmenttask.models.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `ultimate-solutions`.student_teachers WHERE teachers_id=:teacherId", nativeQuery = true)
    void deleteTeacherFromStudentTeacherByID(@Param("teacherId") Long teacherId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `ultimate-solutions`.teacher_students WHERE teacher_id=:teacherId", nativeQuery = true)
    void deleteTeacherFromTeacherStudentByID(@Param("teacherId") Long teacherId);

    List<Teacher> findAllByNameAndSurname(String name, String surname);

    List<Teacher> findAllByName(String name);

    List<Teacher> findAllBySurname(String surname);

}
