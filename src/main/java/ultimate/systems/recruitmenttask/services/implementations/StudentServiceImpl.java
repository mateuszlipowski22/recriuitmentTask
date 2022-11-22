package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ultimate.systems.recruitmenttask.dto.StudentDTO;
import ultimate.systems.recruitmenttask.models.Student;
import ultimate.systems.recruitmenttask.repositories.StudentRepository;
import ultimate.systems.recruitmenttask.services.interfaces.StudentService;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO convertStudentToStudentDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .age(student.getAge())
                .email(student.getEmail())
                .name(student.getName())
                .surname(student.getSurname())
                .teachers(student
                        .getTeachers())
                .specialization(student.getSpecialization())
                .build();
    }

    @Override
    public List<StudentDTO> findAllStudentsDTO() {
        return studentRepository
                .findAll()
                .stream()
                .map(this::convertStudentToStudentDTO)
                .toList();
    }

    @Override
    public Student convertStudentDTOToStudent(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId())
                .age(studentDTO.getAge())
                .email(studentDTO.getEmail())
                .name(studentDTO.getName())
                .surname(studentDTO.getSurname())
                .teachers(studentDTO
                        .getTeachers())
                .specialization(studentDTO.getSpecialization())
                .build();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentFromStudentTeacherByID(id);
        studentRepository.deleteStudentFromTeacherStudentByID(id);
        studentRepository.deleteById(id);
    }
}
