package gr.hua.dit.ds.springbootdemo.rest;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    private StudentDAO studentDao;

    @GetMapping("")
    public List<Student> getStudents(){
        return studentDao.getStudents();
    }

    @PostMapping("")
    public Student saveStudent(@RequestBody  Student student){
        return studentDao.saveStudent(student);
    }


}
