package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> getStudents();

    public void saveStudent(Student student);

    public Student getStudent(int id);
}
