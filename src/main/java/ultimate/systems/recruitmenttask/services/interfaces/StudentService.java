package ultimate.systems.recruitmenttask.services.interfaces;

import ultimate.systems.recruitmenttask.dto.StudentDTO;
import ultimate.systems.recruitmenttask.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    StudentDTO convertStudentToStudentDTO(Student student);

    List<StudentDTO> findAllStudentsDTO();

    Student convertStudentDTOToStudent(StudentDTO studentDTO);

    void saveStudent(Student student);

    Student findByStudentId(Long studentId);

    void deleteStudentById(Long id);
}
