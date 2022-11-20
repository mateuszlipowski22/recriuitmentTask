package ultimate.systems.recruitmenttask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ultimate.systems.recruitmenttask.models.Teacher;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull
    @Size(min = 2)
    private String name;

    private String surname;

    @NotNull
    @Min(value = 19)
    private Integer age;

    @NotNull
    @Email
    private String email;

    private String specialization;

    private List<Teacher> teachers;
}
