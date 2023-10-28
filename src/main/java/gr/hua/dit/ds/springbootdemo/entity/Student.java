package gr.hua.dit.ds.springbootdemo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @jakarta.persistence.Id
    @Column
    private Integer Id;

    @Column
    private String firstName;
    @Column
    private String lastName;

    @Column
    private String email;

}
