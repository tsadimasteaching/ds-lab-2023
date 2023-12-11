package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Assignment;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Student> getStudents() {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student getStudent(Integer student_id) {
        return entityManager.find(Student.class, student_id);
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        System.out.println("student "+ student.getId());
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    @Override
    @Transactional
    public void deleteStudent(Integer student_id) {
        System.out.println("Deleting student with id: " + student_id);
       entityManager.remove(entityManager.find(Student.class, student_id));
    }

    @Override
    @Transactional
    public List<Assignment> getAssignments(Integer student_id) {
        Student student = entityManager.find(Student.class, student_id);
        return student.getAssignments();
    }
}
