package ultimate.systems.recruitmenttask.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String subject;

    @ManyToMany
    private List<Student> students;
}
