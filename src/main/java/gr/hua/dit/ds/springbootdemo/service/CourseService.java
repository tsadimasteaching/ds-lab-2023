package gr.hua.dit.ds.springbootdemo.service;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Course;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import gr.hua.dit.ds.springbootdemo.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Transactional
    public void saveCourse(Course course){
        courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public Course getCourse(Integer courseId) {
        return courseRepository.findById(courseId).get();
    }

    public List<Course> getStudentCourses(Integer studentId){
       Student student = studentDAO.getStudent(studentId);
         return student.getCourses();
    }
}
