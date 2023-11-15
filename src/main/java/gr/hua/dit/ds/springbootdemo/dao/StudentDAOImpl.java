package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Student> getStudents() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Student> query = currentSession.createQuery("from Student", Student.class);

        List<Student> students = query.getResultList();

        return students;

    }

    @Override
    public void saveStudent(Student student) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(student);


    }

    @Override
    public Student getStudent(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Student astudent =currentSession.get(Student.class, id);
        return astudent;
    }
}
