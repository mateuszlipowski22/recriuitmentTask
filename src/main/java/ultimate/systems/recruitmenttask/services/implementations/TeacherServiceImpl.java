package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ultimate.systems.recruitmenttask.dto.TeacherDTO;
import ultimate.systems.recruitmenttask.models.Teacher;
import ultimate.systems.recruitmenttask.repositories.TeacherRepository;
import ultimate.systems.recruitmenttask.services.interfaces.TeacherService;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTO convertTeacherToTeacherDTO(Teacher teacher) {

        return TeacherDTO.builder()
                .id(teacher.getId())
                .age(teacher.getAge())
                .email(teacher.getEmail())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .students(teacher
                        .getStudents())
                .subject(teacher.getSubject())
                .build();
    }

    @Override
    public List<TeacherDTO> findAllTeachersDTO() {
        return teacherRepository
                .findAll()
                .stream()
                .map(this::convertTeacherToTeacherDTO)
                .toList();
    }

    @Override
    public Teacher convertTeacherDTOToTeacher(TeacherDTO teacherDTO) {
        return Teacher.builder()
                .id(teacherDTO.getId())
                .age(teacherDTO.getAge())
                .email(teacherDTO.getEmail())
                .name(teacherDTO.getName())
                .surname(teacherDTO.getSurname())
                .students(teacherDTO
                        .getStudents())
                .subject(teacherDTO.getSubject())
                .build();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findByTeacherId(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteTeacherFromTeacherStudentByID(id);
        teacherRepository.deleteTeacherFromStudentTeacherByID(id);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> findAllTeachersSorted(String direction, String field) {
        return teacherRepository.findAll(Sort.by(Sort.Direction.valueOf(direction), field));

    }

    @Override
    public List<TeacherDTO> findAllTeachersDTOSorted(String direction, String field) {
        return findAllTeachersSorted(direction,field)
                .stream()
                .map(this::convertTeacherToTeacherDTO)
                .toList();
    }

    @Override
    public Page<Teacher> findAllTeachersSortedReturnPages(int page, String direction, String field) {
        return teacherRepository.findAll(PageRequest.of(page-1, 4, Sort.by(Sort.Direction.valueOf(direction), field)));
    }

    @Override
    public List<TeacherDTO> findAllTeachersDTOByNameAndSurname(String name, String surname){
        return teacherRepository.findAllByNameAndSurname(name, surname)
                .stream()
                .map(this::convertTeacherToTeacherDTO)
                .toList();
    }

    @Override
    public List<TeacherDTO> findAllTeachersDTOByName(String name) {
        return teacherRepository.findAllByName(name)
                .stream()
                .map(this::convertTeacherToTeacherDTO)
                .toList();
    }

    @Override
    public List<TeacherDTO> findAllTeachersDTOBySurname(String surname) {
        return teacherRepository.findAllBySurname(surname)
                .stream()
                .map(this::convertTeacherToTeacherDTO)
                .toList();
    }

    @Override
    public List<TeacherDTO> findAllTeachersDTOByNameAndOrSurname(String name, String surname) {
        if(name!=null && !name.isEmpty()){
            if (surname!=null && !surname.isEmpty()){
                return findAllTeachersDTOByNameAndSurname(name, surname);
            }else {
                return findAllTeachersDTOByName(name);
            }
        }else {
            if (surname!=null && !surname.isEmpty()){
                return findAllTeachersDTOBySurname(surname);
            }
        }
        return null;
    }

}
