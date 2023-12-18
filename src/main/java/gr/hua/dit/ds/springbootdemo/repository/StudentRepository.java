package gr.hua.dit.ds.springbootdemo.repository;

import gr.hua.dit.ds.springbootdemo.entity.Student;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path= "student")
@Hidden
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
