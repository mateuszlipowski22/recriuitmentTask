package ultimate.systems.recruitmenttask.services.interfaces;

import org.springframework.data.domain.Page;
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

    List<StudentDTO> findAllStudentsDTOSorted(String direction, String field);

    Page<Student> findAllStudentsSortedReturnPages(int page, String direction, String field);

    List<StudentDTO> findAllStudentsDTOByNameAndSurname(String name, String surname);

    List<StudentDTO> findAllStudentsDTOByName(String name);

    List<StudentDTO> findAllStudentsDTOBySurname(String surname);

    List<StudentDTO> findAllStudentsDTOByNameAndOrSurname(String name, String surname);

    List<Student> findAllStudentsSorted(String direction, String field);

}
