package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.dao.StudentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Assignment;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import gr.hua.dit.ds.springbootdemo.repository.AssignmentRepository;
import gr.hua.dit.ds.springbootdemo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assignment")
public class AssignmentController  {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    StudentDAO studentDAO;

    @PostMapping("{student_id}")
    public String saveAssignment(@PathVariable int student_id, @ModelAttribute("assignment") Assignment assignment){
        System.out.println("student_id: "+student_id);
        assignmentService.saveAssignment(assignment,student_id);
        return "redirect:/assignment/"+student_id;
    }

    @GetMapping("{student_id}")
    public String showAssignments(@PathVariable int student_id, Model model){
        Student student = studentDAO.getStudent(student_id);
        List<Assignment> assignments = student.getAssignments();
        model.addAttribute("student", student);
        model.addAttribute("assignments", assignments);
        return "assignments";
    }

    @GetMapping("{student_id}/{assignment_id}")
    public String editAssignment(@PathVariable int student_id, @PathVariable int assignment_id, Model model){
        Assignment assignment = assignmentService.getAssignment(assignment_id);
        model.addAttribute("assignment", assignment);
        model.addAttribute("student_id", student_id);
        return "add_assignment";
    }

    @PostMapping("{student_id}/{assignment_id}")
    public String updateAssignment(@PathVariable int student_id, @PathVariable int assignment_id){
        Assignment assignment = assignmentService.getAssignment(assignment_id);
        assignmentService.saveAssignment(assignment, student_id);
        return "redirect:/assignment/"+student_id;
    }

    @GetMapping("{student_id}/new")
    public String addAssignment(@PathVariable int student_id, Model model){
        Assignment assignment = new Assignment();
        model.addAttribute("assignment", assignment);
        model.addAttribute("student_id", student_id);
        return "add_assignment";
    }

}
