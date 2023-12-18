package gr.hua.dit.ds.springbootdemo.repository;

import gr.hua.dit.ds.springbootdemo.entity.Course;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
