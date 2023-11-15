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

    @GetMapping("")
    public String showStudents(Model model){


        model.addAttribute("students", studentDao.getStudents());
        return "students";
    }

    @GetMapping("/new")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);

        return "add_student";

    }

    @GetMapping("{student_id}")
    public String editStudent(@PathVariable Integer student_id, Model model){
        Student student = studentDao.getStudent(student_id);
        model.addAttribute("student", student);
        return "add_student";

    }

    @PostMapping("/new")
    public String saveStudent(@ModelAttribute("student") Student student, Model model) {
        studentDao.saveStudent(student);
        model.addAttribute("students", studentDao.getStudents());
        return "students";
    }

    @DeleteMapping("{student_id}")
    public String deleteStudent(@PathVariable Integer student_id){
        studentDao.deleteStudent(student_id);
        return "students";
    }


}
