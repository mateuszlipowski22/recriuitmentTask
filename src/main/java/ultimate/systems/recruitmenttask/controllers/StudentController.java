package ultimate.systems.recruitmenttask.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("students/")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final TeacherService teacherService;


    @ModelAttribute
    public void addTeachersList(Model model) {
        model.addAttribute("teachers", teacherService.findAllTeachers());
    }

    @GetMapping("all")
    public String addParameters(){
        return "redirect:/students/all/1/ASC/surname";
    }

    @GetMapping("all/{page}/{direction}/{field}")
    public String showAllStudentsSortedPage(Model model, @PathVariable String direction, @PathVariable String field, @PathVariable int page){
        Page<Student> pages = studentService.findAllStudentsSortedReturnPages(page, direction, field);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("studentsDTO",pages.getContent()
                .stream()
                .map(studentService::convertStudentToStudentDTO)
                .toList());
        model.addAttribute("direction", direction);
        model.addAttribute("field", field);
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
    public String processDeleteStudent(@RequestParam("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/all";
    }

    @PostMapping("/search")
    public String processSearchStudent(Model model, String name, String surname) {
        model.addAttribute("studentsDTO", studentService.findAllStudentsDTOByNameAndOrSurname(name, surname));
        return "students/results";
    }
}
