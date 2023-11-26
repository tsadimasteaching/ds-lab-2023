package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Course;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import gr.hua.dit.ds.springbootdemo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("")
    public String showCourses(Model model){
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @GetMapping("/new")
    public String addCourse(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "add_course";
    }

    @GetMapping("{course_id}")
    public String editCourse(@PathVariable Integer course_id, Model model){
        Course course = courseService.getCourse(course_id);
        model.addAttribute("course", course);
        return "add_course";
    }

    @GetMapping("students/{course_id}")
    public String enrollStudents(@PathVariable Integer course_id, Model model){
        Course course = courseService.getCourse(course_id);
        model.addAttribute("course", course);
        List<Student> students = studentDAO.getStudents();
        model.addAttribute("students", students);
        return "add_students_to_course";
    }

    @PostMapping("students/{course_id}/{student_id}")
    public String enrollStudent(@PathVariable Integer course_id, @PathVariable Integer student_id, Model model){
        System.out.println("inside post");
        Course course = courseService.getCourse(course_id);
        Student student = studentDAO.getStudent(student_id);
        course.addStudent(student);
        courseService.saveCourse(course);
        model.addAttribute("course", course);
        List<Student> students = studentDAO.getStudents();
        model.addAttribute("students", students);
        return "courses";
    }

    @GetMapping("/student/{student_id}")
    public String getStudentCourses(@PathVariable Integer student_id, Model model){
        model.addAttribute("courses", courseService.getStudentCourses(student_id));
        return "courses";
    }
    @PostMapping("/new")
    public String saveCourse(Course course, Model model){
        courseService.saveCourse(course);
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }


    @DeleteMapping("{course_id}")
    public String deleteCourse(@PathVariable Integer course_id){
        courseService.deleteCourse(course_id);
        return "courses";
    }

}
