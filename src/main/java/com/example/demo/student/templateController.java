package com.example.demo.student;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class templateController {


    @GetMapping("login")
    public String getloginview(){

        return "login";
    }

    @GetMapping("courses")
    public String getcourseview(){
        return "courses";
    }
}
