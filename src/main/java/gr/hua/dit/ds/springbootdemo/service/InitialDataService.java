package gr.hua.dit.ds.springbootdemo.service;

import com.github.javafaker.Faker;
import gr.hua.dit.ds.springbootdemo.entity.Course;
import gr.hua.dit.ds.springbootdemo.entity.Role;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import gr.hua.dit.ds.springbootdemo.entity.User;
import gr.hua.dit.ds.springbootdemo.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

/**
 * Service to populate database with initial data.
 */
@Service
public class InitialDataService {

    private static final int LAST_STUDENT_ID = 10;
    private static final int LAST_COURSE_ID = 10;
    private static final int LAST_STUDENT_COURSE_ID = 10;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentRepository assignmentRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataService(UserRepository userRepository,
                              RoleRepository roleRepository,
                              StudentRepository studentRepository,
                              CourseRepository courseRepository,
                              AssignmentRepository assignmentRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.assignmentRepository = assignmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void createUsersAndRoles() {
        final List<String> rolesToCreate = List.of("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER");
        for (final String roleName : rolesToCreate) {
            roleRepository.findByName(roleName).orElseGet(() -> {
                roleRepository.save(new Role(roleName));
                return null;
            });
        }

        this.userRepository.findByUsername("user").orElseGet(() -> {
            User user = new User("user", "user@hua.gr", this.passwordEncoder.encode("1234"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_ADMIN").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
        this.userRepository.findByUsername("manager").orElseGet(() -> {
            User user = new User("manager", "manager@hua.gr", this.passwordEncoder.encode("1234"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_MANAGER").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
        this.userRepository.findByUsername("admin").orElseGet(() -> {
            User user = new User("admin", "admin@hua.gr", this.passwordEncoder.encode("1234"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_MANAGER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_ADMIN").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
    }

    private void createStudents() {
        for (int i=1; i<=LAST_STUDENT_ID; i++) {
            final Faker faker = new Faker(new Random(i));
            final String firstName = faker.name().firstName();
            final String lastName = faker.name().lastName();
            final String email = faker.internet().emailAddress();

            this.studentRepository.findByEmail(email).orElseGet(() -> {
                Student student = new Student();
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);
                this.studentRepository.save(student);
                return null;
            });
        }
    }

    private void createCourses() {
        for (int i=1; i<=LAST_COURSE_ID; i++) {
            final String title = "Course " + i;

            this.courseRepository.findByTitle(title).orElseGet(() -> {
                Course course = new Course();
                course.setTitle(title);
                this.courseRepository.save(course);
                return null;
            });
        }
    }

    private void createStudentsCourses() {
        final List<Student> studentList = this.studentRepository.findAll();
        for (Student student : studentList) {
            final Faker faker = new Faker(new Random(student.getId()));

            final List<Integer> randomCourseIdList = Stream.of(
                faker.number().numberBetween(1, LAST_COURSE_ID),
                faker.number().numberBetween(1, LAST_COURSE_ID),
                faker.number().numberBetween(1, LAST_COURSE_ID),
                faker.number().numberBetween(1, LAST_COURSE_ID),
                faker.number().numberBetween(1, LAST_COURSE_ID)
            ).distinct().toList();

            final List<Course> newCourseList = new ArrayList<>();
            for (final int courseId : randomCourseIdList) {
                Course course = this.courseRepository.findById(courseId).orElse(null);
                if (course == null) {
                    System.out.println("Course with ID " + courseId + " does not exist!");
                    continue;
                }
                newCourseList.add(course);
            }

            student.setCourses(newCourseList);
            this.studentRepository.save(student);
        }
    }

    @PostConstruct
    public void setup() {
        this.createUsersAndRoles();
        this.createCourses();
        this.createStudents();
        this.createStudentsCourses();
    }
}
