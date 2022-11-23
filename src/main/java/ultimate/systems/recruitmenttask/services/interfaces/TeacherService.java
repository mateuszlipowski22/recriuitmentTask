package ultimate.systems.recruitmenttask.services.interfaces;

import ultimate.systems.recruitmenttask.dto.TeacherDTO;
import ultimate.systems.recruitmenttask.models.Student;
import ultimate.systems.recruitmenttask.models.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAllTeachers();

    TeacherDTO convertTeacherToTeacherDTO(Teacher teacher);

    List<TeacherDTO> findAllTeachersDTO();

    Teacher convertTeacherDTOToTeacher(TeacherDTO teacherDTO);

    void saveTeacher(Teacher teacher);

    Teacher findByTeacherId(Long teacherId);

    void deleteTeacherById(Long id);
}
