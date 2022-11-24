package ultimate.systems.recruitmenttask.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"","/"})
    public String showAllTeachers(){
        return "redirect:/teachers/all/1/ASC/surname";
    }

}
