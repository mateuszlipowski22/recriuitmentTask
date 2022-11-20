package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}
