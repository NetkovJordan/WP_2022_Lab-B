package mk.ukim.finki.wp.university.web.controller;

import mk.ukim.finki.wp.university.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.university.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController  {
    private final StudentService studentService;

    public RegisterController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public String renderRegisterPage(){
        return "register";
    }
    @PostMapping
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String repeatPassword,
                               @RequestParam String name,
                               @RequestParam String surname
                               ){
        try{
            this.studentService.register(username,password,repeatPassword,name,surname);
            return "redirect:/login";
        }catch (InvalidArgumentsException exception){
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
