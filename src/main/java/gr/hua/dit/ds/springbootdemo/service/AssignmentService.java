package gr.hua.dit.ds.springbootdemo.service;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Assignment;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import gr.hua.dit.ds.springbootdemo.repository.AssignmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    public void saveAssignment(Assignment assignment, Integer student_id){
        Student student = studentDAO.getStudent(student_id);
        student.getAssignments().add(assignment);
    }

    public Assignment getAssignment(int assignmentId) {
        return assignmentRepository.findById(assignmentId).get();
    }
}

