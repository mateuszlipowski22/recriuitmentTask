package ultimate.systems.recruitmenttask.services.implementations;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ultimate.systems.recruitmenttask.repositories.TeacherRepository;
import ultimate.systems.recruitmenttask.services.interfaces.TeacherService;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

}
