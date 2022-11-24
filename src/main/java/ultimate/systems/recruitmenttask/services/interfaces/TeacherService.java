package ultimate.systems.recruitmenttask.services.interfaces;

import org.springframework.data.domain.Page;
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

    List<TeacherDTO> findAllTeachersDTOSorted(String direction, String field);

    Page<Teacher> findAllTeachersSortedReturnPages(int page, String direction, String field);

    List<TeacherDTO> findAllTeachersDTOByNameAndSurname(String name, String surname);

    List<TeacherDTO> findAllTeachersDTOByName(String name);

    List<TeacherDTO> findAllTeachersDTOBySurname(String surname);

    List<TeacherDTO> findAllTeachersDTOByNameAndOrSurname(String name, String surname);

    List<Teacher> findAllTeachersSorted(String direction, String field);

}
