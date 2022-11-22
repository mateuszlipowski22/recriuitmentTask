package ultimate.systems.recruitmenttask.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ultimate.systems.recruitmenttask.dto.StudentDTO;
import ultimate.systems.recruitmenttask.models.Student;
import ultimate.systems.recruitmenttask.services.interfaces.StudentService;
import ultimate.systems.recruitmenttask.services.interfaces.TeacherService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("students/")
public class StudentController {

    private final StudentService studentService;
    private final TeacherService teacherService;


    @ModelAttribute
    public void addTeachersList(Model model) {
        model.addAttribute("teachers", teacherService.findAllTeachers());
    }

    @GetMapping("all")
    public String showAllStudents(Model model){
        model.addAttribute("studentsDTO", studentService.findAllStudentsDTO());
        return "students/all";
    }

    @GetMapping("add")
    public String showAddStudentForm(Model model){
        model.addAttribute("studentDTO", StudentDTO.builder().build());
        return "students/addStudent";
    }

    @PostMapping("add")
    public String processAddStudent(@Valid StudentDTO studentDTO, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "students/addStudent";
        }

        Student studentToSave = studentService.convertStudentDTOToStudent(studentDTO);
        studentService.saveStudent(studentToSave);
        return "redirect:/students/all";
    }

    @GetMapping("{id}/edit")
    public String showEditStudentForm(Model model, @PathVariable Long id){
        model.addAttribute("studentDTO", studentService.convertStudentToStudentDTO(studentService.findByStudentId(id)));
        return "students/edit";
    }

    @PostMapping("edit")
    public String processEditStudent(@Valid StudentDTO studentDTO, BindingResult result, Long id){
        if (result.hasErrors()) {
            return "students/edit";
        }

        Student studentToEdit = studentService.findByStudentId(id);
        studentToEdit.setName(studentDTO.getName());
        studentToEdit.setSurname(studentDTO.getSurname());
        studentToEdit.setAge(studentDTO.getAge());
        studentToEdit.setEmail(studentDTO.getEmail());
        studentToEdit.setSpecialization(studentDTO.getSpecialization());
        studentToEdit.setTeachers(studentDTO.getTeachers());
        studentService.saveStudent(studentToEdit);
        return "redirect:/students/all";
    }

    @GetMapping("{id}/show")
    public String showStudentPage(Model model, @PathVariable Long id){
        model.addAttribute("studentDTO", studentService.convertStudentToStudentDTO(studentService.findByStudentId(id)));
        return "students/show";
    }

    @GetMapping("{id}/delete")
    public String showDeleteStudentForm(Model model, @PathVariable Long id) {
        model.addAttribute("studentDTO", studentService.findByStudentId(id));
        return "students/delete";
    }

    @Transactional
    @PostMapping("/delete")
    public String processDeleteUser(@RequestParam("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/all";
    }
}
