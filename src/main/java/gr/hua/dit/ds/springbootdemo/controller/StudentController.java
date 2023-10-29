package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    private List<Student> students = new ArrayList<Student>();

    @PostConstruct
    public void setup() {
        Student Stud1= new Student("Nick", "Jones", "nick@hua.gr");
        Student Stud2= new Student("Jack", "James", "jack@hua.gr");
        Student Stud3= new Student("John", "Stone", "john@hua.gr");
        students.add(Stud1);
        students.add(Stud2);
        students.add(Stud3);
    }
    @GetMapping("")
    public String showStudents(Model model){


        model.addAttribute("students", students);

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
