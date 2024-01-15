package gr.hua.dit.ds.springbootdemo.config;

import gr.hua.dit.ds.springbootdemo.entity.Assignment;
import gr.hua.dit.ds.springbootdemo.entity.Course;
import gr.hua.dit.ds.springbootdemo.entity.Student;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Course.class);
        config.exposeIdsFor(Student.class);
        config.exposeIdsFor(Assignment.class);
    }
}