package ultimate.systems.recruitmenttask.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ultimate.systems.recruitmenttask.dto.StudentDTO;
import ultimate.systems.recruitmenttask.dto.TeacherDTO;
import ultimate.systems.recruitmenttask.models.Student;
import ultimate.systems.recruitmenttask.models.Teacher;
import ultimate.systems.recruitmenttask.services.interfaces.StudentService;
import ultimate.systems.recruitmenttask.services.interfaces.TeacherService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("teachers/")
public class TeacherController {

    private final StudentService studentService;
    private final TeacherService teacherService;


    @ModelAttribute
    public void addStudentsList(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
    }

    @GetMapping("all")
    public String addParameters(){
        return "redirect:/teachers/all/1/ASC/surname";
    }

    @GetMapping("all/{page}/{direction}/{field}")
    public String showAllTeachersSortedPage(Model model, @PathVariable String direction, @PathVariable String field, @PathVariable int page){
        Page<Teacher> pages = teacherService.findAllTeachersSortedReturnPages(page, direction, field);
        model.addAttribute("number", pages.getNumber());
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("teachersDTO",pages.getContent()
                .stream()
                .map(teacherService::convertTeacherToTeacherDTO)
                .toList());
        model.addAttribute("direction", direction);
        model.addAttribute("field", field);
        return "teachers/all";
    }

    @GetMapping("add")
    public String showAddTeacherForm(Model model){
        model.addAttribute("teacherDTO", Teacher.builder().build());
        return "teachers/addTeacher";
    }

    @PostMapping("add")
    public String processAddTeacher(@Valid TeacherDTO teacherDTO, BindingResult result){
        if (result.hasErrors()) {
            return "teachers/addTeacher";
        }

        Teacher teacherToSave = teacherService.convertTeacherDTOToTeacher(teacherDTO);
        teacherService.saveTeacher(teacherToSave);
        return "redirect:/teachers/all";
    }

    @GetMapping("{id}/edit")
    public String showEditTeacherForm(Model model, @PathVariable Long id){
        model.addAttribute("teacherDTO", teacherService.convertTeacherToTeacherDTO(teacherService.findByTeacherId(id)));
        return "teachers/edit";
    }

    @PostMapping("edit")
    public String processEditTeacher(@Valid TeacherDTO teacherDTO, BindingResult result, Long id){
        if (result.hasErrors()) {
            return "teachers/edit";
        }

        Teacher teacherToEdit = teacherService.findByTeacherId(id);
        teacherToEdit.setName(teacherDTO.getName());
        teacherToEdit.setSurname(teacherDTO.getSurname());
        teacherToEdit.setAge(teacherDTO.getAge());
        teacherToEdit.setEmail(teacherDTO.getEmail());
        teacherToEdit.setSubject(teacherDTO.getSubject());
        teacherToEdit.setStudents(teacherDTO.getStudents());
        teacherService.saveTeacher(teacherToEdit);
        return "redirect:/teachers/all";
    }

    @GetMapping("{id}/show")
    public String showTeacherPage(Model model, @PathVariable Long id){
        model.addAttribute("teacherDTO", teacherService.convertTeacherToTeacherDTO(teacherService.findByTeacherId(id)));
        return "teachers/show";
    }

    @GetMapping("{id}/delete")
    public String showDeleteTeacherForm(Model model, @PathVariable Long id) {
        model.addAttribute("teacherDTO", teacherService.findByTeacherId(id));
        return "teachers/delete";
    }

    @Transactional
    @PostMapping("/delete")
    public String processDeleteTeacher(@RequestParam("id") Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers/all";
    }

    @PostMapping("/search")
    public String processSearchTeacher(Model model, String name, String surname) {
        model.addAttribute("teachersDTO", teacherService.findAllTeachersDTOByNameAndOrSurname(name, surname));
        return "teachers/results";
    }
}
