package gr.hua.dit.ds.springbootdemo.repository;

import gr.hua.dit.ds.springbootdemo.entity.StudentProfile;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource(path= "studentprofile")
@Hidden
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {

}
