package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
        @JoinTable (name = "EmployeeSchedule",
                    joinColumns = @JoinColumn(name ="schedule_id"),
                    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employeeList;

    @ManyToMany
        @JoinTable (name = "PetSchedule",
                joinColumns = @JoinColumn(name ="schedule_id"),
                inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> petList;


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
