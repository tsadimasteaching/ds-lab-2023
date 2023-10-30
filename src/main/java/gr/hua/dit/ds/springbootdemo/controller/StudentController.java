package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {

    private List<Student> students = new ArrayList<Student>();

    @PostConstruct
    public void setup() {
        Student Stud1= new Student(1, "Nick", "Jones", "nick@hua.gr");
        Student Stud2= new Student(2, "Jack", "James", "jack@hua.gr");
        Student Stud3= new Student(2,"John", "Stone", "john@hua.gr");
        students.add(Stud1);
        students.add(Stud2);
        students.add(Stud3);
    }
    @GetMapping("")
    public String showStudents(Model model){


        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable Integer id, Model model){
        Student student = students.get(id);
        model.addAttribute("students", student);
        return "students";
    }

    @GetMapping("/new")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);

        return "add_student";

    }

    @PostMapping("/new")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        System.out.println(student);
        System.out.println(students);
        students.add(student);
        model.addAttribute("students", students);
        return "students";
    }
}
