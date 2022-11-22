package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
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
}
