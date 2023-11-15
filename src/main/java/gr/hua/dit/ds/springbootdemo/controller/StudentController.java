package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentDAO studentDao;
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


        model.addAttribute("students", studentDao.getStudents());

        return "students";
    }

    @GetMapping("/{id}")
    public String showStudent(@PathVariable Integer id, Model model){
        Student student = studentDao.getStudent(id);
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
        studentDao.saveStudent(student);
        model.addAttribute("students", studentDao.getStudents());
        return "students";
    }
}
