package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<Student> findAllStudentsSorted(String direction, String field) {
        return studentRepository.findAll(Sort.by(Sort.Direction.valueOf(direction), field));

    }

    @Override
    public List<StudentDTO> findAllStudentsDTOSorted(String direction, String field) {
        return findAllStudentsSorted(direction,field)
                .stream()
                .map(this::convertStudentToStudentDTO)
                .toList();
    }

    @Override
    public Page<Student> findAllStudentsSortedReturnPages(int page, String direction, String field) {
          return studentRepository.findAll(PageRequest.of(page-1, 4, Sort.by(Sort.Direction.valueOf(direction), field)));
    }

    @Override
    public List<StudentDTO> findAllStudentsDTOByNameAndSurname(String name, String surname) {
        return studentRepository.findAllByNameAndSurname(name, surname)
                .stream()
                .map(this::convertStudentToStudentDTO)
                .toList();
    }

    @Override
    public List<StudentDTO> findAllStudentsDTOByName(String name) {
        return studentRepository.findAllByName(name)
                .stream()
                .map(this::convertStudentToStudentDTO)
                .toList();
    }

    @Override
    public List<StudentDTO> findAllStudentsDTOBySurname(String surname) {
        return studentRepository.findAllBySurname(surname)
                .stream()
                .map(this::convertStudentToStudentDTO)
                .toList();
    }

    @Override
    public List<StudentDTO> findAllStudentsDTOByNameAndOrSurname(String name, String surname) {
        if(name!=null && !name.isEmpty()){
            if (surname!=null && !surname.isEmpty()){
                return findAllStudentsDTOByNameAndSurname(name, surname);
            }else {
                return findAllStudentsDTOByName(name);
            }
        }else {
            if (surname!=null && !surname.isEmpty()){
                return findAllStudentsDTOBySurname(surname);
            }
        }
        return null;
    }
}
