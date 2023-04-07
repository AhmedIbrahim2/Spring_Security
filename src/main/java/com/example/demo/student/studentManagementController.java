package com.example.demo.student;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("managemnet/api/v1/students")
public class studentManagementController {
    private static final List<Student> students = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_Admin' ,'ROLE_AdminTraine')")
    public List<Student> getall(){
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void register(@RequestBody Student student){
        System.out.print(student);
    }

    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delete (@PathVariable("id") Integer id){
        System.out.print(id);
    }

    @PutMapping(path = "{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void update (@PathVariable Integer id ,@RequestBody Student student){
        System.out.print(String.format("%s %s",id , student));
    }


}
