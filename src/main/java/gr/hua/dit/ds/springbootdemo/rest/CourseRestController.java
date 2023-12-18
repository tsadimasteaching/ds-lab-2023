package gr.hua.dit.ds.springbootdemo.rest;

import gr.hua.dit.ds.springbootdemo.entity.Course;
import gr.hua.dit.ds.springbootdemo.repository.CourseRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Hidden
public class CourseRestController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

}
