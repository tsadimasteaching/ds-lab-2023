package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Assignment;
import gr.hua.dit.ds.springbootdemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> getStudents();
    public Student getStudent(Integer student_id);
    public Student saveStudent(Student student);
    public void deleteStudent(Integer student_id);

    public List<Assignment> getAssignments(Integer student_id);

}
