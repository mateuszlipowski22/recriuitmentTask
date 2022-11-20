package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ultimate.systems.recruitmenttask.repositories.StudentRepository;
import ultimate.systems.recruitmenttask.services.interfaces.StudentService;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

}
